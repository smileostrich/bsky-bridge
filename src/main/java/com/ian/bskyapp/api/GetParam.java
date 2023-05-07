package com.ian.bskyapp.api;

import java.util.Map;

public sealed interface GetParam permits FollowersRequestParam, FollowsRequestParam, AuthorFeedRequestParam, TimeLineRequestParam, LikesRequestParam {

    String path();
    Map<String, String> queryParams();

}
