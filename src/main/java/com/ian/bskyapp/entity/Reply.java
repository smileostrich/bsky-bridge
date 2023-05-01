package com.ian.bskyapp.entity;

public record Reply(
        PostView root,
        PostView parent) {
}
