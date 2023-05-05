package com.ian.bskyapp.service;

import com.ian.bskyapp.api.Session;
import com.ian.bskyapp.entity.Feeds;

public interface BlueSkyApiService {

    Feeds getAuthorFeed(Session session);

    void createPost(Session session, String text);

    Feeds getTimeLine(Session session);

}
