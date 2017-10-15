/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.service;

public interface IOtpService {

    /**
     * This method for used for send otp to mobile number
     */
    String sendOtp(String phoneNumber);

    /**
     * This method used for verify the otp
     * 
     * @param phoneNumber
     * @param timestamp
     * @param otp
     * @return
     */
    boolean verifyOtp(String phoneNumber, String timestamp, String otp);

    String getSMSBalance();

}
