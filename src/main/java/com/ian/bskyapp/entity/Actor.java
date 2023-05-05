package com.ian.bskyapp.entity;

import java.time.LocalDateTime;
import java.util.List;

public record Actor(
        String did,
        String handle,
        String displayName,
        String description,
        String avatar,
        LocalDateTime indexedAt,
        Viewer viewer,
        List<Label> labels) {
}
