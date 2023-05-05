package com.ian.bskyapp.entity;

import java.util.List;

public record Media(
        String $type,
        List<Images> images) {
}
