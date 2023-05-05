package com.ian.bskyapp.service;

import com.ian.bskyapp.entity.Session;
import com.ian.bskyapp.entity.Feeds;
import com.ian.bskyapp.entity.Likes;
import com.ian.bskyapp.entity.StrongRef;

public interface BlueSkyApiService {

    void createPost(Session session, String text);

    void repost(Session session, StrongRef strongRef);

    void like(Session session, StrongRef strongRef);

    void follow(Session session, String did);

    Feeds getAuthorFeed(Session session);

    Feeds getTimeLine(Session session);

    Likes getLikes(Session session, String uri);

}
