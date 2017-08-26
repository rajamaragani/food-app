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
     * @param restaurantId
     * @return
     * @throws JsonProcessingException
     */
    Boolean updateRestaurant(Restaurant restaurant, String restaurantId) throws JsonProcessingException;

    /**
     * This method is used for the delete the restaurant by id
     * 
     * @param restaurantId
     * @return
     * @throws JsonProcessingException
     */
    Boolean deleteRestaurant(String restaurantId) throws JsonProcessingException;

}
