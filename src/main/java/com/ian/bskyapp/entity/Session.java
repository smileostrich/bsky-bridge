package com.ian.bskyapp.entity;

public record Session(
        String did,
        String handle,
        Jwt jwt) {
}
