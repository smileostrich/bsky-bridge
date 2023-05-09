package com.ian.bskyapp.entity;

public record RefreshSession(
        String accessJwt,
        String refreshJwt,
        String handle,
        String did) {
}
