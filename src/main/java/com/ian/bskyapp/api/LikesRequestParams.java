package com.ian.bskyapp.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record LikesRequestParams(Optional<String> uri, Optional<Integer> limit, Optional<String> cursor) implements GetParams {

    @Override
    public String path() {
        return "xrpc/app.bsky.feed.getLikes";
    }

    @Override
    public Map<String, String> queryParams() {
        Map<String, String> queryParams = new HashMap<>();
        uri.ifPresent(u -> queryParams.put("uri", u));
        limit.ifPresent(l -> queryParams.put("limit", l.toString()));
        cursor.ifPresent(c -> queryParams.put("cursor", c));

        return queryParams;
    }

}