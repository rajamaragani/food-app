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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.foodworld.pojo.User;

public interface IUserService {

    /**
     * This method for get the all registered
     * 
     * @return list of users
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    List<User> getAllUsers() throws JsonParseException, JsonMappingException, IOException;

    /**
     * This method is used for create a use
     * 
     * @param user
     * @return
     * @throws JsonProcessingException
     */
    Boolean createUser(User user) throws JsonProcessingException;

    /**
     * This method used for the get the user by Uses ID
     * 
     * @param userId
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    User getUserByID(String userId) throws JsonParseException, JsonMappingException, IOException;

    /**
     * This method used for update the used by user id
     * 
     * @param user
     * @return
     */
    Boolean updateUser(User user);

    /**
     * This method for the delete user by user id
     * 
     * @param userId
     * @return
     */
    Boolean deleteUser(String userId);

    /**
     * Checking the is authenticated user
     * 
     * @param user
     * @return
     */
    User login(User user);

    User getUserByPhoneNum(String phoneNumber);
}
