package com.atul.ttt.utils;

import java.util.ArrayList;

public class Preconditions {

    public static void validateGreater(int x, int threshold) {
        if (x < threshold) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateSmaller(int x, int threshold) {
        if (x > threshold) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateNotNull(Object field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void validatePhoneNumber(String phone) {
        if (!phone.matches("[0-9]{10}")) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateEmail(String email) {
        if (!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateIsTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
