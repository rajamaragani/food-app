/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.foodworld.restclient.RestClient;
import com.foodworld.utils.FoodAppEncry;
import com.foodworld.utils.Util;

@Service
public class OtpService implements IOtpService {

    @Value("${otp.validInMin}")
    private int validInMin;

    @Value("${otp.url}")
    private String url;

    @Value("${otp.balance}")
    private String balanceURL;

    @Value("#{'${otp.adminPhoneNumbers}'.split(',')}")
    private List<String> phoneNumbersList;

    @Autowired
    FoodAppEncry foodAppEncry;

    @Autowired
    RestClient restClient;

    @Override
    public String sendOtp(String phoneNumber) {
        String otp = Util.generateOTP();
        String otp_message = "Thank you for choosing Meals on Wheels, Enter " + otp + " for Order Place.";
        phoneNumbersList.add(phoneNumber);
        url = url.replace("_TO", StringUtils.join(phoneNumbersList, ',')).replace("_MESSAGE", otp_message);
        String result = restClient.getService(url);
        if (result.contains("Messages has been sent")) {
            return foodAppEncry.encrypt(otp + "," + Util.getStringLocalTime() + "," + phoneNumber);
        } else {
            return null;
        }
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

    @Override
    public String getSMSBalance() {
        return restClient.getService(balanceURL);
    }

}
