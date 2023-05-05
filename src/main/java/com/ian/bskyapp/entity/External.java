package com.ian.bskyapp.entity;

public record External(
        String uri,
        String title,
        String description,
        Object thumb) {
}
