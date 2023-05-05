package com.ian.bskyapp.entity;

public record Reply(
        PostView root,
        String handle,
        PostView parent) {
}
