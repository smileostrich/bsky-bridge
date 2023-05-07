package com.ian.bskyapp.entity;

import java.time.ZonedDateTime;

public record Label(
        String src,
        String uri,
        String val,
        boolean neg,
        ZonedDateTime cts) {
}
