package com.ian.bskyapp.service;

import com.ian.bskyapp.entity.*;
import com.ian.bskyapp.entity.dto.Followers;
import com.ian.bskyapp.entity.dto.Follows;

public interface BlueSkyApiService {

    void createPost(Session session, String text);

    void repost(Session session, StrongRef strongRef);

    void like(Session session, StrongRef strongRef);

    void follow(Session session, String did);

    Followers getFollowers(Session session);

    Follows getFollows(Session session);

    Feeds getAuthorFeed(Session session);

    Feeds getTimeLine(Session session);

    Likes getLikes(Session session, String uri);

}
