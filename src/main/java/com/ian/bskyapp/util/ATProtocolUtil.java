package com.ian.bskyapp.util;

public class ATProtocolUtil {

    public static final String AT_PROTOCOL = "at://";
    public static final String DELIMITER = "/";

    public static String getAtpUri(String... parameters) {
        return AT_PROTOCOL + String.join(DELIMITER, parameters);
    }

    public static String parseRKey(String uri) {
        return uri.substring(uri.lastIndexOf("/") + 1);
    }

}
