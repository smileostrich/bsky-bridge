package com.ian.bskyapp.entity.dto;

import org.jetbrains.annotations.NotNull;

public record User (
        @NotNull String identifier,
        @NotNull String password) {
}
