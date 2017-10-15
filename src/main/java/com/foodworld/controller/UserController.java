/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodworld.pojo.FoodAppResponse;
import com.foodworld.pojo.User;
import com.foodworld.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/foodworld/users", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api("This API call for the manage the users")
public class UserController {

    @Autowired
    IUserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OTPController.class);

    @RequestMapping(value = "/admin/all", method = RequestMethod.GET)
    @ApiOperation("Get the all Users")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Users not found") })
    public ResponseEntity<FoodAppResponse> getUsers() {
        try {
            LOGGER.debug("Start getUsers");
            List<User> list = userService.getAllUsers();
            if (list.isEmpty()) {
                LOGGER.info("Records not found");
                LOGGER.debug("No User Records found");
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Users not found", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End Get Users");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", list), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got Exception in getUsers " + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal server issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/{userId}/", method = RequestMethod.GET)
    @ApiOperation("Get the User by user ID")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "User not found") })
    public ResponseEntity<FoodAppResponse> getUserByID(
            @ApiParam(value = "existing user id", required = true) @PathVariable("userId") String userId) {
        try {
            LOGGER.debug("Start Get User by Id");
            User user = userService.getUserByID(userId);
            if (user == null) {
                LOGGER.info("User Not found");
                LOGGER.debug("User not found by id :" + userId);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("User not found", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End Get User by Id");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", user), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got Exception in get user by id :" + e.getLocalizedMessage());
            System.out.println("Exception :: " + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal server issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    @ApiOperation("Create the new user")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public ResponseEntity<FoodAppResponse> createUser(
            @ApiParam(value = "User json object that needs to be added to the database", required = true) @RequestBody User user) {
        try {
            User user2 = userService.getUserByPhoneNum(user.getPhoneNumber());
            if (user2 != null) {
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("User Already Existed", null),
                        HttpStatus.BAD_REQUEST);
            }
            user2 = userService.createUser(user);
            LOGGER.debug("Start Creating User");
            if (user2 == null) {
                LOGGER.info("User Not created");
                LOGGER.debug("User not created for the user :" + user);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("User Not Inserted", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End Create User");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Inserted Successfully", user2),
                    HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got Exception in Creating User");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/login/", method = RequestMethod.POST)
    @ApiOperation("User Validation")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public ResponseEntity<FoodAppResponse> loginUser(
            @ApiParam(value = "User login", required = true) @RequestBody User user) {
        try {
            LOGGER.debug("Start Login User");
            User user2 = userService.login(user);
            if (user2 == null) {
                LOGGER.info("Authentication failed");
                LOGGER.debug("Authentication failed for the user :" + user);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Authentication failed", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("Login successfully ");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", user2), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got Exception in login user");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/", method = RequestMethod.PUT)
    @ApiOperation("update the User")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public ResponseEntity<?> updateUser(
            @ApiParam(value = "new restaurant object for replace", required = true) @RequestBody User user) {
        try {
            LOGGER.debug("Start Update User");
            Boolean isInserted = userService.updateUser(user);
            if (!isInserted) {
                LOGGER.debug("Unable to update the user :" + user);
                LOGGER.info("Unable to update");
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("User Not Updated", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End the update user");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Updated Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.debug("Got exception in update user");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/{userId}", method = RequestMethod.DELETE)
    @ApiOperation("Delete the User")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid userId supplied"),
            @ApiResponse(code = 404, message = "User not found") })
    public ResponseEntity<FoodAppResponse> deleteUser(
            @ApiParam(value = "user id for delete", required = true) @PathVariable("userId") String userId) {
        try {
            LOGGER.debug("Unable to update");
            Boolean isInserted = userService.deleteUser(userId);
            if (!isInserted) {
                LOGGER.info("User not deleted");
                LOGGER.debug("user not deleted for the user :" + userId);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("User Not deleted", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End the delete user");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Deleted Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got exception in delete the user");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
