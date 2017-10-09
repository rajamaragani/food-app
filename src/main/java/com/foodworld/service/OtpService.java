/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.service;

import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.foodworld.utils.FoodAppEncry;
import com.foodworld.utils.Util;

@Service
public class OtpService implements IOtpService {

    @Value("${otp.validInMin}")
    private int validInMin;

    @Autowired
    FoodAppEncry foodAppEncry;

    @Override
    public String sendOtp(String phoneNumber) {

        String otp = "1234";// Util.generateOTP();
        String s = "Thank You for choosing app name, Enter " + otp + " for registration";

        return foodAppEncry.encrypt(otp + "," + Util.getStringLocalTime() + "," + phoneNumber);
    }

    @Override
    public boolean verifyOtp(String phoneNumber, String timestamp, String otp) {
        String decoded = new String(foodAppEncry.decrypt(timestamp));
        String[] s = decoded.split(",");
        if (s.length == 3) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime fromDate = Util.stringToLocalDateTime(s[1]);
            Seconds secondsBetween = Seconds.secondsBetween(fromDate, now);
            int minutes = secondsBetween.toStandardMinutes().getMinutes();
            if (minutes <= validInMin && s[0].equals(otp) && s[2].equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

}
