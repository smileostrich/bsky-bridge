package com.ian.bskyapp.service;

import com.ian.bskyapp.entity.*;
import com.ian.bskyapp.entity.dto.Followers;
import com.ian.bskyapp.entity.dto.Follows;

import java.util.Optional;

public interface BlueSkyApiService {

    void createPost(Session session, String text);

    void repost(Session session, StrongRef strongRef);

    void like(Session session, StrongRef strongRef);

    void follow(Session session, String did);

    void block(Session session, String did);

    void deletePost(Session session, String uri);

    Followers getFollowers(Session session, Optional<String> did, Optional<Integer> limit, Optional<String> cursor);

    Follows getFollows(Session session, Optional<String> did, Optional<Integer> limit, Optional<String> cursor);

    Feeds getAuthorFeed(Session session, Optional<String> did, Optional<Integer> limit, Optional<String> cursor);

    Feeds getTimeLine(Session session, Optional<String> algorithm, Optional<Integer> limit, Optional<String> cursor);

    Likes getLikes(Session session, Optional<String> uri, Optional<Integer> limit, Optional<String> cursor);

}
