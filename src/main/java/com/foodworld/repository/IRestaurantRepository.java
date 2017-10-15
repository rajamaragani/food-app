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

import com.foodworld.pojo.Restaurant;

public interface IRestaurantRepository {

    Boolean createRestaurant(Restaurant restaurant);

    Boolean updateRestaurant(Restaurant restaurant);

    Boolean deleteRestaurant(String condition);

    List<Restaurant> getRestaurant(String condition);

    List<Restaurant> getRestaurant();

    Boolean updateRestaurantStatus(String id,int status);

    Boolean updateItemStatus(String restaurantId, String itemId, int status);

}
