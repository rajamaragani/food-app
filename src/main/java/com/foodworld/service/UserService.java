/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodworld.pojo.User;
import com.foodworld.repository.IUsersRepository;
import com.foodworld.utils.FoodAppEncry;
import com.foodworld.utils.Util;

@Service
public class UserService implements IUserService {

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    IUsersRepository usersRepository;
    @Autowired
    FoodAppEncry foodAppEncry;

    @Override
    public List<User> getAllUsers() throws JsonParseException, JsonMappingException, IOException {
        List<User> user = usersRepository.getUser("");
        return user;
    }

    @Override
    public User createUser(User user) throws JsonProcessingException {
        if (user.get_id() == null)
            user.set_id(Util.generateUUID());

        user.setPassword(foodAppEncry.encrypt(user.getPassword()));
        if (usersRepository.createUser(user))
            return user;
        else {
            return null;
        }
    }

    @Override
    public User getUserByID(String userId) throws JsonParseException, JsonMappingException, IOException {
        List<User> users = usersRepository.getUser("WHERE ID=  \"" + userId + "\"");
        return users != null ? users.get(0) : null;
    }

    @Override
    public Boolean updateUser(User user) {
        return usersRepository.updateUser(user);
    }

    @Override
    public Boolean deleteUser(String userId) {
        return usersRepository.deleteUser(userId);
    }

    @Override
    public User login(User user) {

        user.setPassword(foodAppEncry.encrypt(user.getPassword()));
        return usersRepository.login(user);
    }
    
    @Override
    public User getUserByPhoneNum(String phoneNumber) {
        return usersRepository.getUserByPhoneNum(phoneNumber);
    }

}
