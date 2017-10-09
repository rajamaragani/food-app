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
import com.foodworld.pojo.Restaurant;
import com.foodworld.repository.IRestaurantRepository;
import com.foodworld.utils.Util;

@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    IRestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getRestaurantByCity(String city)
            throws JsonParseException, JsonMappingException, IOException {
        List<Restaurant> restaurants = restaurantRepository.getRestaurant(" RD.CITY=\"" + city + "\"");
        return restaurants;
    }
    
    @Override
    public List<Restaurant> getRestaurantByCity()
            throws JsonParseException, JsonMappingException, IOException {
        List<Restaurant> restaurants = restaurantRepository.getRestaurant();
        return restaurants;
    }

    @Override
    public Restaurant getRestaurantByID(String restaurantId)
            throws JsonParseException, JsonMappingException, IOException {
        List<Restaurant> restaurants = restaurantRepository.getRestaurant(" R.ID=\"" + restaurantId + "\"");
        return restaurants.get(0);
    }

    @Override
    public Boolean createRestaurant(Restaurant restaurant) throws JsonProcessingException {
        if (restaurant.get_id() == null)
            restaurant.set_id(Util.generateUUID());
        return restaurantRepository.createRestaurant(restaurant);
    }

    @Override
    public Boolean updateRestaurant(Restaurant restaurant) {
        return restaurantRepository.updateRestaurant(restaurant);
    }
    @Override
    public Boolean updateRestaurantStatus(String restaurantId,int status) {
        return restaurantRepository.updateRestaurantStatus(restaurantId,status);
    }

    @Override
    public Boolean deleteRestaurant(String restaurantId) {
        return restaurantRepository.deleteRestaurant(restaurantId);
    }
}
