package org.e2e.utils;

import java.util.HashMap;
import java.util.Map;

public class UrlUtils {
    public static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("\\?")[1].split("&");
        Map<String, String> map = new HashMap<String, String>();

        for (String param : params) {
            if (!param.isBlank()) {
                String name = param.split("=")[0];
                String value = param.split("=")[1];
                map.put(name, value);
            }
        }
        return map;
    }

    public static String getQueryParam(String url, String queryParam) {
        return getQueryMap(url).getOrDefault(queryParam, "");
    }
}
