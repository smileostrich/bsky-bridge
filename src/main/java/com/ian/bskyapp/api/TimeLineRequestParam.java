package com.ian.bskyapp.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record TimeLineRequestParam(Optional<String> algorithm, Optional<Integer> limit, Optional<String> cursor, String actor) implements GetParam {

    @Override
    public String path() {
        return "xrpc/app.bsky.feed.getTimeline";
    }

    @Override
    public Map<String, String> queryParams() {
        Map<String, String> queryParams = new HashMap<>();
        algorithm.ifPresent(a -> queryParams.put("algorithm", a));
        limit.ifPresent(l -> queryParams.put("limit", l.toString()));
        cursor.ifPresent(c -> queryParams.put("cursor", c));
        queryParams.put("actor", actor);

        return queryParams;
    }

}
