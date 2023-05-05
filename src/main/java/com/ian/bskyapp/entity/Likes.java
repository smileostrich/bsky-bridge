package com.ian.bskyapp.entity;

import java.util.List;

public record Likes(
        String uri,
        String cursor,
        List<Like> likes) {
}
