/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.pojo;
/**
 * 
 * @author raja maragani
 *
 */

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.foodworld.utils.Util;

public class OrderInfo {

    private String restaurantName;
    private String restaurantId;
    private String foodType;
    private List<Item> items;
    private String orderedTime;
    private int totalAmount;

    public OrderInfo() {
        super();
    }

    public OrderInfo(String restaurantName, String restaurantId, String foodType, List<Item> items, String orderedTime,  int totalAmount) {
        super();
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
        this.foodType = foodType;
        this.items = items;
        this.orderedTime = orderedTime;
        this.totalAmount= totalAmount;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getOrderedTime() {
        if (StringUtils.isBlank(orderedTime)) {
            return Util.getStringLocalTime();
        }
        return orderedTime;
    }

    public void setOrderedTime(String orderedTime) {
        this.orderedTime = orderedTime;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

}
