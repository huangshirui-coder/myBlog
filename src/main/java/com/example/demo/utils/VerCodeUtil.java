package com.example.demo.utils;

import java.util.Random;

public class VerCodeUtil {
    private static final String STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWSYZ";
    private static final Random random = new Random();

    public static String getVerCode() {
        char[] verCode = new char[6];
        for (int i = 0; i < 6; i++) {
            verCode[i] = STRING.charAt(random.nextInt(STRING.length()));
        }
        return new String(verCode);
    }
}
