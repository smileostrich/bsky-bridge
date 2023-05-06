package com.ian.bskyapp.api;

import java.util.Map;

public sealed interface GetParams permits FollowersRequestParams, FollowsRequestParams, AuthorFeedRequestParams, TimeLineRequestParams, LikesRequestParams {

    String path();
    Map<String, String> queryParams();

}
