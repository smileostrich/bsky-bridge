package com.ian.bskyapp.entity;

import java.time.LocalDateTime;
import java.util.List;

public record PostView(
        String uri,
        String cid,
        Author author,
        Embed embed,
        Record record,
        Integer replyCount,
        Integer repostCount,
        Integer likeCount,
        LocalDateTime indexedAt,
        Viewer viewer,
        List<Label> labels) {
}
