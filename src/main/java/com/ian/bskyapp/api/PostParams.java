package com.ian.bskyapp.api;

import java.util.Map;

public sealed interface PostParams permits PostDefaultParams {

    String path();
    Map<String, Object> queryParams();

}