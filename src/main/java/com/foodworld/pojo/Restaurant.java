/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.pojo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author raja maragani
 *
 */
public class Restaurant {

    private String _id;
    private String type;
    private String name;
    private String restLogo;
    private Address address;
    private int rating;
    private int dliveryTimeInMin;
    private int dliveryCharges;
    private List<Item> items;
    private int status;

    public Restaurant() {
        super();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetails.class);
    ObjectMapper mapper = new ObjectMapper();

    public Restaurant(String _id, String type, String name, String restLogo, Address address, int rating,
            int dliveryTimeInMin, int dliveryCharges, List<Item> items, int status) {
        super();
        this._id = _id;
        this.type = type;
        this.name = name;
        this.restLogo = restLogo;
        this.address = address;
        this.rating = rating;
        this.dliveryTimeInMin = dliveryTimeInMin;
        this.dliveryCharges = dliveryCharges;
        this.items = items;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestLogo() {
        return restLogo;
    }

    public void setRestLogo(String restLogo) {
        this.restLogo = restLogo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getDliveryTimeInMin() {
        return dliveryTimeInMin;
    }

    public void setDliveryTimeInMin(int dliveryTimeInMin) {
        this.dliveryTimeInMin = dliveryTimeInMin;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getDliveryCharges() {
        return dliveryCharges;
    }

    public void setDliveryCharges(int dliveryCharges) {
        this.dliveryCharges = dliveryCharges;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
