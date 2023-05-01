package com.ian.bskyapp.service.impl;

import com.ian.bskyapp.api.Session;
import com.ian.bskyapp.entity.AuthorFeed;
import com.ian.bskyapp.service.BlueSkyApiService;
import okhttp3.*;
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
    private static final String API_FEED_POST = "app.bsky.feed.post";

    public AuthorFeed getAuthorFeed(Session session) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(HTTPS)
                .host(HOST)
                .addPathSegments("xrpc/app.bsky.feed.getAuthorFeed")
                .addEncodedQueryParameter("actor", session.did())
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer %s".formatted(session.jwt().access()))
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.code() == 400)
                throw new IllegalStateException("API error: " + response.body().string());

            return objectMapperWithDate().readValue(response.body().string(), AuthorFeed.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPost(Session session, String text) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(HTTPS)
                .host(HOST)
                .addPathSegments("xrpc/com.atproto.repo.createRecord")
                .build();

        Map<String, String> record = Map.of(
                "$type", API_FEED_POST,
                "createdAt", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                "text", text
        );

        Map<String, Object> map = Map.of(
                "collection", API_FEED_POST,
                "$type", API_FEED_POST,
                "repo", session.did(),
                "record", record
        );

        RequestBody body;
        try {
            body = RequestBody.create(objectMapperWithDate()
                    .writeValueAsString(map),MediaType.get("application/json"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer %s".formatted(session.jwt().access()))
                .post(body)
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.code() == 400)
                throw new IllegalStateException("API error: " + response.body().string());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
