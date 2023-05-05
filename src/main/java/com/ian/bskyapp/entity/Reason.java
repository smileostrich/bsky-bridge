package com.ian.bskyapp.entity;

import java.time.LocalDateTime;

public record Reason(
        String $type,
        Author by,
        LocalDateTime indexedAt) {
}
