package com.foodworld.pojo;
/**
 * 
 * @author raja maragani
 *
 */

import org.joda.time.DateTime;

public class Orders {

    private String restaurantName;
    private String restaurantId;
    private String foodType;
    private Item item;
    private String phoneNumber;
    private DateTime orderedTime;
    private String isOrderConformed;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public DateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(DateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public String getIsOrderConformed() {
        return isOrderConformed;
    }

    public void setIsOrderConformed(String isOrderConformed) {
        this.isOrderConformed = isOrderConformed;
    }

}
