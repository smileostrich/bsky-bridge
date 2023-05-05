package com.ian.bskyapp.entity;

public record Feed(
        PostView post,
        Reason reason,
        Reply reply) {
}
