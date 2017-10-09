/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.utils;

import java.util.Random;
import java.util.UUID;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Util {

    static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");

    public static String generateUUID() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String generateOTP() {
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }

    public static String getStringLocalTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.toString(formatter);
    }

    public static LocalDateTime stringToLocalDateTime(String localDateTime) {
        return LocalDateTime.parse(localDateTime, formatter);
    }
}
