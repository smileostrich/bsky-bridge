package com.ian.bskyapp.service.impl;

import com.ian.bskyapp.entity.*;
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
        executePostRequest(session,
                parseRecord(API_FEED_POST, "text", text),
                API_FEED_POST);
    }

    public void repost(Session session, StrongRef strongRef) {
        executePostRequest(session,
                parseRecord(API_FEED_REPOST, "subject", strongRef),
                API_FEED_REPOST);
    }

    public void like(Session session, StrongRef strongRef) {
        executePostRequest(session,
                parseRecord(API_FEED_LIKE, "subject", strongRef),
                API_FEED_LIKE);
    }

    public void follow(Session session, String did) {
        executePostRequest(session,
                parseRecord(API_GRAPH_FOLLOW, "subject", did),
                API_GRAPH_FOLLOW);
    }

    public Followers getFollowers(Session session) {
        HttpUrl url = buildUrl("xrpc/app.bsky.graph.getFollowers", "actor", session.did());

        return executeGetRequest(session, url, Followers.class);
    }

    public Feeds getAuthorFeed(Session session) {
        HttpUrl url = buildUrl("xrpc/app.bsky.feed.getAuthorFeed", "actor", session.did());

        return executeGetRequest(session, url, Feeds.class);
    }

    public Feeds getTimeLine(Session session) {
        HttpUrl url = buildUrl("xrpc/app.bsky.feed.getTimeline", "actor", session.did());

        return executeGetRequest(session, url, Feeds.class);
    }

    public Likes getLikes(Session session, String uri) {
        HttpUrl url = buildUrl("xrpc/app.bsky.feed.getLikes", "uri", uri);

        return executeGetRequest(session, url, Likes.class);
    }

    private HttpUrl buildUrl(String pathSegments, String queryParamKey, String queryParamValue) {
        return new HttpUrl.Builder()
                .scheme(HTTPS)
                .host(HOST)
                .addPathSegments(pathSegments)
                .addEncodedQueryParameter(queryParamKey, queryParamValue)
                .build();
    }

    private <T> T executeGetRequest(Session session, HttpUrl url, Class<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTH, "Bearer %s".formatted(session.jwt().access()))
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.code() == 400) {
                throw new IllegalStateException("API error: " + response.body().string());
            }

            return objectMapperWithDate().readValue(response.body().string(), responseType);
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

    private void executePostRequest(Session session, Map<String, Object> record, String type) {
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
