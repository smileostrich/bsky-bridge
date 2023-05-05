package com.ian.bskyapp.entity.dto;

import com.ian.bskyapp.entity.Actor;

import java.util.List;

public record Followers(
        Actor subject,
        List<Actor> followers,
        String cursor) {
}
