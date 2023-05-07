package com.ian.bskyapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ian.bskyapp.entity.CreateSession;
import com.ian.bskyapp.entity.Jwt;
import com.ian.bskyapp.entity.Session;
import com.ian.bskyapp.entity.dto.User;
import com.ian.bskyapp.service.UserService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    private final ObjectMapper objectMapper;

    public UserServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Session login(User user) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("bsky.social")
                .addPathSegments("xrpc/com.atproto.server.createSession")
                .build();

        RequestBody body;
        try {
            body = RequestBody.create(
                    objectMapper.writeValueAsString(user),
                    MediaType.get("application/json")
            );
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            CreateSession session = objectMapper
                    .readValue(response.body().charStream(), CreateSession.class);

            Jwt jwt = new Jwt(session.accessJwt(), session.refreshJwt());

            return new Session(session.did(), session.handle(), jwt);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
