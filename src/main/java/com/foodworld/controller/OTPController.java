/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodworld.pojo.FoodAppResponse;
import com.foodworld.pojo.User;
import com.foodworld.service.IOtpService;
import com.foodworld.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/foodworld/otp", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api("This API call for the manage the OTP's")
public class OTPController {

    private static final String MOBILEREGEX = "^?[789]\\d{9}$";
    @Autowired
    IOtpService otpService;

    @Value("${otp.identity}")
    private String otpIdentity;
    
    @Autowired
    IUserService userService;
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OTPController.class);

    @RequestMapping(value = "/user/generate/{phoneNumber}/{identity}", method = RequestMethod.GET)
    @ApiOperation("Send otp to mobile number")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Unable to send otp"),
            @ApiResponse(code = 405, message = "Invalid MobileNumber") })
    public ResponseEntity<FoodAppResponse> sendOtp(
            @ApiParam(value = "Phone number", required = true) @PathVariable("phoneNumber") String phoneNumber,
            @ApiParam(value = "identity", required = true) @PathVariable("identity") String identity) {
        try {
            LOGGER.debug("Start send otp to mobile");
            Pattern pattern = Pattern.compile(MOBILEREGEX);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches() && otpIdentity.equals(identity)) {
                LOGGER.debug("Match the otp identity");
                String otpMessage = otpService.sendOtp(phoneNumber);
                if (otpMessage == null) {
                    LOGGER.info("Match the otp identity");
                    return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Unable to send otp", null),
                            HttpStatus.BAD_REQUEST);
                }
                LOGGER.info("sent otp successfully" + phoneNumber);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", otpMessage), HttpStatus.OK);
            } else {
                LOGGER.info("Invalid mobile number or identity");
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Invalid MobileNumber", null),
                        HttpStatus.METHOD_NOT_ALLOWED);
            }

        } catch (Exception e) {
            LOGGER.error("Got exception in sent otp");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal server issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/verify/{phoneNumber}/{identity}/{timestamp}/{otp}", method = RequestMethod.GET)
    @ApiOperation("Send otp to mobile number")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "OTO not valid"),
            @ApiResponse(code = 405, message = "Invalid MobileNumber") })
    public ResponseEntity<FoodAppResponse> validateOtp(
            @ApiParam(value = "Phone number", required = true) @PathVariable("phoneNumber") String phoneNumber,
            @ApiParam(value = "identity", required = true) @PathVariable("identity") String identity,
            @ApiParam(value = "timestamp", required = true) @PathVariable("timestamp") String timestamp,
            @ApiParam(value = "otp", required = true) @PathVariable("otp") String otp) {
        try {
            LOGGER.debug("Start Validating otp");
            Pattern pattern = Pattern.compile(MOBILEREGEX);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches() && otpIdentity.equals(identity)) {
                LOGGER.debug("Match the otp and identity");
                boolean isVerified = otpService.verifyOtp(phoneNumber, timestamp, otp);
                if (!isVerified) {
                    LOGGER.info("Invalid otp by :  " + phoneNumber);
                    return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("OTO not valid", null),
                            HttpStatus.BAD_REQUEST);
                }
                
                User user = userService.getUserByPhoneNum(phoneNumber);
                LOGGER.debug("Validated otp successfully");
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("OTP Valid", user), HttpStatus.OK);
            } else {
                LOGGER.debug("Invalid mobile number or identity");
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Invalid MobileNumber", null),
                        HttpStatus.METHOD_NOT_ALLOWED);
            }

        } catch (Exception e) {
            LOGGER.debug("Got exception in validate the otp :" + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal server issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/admin/get/sms_balance", method = RequestMethod.GET)
    @ApiOperation("Get SMS Balance")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Unable to get sms balance"),
            @ApiResponse(code = 405, message = "Invalid MobileNumber") })
    public ResponseEntity<FoodAppResponse> getSMSBalance() {
        try {
            LOGGER.debug("Start getSMSBalance");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", otpService.getSMSBalance()), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got exception in sent otp");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal server issue", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
