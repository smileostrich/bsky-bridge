package com.ian.bskyapp.service.impl;

import com.ian.bskyapp.api.*;
import com.ian.bskyapp.entity.Feeds;
import com.ian.bskyapp.entity.Likes;
import com.ian.bskyapp.entity.Session;
import com.ian.bskyapp.entity.StrongRef;
import com.ian.bskyapp.entity.dto.Followers;
import com.ian.bskyapp.entity.dto.Follows;
import com.ian.bskyapp.service.BlueSkyApiService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Service
public class BlueSkyApiServiceImpl implements BlueSkyApiService {

    private final BlueSkyApi blueSkyApi;

    private static final String API_FEED_POST = "app.bsky.feed.post";
    private static final String API_FEED_REPOST = "app.bsky.feed.repost";
    private static final String API_FEED_LIKE = "app.bsky.feed.like";
    private static final String API_GRAPH_FOLLOW = "app.bsky.graph.follow";
    private static final String API_GRAPH_BLOCK = "app.bsky.graph.block";

    public BlueSkyApiServiceImpl(BlueSkyApi blueSkyApi) {
        this.blueSkyApi = blueSkyApi;
    }

    public void createPost(Session session, String text) {
        blueSkyApi.executePostRequest(session,
                new PostDefaultParams(API_FEED_POST, session.did(), parseRecord(API_FEED_POST, "text", text)));
    }

    public void repost(Session session, StrongRef strongRef) {
        blueSkyApi.executePostRequest(session,
                new PostDefaultParams(API_FEED_REPOST, session.did(), parseRecord(API_FEED_REPOST, "subject", strongRef)));
    }

    public void like(Session session, StrongRef strongRef) {
        blueSkyApi.executePostRequest(session,
                new PostDefaultParams(API_FEED_LIKE, session.did(), parseRecord(API_FEED_LIKE, "subject", strongRef)));
    }

    public void follow(Session session, String did) {
        blueSkyApi.executePostRequest(session,
                new PostDefaultParams(API_GRAPH_FOLLOW, session.did(), parseRecord(API_GRAPH_FOLLOW, "subject", did)));
    }

    public void block(Session session, String did) {
        blueSkyApi.executePostRequest(session,
                new PostDefaultParams(API_GRAPH_BLOCK, session.did(), parseRecord(API_GRAPH_BLOCK, "subject", did)));
    }

    public Followers getFollowers(Session session, Optional<String> did, Optional<Integer> limit, Optional<String> cursor) {
        return blueSkyApi.executeGetRequest(session, new FollowersRequestParams(did, limit, cursor), Followers.class);
    }

    public Follows getFollows(Session session, Optional<String> did, Optional<Integer> limit, Optional<String> cursor) {
        return blueSkyApi.executeGetRequest(session, new FollowsRequestParams(did, limit, cursor), Follows.class);
    }

    public Feeds getAuthorFeed(Session session, Optional<String> did, Optional<Integer> limit, Optional<String> cursor) {
        return blueSkyApi.executeGetRequest(session, new AuthorFeedRequestParams(did, limit, cursor), Feeds.class);
    }

    public Feeds getTimeLine(Session session, Optional<String> algorithm, Optional<Integer> limit, Optional<String> cursor) {
        return blueSkyApi.executeGetRequest(session, new TimeLineRequestParams(algorithm, limit, cursor, session.did()), Feeds.class);
    }

    public Likes getLikes(Session session, Optional<String> uri, Optional<Integer> limit, Optional<String> cursor) {
        return blueSkyApi.executeGetRequest(session, new LikesRequestParams(uri, limit, cursor), Likes.class);
    }

    private Map<String, Object> parseRecord(String type, String key, Object value) {
        return Map.of(
                "$type", type,
                key, value,
                "createdAt", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
    }

}
