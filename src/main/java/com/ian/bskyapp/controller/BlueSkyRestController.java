package com.ian.bskyapp.controller;

import com.ian.bskyapp.entity.Feeds;
import com.ian.bskyapp.entity.Likes;
import com.ian.bskyapp.entity.Session;
import com.ian.bskyapp.entity.StrongRef;
import com.ian.bskyapp.entity.dto.*;
import com.ian.bskyapp.service.BlueSkyApiService;
import com.ian.bskyapp.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.ian.bskyapp.util.ATProtocolUtil.getAtpUri;
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

    @PostMapping("/refresh")
    public Session refreshSession(@RequestHeader HttpHeaders headers) {
        return userService.refreshSession(parseHeader(headers));
    }

    @PostMapping("/post")
    public void createPost(@RequestHeader HttpHeaders headers, @RequestBody CreatePostDto dto) {
        blueSkyApiService.createPost(parseHeader(headers), dto.text());
    }

    @PostMapping("/repost")
    public void repost(@RequestHeader HttpHeaders headers, @RequestBody StrongRef strongRef) {
        blueSkyApiService.repost(parseHeader(headers), strongRef);
    }

    @PostMapping("/like")
    public void like(@RequestHeader HttpHeaders headers, @RequestBody StrongRef strongRef) {
        blueSkyApiService.like(parseHeader(headers), strongRef);
    }

    @PostMapping("/follow")
    public void follow(@RequestHeader HttpHeaders headers, @RequestBody Did dto) {
        blueSkyApiService.follow(parseHeader(headers), dto.did());
    }

    @PostMapping("/block")
    public void block(@RequestHeader HttpHeaders headers, @RequestBody Did dto) {
        blueSkyApiService.block(parseHeader(headers), dto.did());
    }

    @DeleteMapping("/post")
    public void deletePost(@RequestHeader HttpHeaders headers, @RequestBody DeletePostDto dto) {
        blueSkyApiService.deletePost(parseHeader(headers), dto.rkey());
    }

    @GetMapping("/followers")
    public Followers getFollowers(@RequestHeader HttpHeaders headers,
                                  @RequestParam(value = "did", required = false) Optional<String> did,
                                  @RequestParam(value = "limit", required = false) Optional<Integer> limit,
                                  @RequestParam(value = "cursor", required = false) Optional<String> cursor) {
        Session session = parseHeader(headers);

        if (did.isEmpty())
            did = Optional.of(session.did());

        return blueSkyApiService.getFollowers(session, did, limit, cursor);
    }

    @GetMapping("/follows")
    public Follows getFollows(@RequestHeader HttpHeaders headers,
                              @RequestParam(value = "did", required = false) Optional<String> did,
                              @RequestParam(value = "limit", required = false) Optional<Integer> limit,
                              @RequestParam(value = "cursor", required = false) Optional<String> cursor) {
        Session session = parseHeader(headers);

        if (did.isEmpty())
            did = Optional.of(session.did());

        return blueSkyApiService.getFollows(parseHeader(headers), did, limit, cursor);
    }

    @GetMapping("/author-feed")
    public Feeds getAuthorFeed(@RequestHeader HttpHeaders headers,
                               @RequestParam(value = "did", required = false) Optional<String> did,
                               @RequestParam(value = "limit", required = false) Optional<Integer> limit,
                               @RequestParam(value = "cursor", required = false) Optional<String> cursor) {
        Session session = parseHeader(headers);

        if (did.isEmpty())
            did = Optional.of(session.did());

        return blueSkyApiService.getAuthorFeed(parseHeader(headers), did, limit, cursor);
    }

    @GetMapping("/timeline")
    public Feeds getTimeline(@RequestHeader HttpHeaders headers,
                             @RequestParam(value = "algorithm", required = false) Optional<String> algorithm,
                             @RequestParam(value = "limit", required = false) Optional<Integer> limit,
                             @RequestParam(value = "cursor", required = false) Optional<String> cursor) {
        return blueSkyApiService.getTimeLine(parseHeader(headers), algorithm, limit, cursor);
    }

    @GetMapping("/did/{did}/post/{post}/likes")
    public Likes getLikes(@RequestHeader HttpHeaders headers,
                          @RequestParam(value = "limit", required = false) Optional<Integer> limit,
                          @RequestParam(value = "cursor", required = false) Optional<String> cursor,
                          @PathVariable String did, @PathVariable String post) {
        return blueSkyApiService.getLikes(parseHeader(headers),
                Optional.of(getAtpUri(did, "app.bsky.feed.post", post)),
                limit,
                cursor);
    }

}
