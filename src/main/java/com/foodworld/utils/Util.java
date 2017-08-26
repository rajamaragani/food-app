package com.foodworld.utils;

import java.util.UUID;

public class Util {

    public static String generateUUID() {
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
