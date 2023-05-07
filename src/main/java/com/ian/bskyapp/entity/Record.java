package com.ian.bskyapp.entity;

import java.time.ZonedDateTime;
import java.util.List;

public record Record(
        String cid,
        String uri,
        Author author,
        Value value,
        String text,
        String $type,
        Reply reply,
        Record record,
        Embed embed,
        List<Embed> embeds,
        List<Facet> facets,
        List<Label> labels,
        Unofficial unofficial,
        Viewer viewer,
        Integer likeCount,
        Integer replyCount,
        Integer repostCount,
        ZonedDateTime createdAt,
        ZonedDateTime indexedAt) {
}
