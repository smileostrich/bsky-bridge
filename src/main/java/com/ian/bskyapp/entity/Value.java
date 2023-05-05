package com.ian.bskyapp.entity;

import java.time.LocalDateTime;
import java.util.List;

public record Value(
        String text,
        String $type,
        Embed embed,
        LocalDateTime createdAt,
        List<Facet> facets,
        Reply reply) {
}
