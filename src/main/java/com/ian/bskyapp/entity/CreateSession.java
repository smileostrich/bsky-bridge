package com.ian.bskyapp.entity;

public record CreateSession(
        String did,
        String email,
        String handle,
        String accessJwt,
        String refreshJwt) {
}
