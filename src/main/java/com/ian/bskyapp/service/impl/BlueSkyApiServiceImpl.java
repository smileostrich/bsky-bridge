package com.ian.bskyapp.service.impl;

import com.ian.bskyapp.entity.Session;
import com.ian.bskyapp.entity.Feeds;
import com.ian.bskyapp.entity.Likes;
import com.ian.bskyapp.entity.StrongRef;
import com.ian.bskyapp.service.BlueSkyApiService;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.ian.bskyapp.util.JsonUtil.objectMapperWithDate;

@Service
public class BlueSkyApiServiceImpl implements BlueSkyApiService {

    private static final String HOST = "bsky.social";
    private static final String HTTPS = "https";
    private static final String HEADER_AUTH = "Authorization";

    private static final String API_REPO_CREATE_RECORD = "xrpc/com.atproto.repo.createRecord";

    private static final String API_FEED_POST = "app.bsky.feed.post";
    private static final String API_FEED_REPOST = "app.bsky.feed.repost";
    private static final String API_FEED_LIKE = "app.bsky.feed.like";
    private static final String API_GRAPH_FOLLOW = "app.bsky.graph.follow";

    public void createPost(Session session, String text) {
        postCall(session,
                parseRecord(API_FEED_POST, "text", text),
                API_FEED_POST);
    }

    public void repost(Session session, StrongRef strongRef) {
        postCall(session,
                parseRecord(API_FEED_REPOST, "subject", strongRef),
                API_FEED_REPOST);
    }

    public void like(Session session, StrongRef strongRef) {
        postCall(session,
                parseRecord(API_FEED_LIKE, "subject", strongRef),
                API_FEED_LIKE);
    }

    public void follow(Session session, String did) {
        postCall(session,
                parseRecord(API_GRAPH_FOLLOW, "subject", did),
                API_GRAPH_FOLLOW);
    }

    public Feeds getAuthorFeed(Session session) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(HTTPS)
                .host(HOST)
                .addPathSegments("xrpc/app.bsky.feed.getAuthorFeed")
                .addEncodedQueryParameter("actor", session.did())
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTH, "Bearer %s".formatted(session.jwt().access()))
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.code() == 400)
                throw new IllegalStateException("API error: " + response.body().string());

            return objectMapperWithDate().readValue(response.body().string(), Feeds.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Feeds getTimeLine(Session session) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(HTTPS)
                .host(HOST)
                .addPathSegments("xrpc/app.bsky.feed.getTimeline")
                .addEncodedQueryParameter("actor", session.did())
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTH, "Bearer %s".formatted(session.jwt().access()))
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.code() == 400)
                throw new IllegalStateException("API error: " + response.body().string());

            return objectMapperWithDate().readValue(response.body().string(), Feeds.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Likes getLikes(Session session, String uri) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(HTTPS)
                .host(HOST)
                .addPathSegments("xrpc/app.bsky.feed.getLikes")
                .addEncodedQueryParameter("uri", uri)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTH, "Bearer %s".formatted(session.jwt().access()))
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.code() == 400)
                throw new IllegalStateException("API error: " + response.body().string());

            return objectMapperWithDate().readValue(response.body().string(), Likes.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> parseRecord(String type, String key, Object value) {
        return Map.of(
                "$type", type,
                key, value,
                "createdAt", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
    }

    private void postCall(Session session, Map<String, Object> record, String type) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(HTTPS)
                .host(HOST)
                .addPathSegments(API_REPO_CREATE_RECORD)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTH, "Bearer %s".formatted(session.jwt().access()))
                .post(getRequestBody(session, type, record))
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.code() == 400)
                throw new IllegalStateException("API error: " + response.body().string());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @NotNull
    private static RequestBody getRequestBody(Session session, String type, Map<String, Object> record) {
        Map<String, Object> map = Map.of(
                "collection", type,
                "$type", type,
                "repo", session.did(),
                "record", record
        );

        RequestBody body;
        try {
            body = RequestBody.create(objectMapperWithDate()
                    .writeValueAsString(map), MediaType.get("application/json"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return body;
    }

}
