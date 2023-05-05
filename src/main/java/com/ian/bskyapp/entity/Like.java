package com.ian.bskyapp.entity;

import java.time.LocalDateTime;

public record Like(
        LocalDateTime createdAt,
        LocalDateTime indexedAt,
        Actor actor) {
}
