package com.ian.bskyapp.entity;

import java.time.LocalDateTime;
import java.util.List;

public record PostView(
        String uri,
        String cid,
        Author author,
        Record record,
        int replyCount,
        int repostCount,
        int likeCount,
        LocalDateTime indexedAt,
        Viewer viewer,
        List<Label> labels) {
}
