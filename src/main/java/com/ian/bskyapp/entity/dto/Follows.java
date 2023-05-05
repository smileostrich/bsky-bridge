package com.ian.bskyapp.entity.dto;

import com.ian.bskyapp.entity.Actor;

import java.util.List;

public record Follows(
        Actor subject,
        List<Actor> follows,
        String cursor) {
}
