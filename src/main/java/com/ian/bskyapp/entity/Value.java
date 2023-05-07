package com.ian.bskyapp.entity;

import java.time.ZonedDateTime;
import java.util.List;

public record Value(
        String text,
        String $type,
        Embed embed,
        ZonedDateTime createdAt,
        List<Facet> facets,
        Reply reply) {
}
