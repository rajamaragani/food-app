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

public class OrderDetails {

    private String _id;
    private OrderStatus orderStatus;
    private OrderInfo orderInfos;
    private User user;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetails.class);
    ObjectMapper mapper = new ObjectMapper();
    private int orderRemainTimeInMin;

    public OrderDetails() {
        super();
    }

    public OrderDetails(String _id, OrderStatus orderStatus, OrderInfo orderInfos, User user,
            int orderRemainTimeInMin) {
        super();
        this._id = _id;
        this.orderStatus = orderStatus;
        this.orderInfos = orderInfos;
        this.user = user;
        this.orderRemainTimeInMin = orderRemainTimeInMin;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderInfo getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(OrderInfo orderInfos) {
        this.orderInfos = orderInfos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getOrderRemainTimeInMin() {
        return orderRemainTimeInMin;
    }

    public void setOrderRemainTimeInMin(int orderRemainTimeInMin) {
        this.orderRemainTimeInMin = orderRemainTimeInMin;
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
