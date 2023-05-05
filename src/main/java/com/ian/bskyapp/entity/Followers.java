package com.ian.bskyapp.entity;

import java.util.List;

public record Followers(
        Actor subject,
        List<Actor> followers,
        String cursor) {
}
