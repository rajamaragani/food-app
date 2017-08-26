package com.foodworld.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodworld.pojo.FoodAppResponse;
import com.foodworld.repository.IUsersRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/foodworld/user", produces = "application/json")
@Api("This api call for users")
public class UserController {

    @Autowired
    IUsersRepository usersRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation("Get the All users")
    public ResponseEntity<List<FoodAppResponse>> getUsers() {
        FoodAppResponse r = new FoodAppResponse("This is Raja ", "Hiiiiii");

        ObjectMapper mapper = new ObjectMapper();
        String stringSubscription;
        try {
            stringSubscription = mapper.writeValueAsString(r);
            usersRepository.addSubscription(stringSubscription);
        } catch (Exception e) {

        }
        List<FoodAppResponse> list = new ArrayList<>();
        list.add(r);
        return new ResponseEntity<List<FoodAppResponse>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation("Create a user")
    public ResponseEntity<List<FoodAppResponse>> createUser() {
        List<FoodAppResponse> list = new ArrayList<>();
        FoodAppResponse r = new FoodAppResponse("This is Raja ", "Hiiiiii");

        ObjectMapper mapper = new ObjectMapper();
        String stringSubscription;
        try {
            stringSubscription = mapper.writeValueAsString(r);
            usersRepository.addSubscription(stringSubscription);
        } catch (Exception e) {

        }
        list.add(r);
        return new ResponseEntity<List<FoodAppResponse>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation("Update a user")
    public ResponseEntity<List<FoodAppResponse>> updateUser() {
        List<FoodAppResponse> list = new ArrayList<>();
        return new ResponseEntity<List<FoodAppResponse>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation("Delete a user")
    public ResponseEntity<List<FoodAppResponse>> deleteUser() {
        List<FoodAppResponse> list = new ArrayList<>();
        return new ResponseEntity<List<FoodAppResponse>>(list, HttpStatus.OK);
    }
}
