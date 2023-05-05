package com.ian.bskyapp.service;

import com.ian.bskyapp.api.Session;
import com.ian.bskyapp.entity.Feeds;
import com.ian.bskyapp.entity.Likes;
import com.ian.bskyapp.entity.StrongRef;

public interface BlueSkyApiService {

    void createPost(Session session, String text);

    void like(Session session, StrongRef strongRef);

    Feeds getAuthorFeed(Session session);

    Feeds getTimeLine(Session session);

    Likes getLikes(Session session, String uri);

}
