package com.ian.bskyapp.entity;

public record Viewer(
        Boolean muted,
        Boolean blockedBy,
        String following,
        String followedBy,
        String like) {
}
