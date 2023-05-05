package com.ian.bskyapp.entity;

import java.util.List;

public record Feeds(
        List<Feed> feed,
        String cursor) {
}
