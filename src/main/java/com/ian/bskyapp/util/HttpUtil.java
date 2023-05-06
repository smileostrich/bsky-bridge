package com.ian.bskyapp.util;

import com.ian.bskyapp.entity.Jwt;
import com.ian.bskyapp.entity.Session;
import okhttp3.HttpUrl;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.util.Map;

public class HttpUtil {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final String HEADER_DID = "Did";
    private static final String HEADER_HANDLE = "Handle";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_REFRESH = "RefreshAuthorization";
    private static final String HOST = "bsky.social";
    private static final String HTTPS = "https";

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
                        headers.get(HEADER_REFRESH) != null ? resolveToken(headers.getFirst(HEADER_REFRESH)) : null)
        );
    }

    public static HttpUrl parseHttpUrl(String pathSegments, Map<String, String> queryParams) {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme(HTTPS)
                .host(HOST)
                .addPathSegments(pathSegments);

        for (Map.Entry<String, String> entry : queryParams.entrySet())
            urlBuilder.addEncodedQueryParameter(entry.getKey(), entry.getValue());

        return urlBuilder.build();
    }

}
