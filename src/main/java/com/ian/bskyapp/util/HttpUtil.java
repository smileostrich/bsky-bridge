package com.ian.bskyapp.util;

import com.ian.bskyapp.api.Jwt;
import com.ian.bskyapp.api.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

public class HttpUtil {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final String HEADER_DID = "Did";
    private static final String HEADER_HANDLE = "Handle";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_REFRESH = "RefreshAuthorization";

    public static String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX))
            return bearerToken.substring(7);

        throw new IllegalArgumentException("Invalid bearer token");
    }

    public static Session parseHeader(HttpHeaders headers) {
        return new Session(
                headers.getFirst(HEADER_DID),
                headers.getFirst(HEADER_HANDLE),
                new Jwt(resolveToken(headers.getFirst(HEADER_AUTHORIZATION)),
                        resolveToken(headers.getFirst(HEADER_REFRESH)))
        );
    }

}
