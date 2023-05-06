package com.ian.bskyapp.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record FollowsRequestParams(Optional<String> did, Optional<Integer> limit, Optional<String> cursor) implements GetParams {

    @Override
    public String path() {
        return "xrpc/app.bsky.graph.getFollows";
    }

    @Override
    public Map<String, String> queryParams() {
        Map<String, String> queryParams = new HashMap<>();
        did.ifPresent(d -> queryParams.put("actor", d));
        limit.ifPresent(l -> queryParams.put("limit", l.toString()));
        cursor.ifPresent(c -> queryParams.put("cursor", c));

        return queryParams;
    }

}