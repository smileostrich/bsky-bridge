package com.ian.bskyapp.controller;

import com.ian.bskyapp.api.Session;
import com.ian.bskyapp.entity.Feeds;
import com.ian.bskyapp.entity.dto.User;
import com.ian.bskyapp.entity.dto.CreatePostDto;
import com.ian.bskyapp.service.BlueSkyApiService;
import com.ian.bskyapp.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static com.ian.bskyapp.util.HttpUtil.parseHeader;

@RestController
public class BlueSkyRestController {

    private final BlueSkyApiService blueSkyApiService;
    private final UserService userService;

    public BlueSkyRestController(BlueSkyApiService blueSkyApiService, UserService userService) {
        this.blueSkyApiService = blueSkyApiService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Session getLogin(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/post")
    public void createPost(@RequestHeader HttpHeaders headers, @RequestBody CreatePostDto dto) {
        blueSkyApiService.createPost(parseHeader(headers), dto.text());
    }

    @GetMapping("/author-feed")
    public Feeds getAuthorFeed(@RequestHeader HttpHeaders headers) {
        return blueSkyApiService.getAuthorFeed(parseHeader(headers));
    }

    @GetMapping("/timeline")
    public Feeds getTimeline(@RequestHeader HttpHeaders headers) {
        return blueSkyApiService.getTimeLine(parseHeader(headers));
    }

    // for poc virtual thread
    @GetMapping("/where-am-i")
    String getThreadName() {
        return Thread.currentThread().toString();
    }

}
