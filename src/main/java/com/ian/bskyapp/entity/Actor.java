package com.ian.bskyapp.entity;

import java.time.ZonedDateTime;
import java.util.List;

public record Actor(
        String did,
        String handle,
        String displayName,
        String description,
        String avatar,
        ZonedDateTime indexedAt,
        Viewer viewer,
        List<Label> labels) {
}
