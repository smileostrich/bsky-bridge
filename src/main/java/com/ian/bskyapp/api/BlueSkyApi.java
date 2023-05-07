package com.ian.bskyapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ian.bskyapp.entity.Session;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

import static com.ian.bskyapp.util.HttpUtil.parseHttpUrl;

@Component
public class BlueSkyApi {

    private final ObjectMapper objectMapper;

    private static final String HEADER_AUTH = "Authorization";

    public BlueSkyApi(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T, P extends GetParam> T executeGetRequest(Session session, P requestParams, Class<T> responseType) {
        HttpUrl url = parseHttpUrl(requestParams.path(), requestParams.queryParams());

        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTH, "Bearer %s".formatted(session.jwt().access()))
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.code() == 400)
                throw new IllegalStateException("API error: " + response.body().string());

            return objectMapper.readValue(response.body().string(), responseType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <P extends PostParam> void executePostRequest(Session session, P requestParams) {
        HttpUrl url = parseHttpUrl(requestParams.path(), Collections.emptyMap());

        RequestBody body;
        try {
            body = RequestBody.create(objectMapper.writeValueAsString(requestParams.queryParams()), MediaType.get("application/json"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTH, "Bearer %s".formatted(session.jwt().access()))
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
