package com.example.demo.utils;

public class PictureUtil {
    private static final String prefix = "http://203.195.195.64:8080/huang/demoFile";

    public static String addPrefix(String address){
        if (StringUtils.isNotBlank(address)){
            return prefix + address;
        }
        return null;
    }
}
