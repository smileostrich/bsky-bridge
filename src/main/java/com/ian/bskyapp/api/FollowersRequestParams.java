package com.ian.bskyapp.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record FollowersRequestParams(Optional<String> did, Optional<Integer> limit, Optional<String> cursor) implements ApiRequestParams {

    @Override
    public String path() {
        return "xrpc/app.bsky.graph.getFollowers";
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
