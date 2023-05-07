package com.ian.bskyapp.api;

import java.util.HashMap;
import java.util.Map;

public record DeleteDefaultParam(String type, String did, Object rkey) implements PostParam {

    @Override
    public String path() {
        return "xrpc/com.atproto.repo.deleteRecord";
    }

    @Override
    public Map<String, Object> queryParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("collection", type);
        queryParams.put("$type", type);
        queryParams.put("repo", did);
        queryParams.put("rkey", rkey);

        return queryParams;
    }

}
