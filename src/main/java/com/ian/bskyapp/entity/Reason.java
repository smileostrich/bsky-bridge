package com.ian.bskyapp.entity;

import java.time.ZonedDateTime;

public record Reason(
        String $type,
        Author by,
        ZonedDateTime indexedAt) {
}
