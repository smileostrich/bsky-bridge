package com.ian.bskyapp.entity;

import java.time.LocalDateTime;
import java.util.List;

public record Record(
        String text,
        String $type,
        Reply reply,
        List<Facet> facets,
        LocalDateTime createdAt) {
}
