package com.foodworld.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodworld.pojo.Restaurant;
import com.foodworld.repository.IRestaurantRepository;
import com.foodworld.utils.Util;

@Service
public class RestaurantService implements IRestaurantService {

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    IRestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getRestaurantByCity(String city)
            throws JsonParseException, JsonMappingException, IOException {
        List<String> restaurant = restaurantRepository.getRestaurant("{\"adress.city\":\"" + city + "\"}");
        if (restaurant.isEmpty()) {
            return new ArrayList<>();
        }
        List<Restaurant> restaurants = mapper.readValue(restaurant.toString(), new TypeReference<List<Restaurant>>() {
        });
        return restaurants;
    }

    @Override
    public Restaurant getRestaurantByID(String restaurantId)
            throws JsonParseException, JsonMappingException, IOException {
        ArrayList<String> restaurant = restaurantRepository.getRestaurant("{\"_id\":\"" + restaurantId + "\"}");
        if (restaurant.isEmpty()) {
            return null;
        }
        List<Restaurant> restaurants = mapper.readValue(restaurant.toString(), new TypeReference<List<Restaurant>>() {
        });
        return restaurants.get(0);
    }

    @Override
    public Boolean createRestaurant(Restaurant restaurant) throws JsonProcessingException {
        if (restaurant.get_id() == null)
            restaurant.set_id(Util.generateUUID());
        // Object to JSON in String
        String jsonInString = mapper.writeValueAsString(restaurant);
        return restaurantRepository.createRestaurant(jsonInString);
    }

    @Override
    public Boolean updateRestaurant(Restaurant restaurant, String restaurantId) throws JsonProcessingException {
        String jsonInString = mapper.writeValueAsString(restaurant);
        return restaurantRepository.updateRestaurant("{\"_id\":\"" + restaurantId + "\"}", jsonInString);
    }

    @Override
    public Boolean deleteRestaurant(String restaurantId) {
        return restaurantRepository.deleteRestaurant("{\"_id\":\"" + restaurantId + "\"}");
    }

}
