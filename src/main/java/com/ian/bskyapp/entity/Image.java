package com.ian.bskyapp.entity;

public record Image (
        String $type,
        Ref ref,
        String mimeType,
        Integer size) {
}
