package com.ian.bskyapp.api;

import java.util.HashMap;
import java.util.Map;

public record PostDefaultParams(String type, String did, Object record) implements PostParams {

    @Override
    public String path() {
        return "xrpc/com.atproto.repo.createRecord";
    }

    @Override
    public Map<String, Object> queryParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("collection", type);
        queryParams.put("$type", type);
        queryParams.put("repo", did);
        queryParams.put("record", record);

        return queryParams;
    }

}
