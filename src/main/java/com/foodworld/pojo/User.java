/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author raja maragani
 *
 */
public class User {

    public User() {
        super();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetails.class);
    ObjectMapper mapper = new ObjectMapper();

    public User(String _id, String phoneNumber, String email, String password, String name, Address address) {
        super();
        this._id = _id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    private String _id;
    private String phoneNumber;
    private String email;
    private String password;
    private String name;
    private Address address;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            LOGGER.error("Getting issue while paring to object to json:" + this.get_id());
            return super.toString();
        }
    }
}
