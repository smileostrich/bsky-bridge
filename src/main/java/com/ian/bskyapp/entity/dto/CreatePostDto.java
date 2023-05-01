package com.ian.bskyapp.entity.dto;

import org.jetbrains.annotations.NotNull;

public record CreatePostDto(
        @NotNull String text) {
}
