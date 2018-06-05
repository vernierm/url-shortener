package com.marin.urlshortener.service;

import java.util.Random;

public class RandomStringUtils {

    private static final Random random = new Random();
    private static char[] allowedChars;

    static{
        StringBuilder allowed = new StringBuilder();
        for (char i = 'A'; i <= 'Z'; ++i) {
            allowed.append(i);
        }
        for (char i = 'a'; i <= 'z'; ++i) {
            allowed.append(i);
        }
        for (char i = '0'; i <= '9'; ++i) {
            allowed.append(i);
        }
        allowedChars = allowed.toString().toCharArray();
    }

    public static String generateRandomString(int length) {
        StringBuilder passwordBuilder = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            passwordBuilder.append(allowedChars[random.nextInt(allowedChars.length)]);
        }

        return passwordBuilder.toString();
    }
}
