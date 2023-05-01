package com.ian.bskyapp.entity;

import java.util.List;

public record AuthorFeed(
        List<FeedViewPost> feed,
        String cursor) {
}
