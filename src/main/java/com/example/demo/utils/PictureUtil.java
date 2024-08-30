package com.example.demo.utils;

public class PictureUtil {
    private static final String prefix = "http://203.195.195.64:8080/huang/demoFile";

    public static String addPrefix(String address){
        if (StringUtils.isNotBlank(address)){
            return prefix + address;
        }
        return null;
    }

    public static String removeBaseUrl(String originalUrl) {
        if (originalUrl == null) {
            return originalUrl; // 返回原始字符串
        }

        if (originalUrl.startsWith(prefix)) {
            // 获取 baseUrl 的长度
            int baseLength = prefix.length();

            // 截取剩余的部分
            return originalUrl.substring(baseLength);
        } else {
            return originalUrl; // 不包含 baseUrl，返回原始字符串
        }
    }


}
