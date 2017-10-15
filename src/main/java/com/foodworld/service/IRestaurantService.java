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
import com.foodworld.pojo.Restaurant;

public interface IRestaurantService {

    /**
     * This method returns the List of restaurants in a mentioned city
     * 
     * @param city
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    List<Restaurant> getRestaurantByCity(String city) throws JsonParseException, JsonMappingException, IOException;

    /**
     * This method for the fetch the restaurant by the restaurant id
     * 
     * @param restaurantId
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    Restaurant getRestaurantByID(String restaurantId) throws JsonParseException, JsonMappingException, IOException;

    /**
     * This method used for the insert the new restaurant to the database
     * 
     * @param restaurant
     * @return
     * @throws JsonProcessingException
     */
    Boolean createRestaurant(Restaurant restaurant) throws JsonProcessingException;

    /**
     * This method used for the update the Restaurant by restaurant id
     * 
     * @param restaurant
     * @return
     */
    Boolean updateRestaurant(Restaurant restaurant);

    /**
     * This method is used for the delete the restaurant by id
     * 
     * @param restaurantId
     * @return
     * @throws JsonProcessingException
     */
    Boolean deleteRestaurant(String restaurantId) throws JsonProcessingException;

    List<Restaurant> getRestaurantByCity() throws JsonParseException, JsonMappingException, IOException;

    Boolean updateRestaurantStatus(String restaurantId, int status);
    /**
     * This method used for the update the Item status 
     * @param restaurantId
     * @param status
     * @return
     */
    Boolean updateItemStatus(String restaurantId,String ItemId, int status);

}
