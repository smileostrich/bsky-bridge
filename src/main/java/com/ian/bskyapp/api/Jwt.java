package com.ian.bskyapp.api;

public record Jwt(
        String access,
        String refresh) {
}
