/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.repository;

import java.util.List;

import com.foodworld.pojo.User;

public interface IUsersRepository {

    /**
     * This method is used for create a use
     * 
     * @param user
     * @return
     */
    Boolean createUser(User user);

    /**
     * This method used for the get the user by Uses ID
     * 
     * @param condition
     * @return
     */
    public List<User> getUser(String condition);

    /**
     * This method used for update the used by condition
     * 
     * @param user
     * @param condition
     * @return
     */
    Boolean updateUser(User user);

    /**
     * 
     * This method for the delete user by condition
     * 
     * @param condition
     * @return
     */
    Boolean deleteUser(String condition);

    User login(User user);

    User getUserByPhoneNum(String phoneNumber);

}
