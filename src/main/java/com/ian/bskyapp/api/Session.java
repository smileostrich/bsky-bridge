package com.ian.bskyapp.api;

public record Session(
        String did,
        String handle,
        Jwt jwt) {
}
