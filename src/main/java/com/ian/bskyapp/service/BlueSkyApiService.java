package com.ian.bskyapp.service;

import com.ian.bskyapp.api.Session;
import com.ian.bskyapp.entity.AuthorFeed;

public interface BlueSkyApiService {

    AuthorFeed getAuthorFeed(Session session);

    void createPost(Session session, String text);

}
