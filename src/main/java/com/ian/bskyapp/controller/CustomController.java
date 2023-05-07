package com.ian.bskyapp.controller;

import com.ian.bskyapp.entity.Feeds;
import com.ian.bskyapp.entity.Session;
import com.ian.bskyapp.entity.dto.OrganizePostsDto;
import com.ian.bskyapp.service.BlueSkyApiService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.ian.bskyapp.util.ATProtocolUtil.parseRKey;
import static com.ian.bskyapp.util.HttpUtil.parseHeader;

@RestController
public class CustomController {

    private final BlueSkyApiService blueSkyApiService;

    public CustomController(BlueSkyApiService blueSkyApiService) {
        this.blueSkyApiService = blueSkyApiService;
    }

    @DeleteMapping("/organize/posts")
    public void organizePosts(@RequestHeader HttpHeaders headers, @RequestBody OrganizePostsDto dto) {
        Session session = parseHeader(headers);
        Feeds feeds = blueSkyApiService.getAuthorFeed(session, Optional.ofNullable(session.did()), Optional.of(100), Optional.empty());

        feeds.feed().stream()
                .filter(feed -> feed.post().record().createdAt().isAfter(dto.from()))
                .forEach(feed -> blueSkyApiService.deletePost(session, parseRKey(feed.post().uri())));
    }

}
