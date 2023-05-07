package com.ian.bskyapp.api;

import java.util.Map;

public sealed interface PostParam permits PostDefaultParam, DeleteDefaultParam {

    String path();
    Map<String, Object> queryParams();

}