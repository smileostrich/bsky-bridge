package com.ian.bskyapp.entity;

import java.time.LocalDateTime;

public record Label(
        String src,
        String uri,
        String val,
        boolean neg,
        LocalDateTime cts) {
}
