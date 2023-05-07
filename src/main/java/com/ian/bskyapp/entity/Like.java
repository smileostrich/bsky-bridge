package com.ian.bskyapp.entity;

import java.time.ZonedDateTime;

public record Like(
        ZonedDateTime createdAt,
        ZonedDateTime indexedAt,
        Actor actor) {
}
