package com.ian.bskyapp.service;

import com.ian.bskyapp.api.Session;
import com.ian.bskyapp.entity.Feeds;
import com.ian.bskyapp.entity.Likes;

public interface BlueSkyApiService {

    Feeds getAuthorFeed(Session session);

    void createPost(Session session, String text);

    Feeds getTimeLine(Session session);

    Likes getLikes(Session session, String uri);

}
