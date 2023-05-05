package com.ian.bskyapp.entity;

import java.util.List;

public record Facet(
        Index index,
        String $type,
        List<Feature> features) {
}
