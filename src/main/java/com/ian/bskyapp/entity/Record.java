package com.ian.bskyapp.entity;

import java.time.LocalDateTime;
import java.util.List;

public record Record(
        String cid,
        String uri,
        Author author,
        Value value,
        String text,
        String $type,
        Reply reply,
        Embed embed,
        List<Embed> embeds,
        List<Facet> facets,
        List<Label> labels,
        LocalDateTime createdAt,
        LocalDateTime indexedAt) {
}
