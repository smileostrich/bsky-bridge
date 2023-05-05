package com.ian.bskyapp.entity;

import java.util.List;

public record Embed(
        String $type,
        Record record,
        External external,
        List<Images> images) {
}
