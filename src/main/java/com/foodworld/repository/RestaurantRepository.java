package com.foodworld.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.foodworld.mongohandler.MongoDBHandler;

@Service
public class RestaurantRepository implements IRestaurantRepository {

    @Autowired
    MongoDBHandler mongoDBHandler;

    @Value("${collection.name}")
    private String collectionName;

    @Value("${database.name}")
    private String dataBaseName;

    @Override
    public ArrayList<String> getRestaurant(String condition) {
        return mongoDBHandler.find(dataBaseName, collectionName, condition);
    }

    @Override
    public Boolean createRestaurant(String restaurant) {
        return mongoDBHandler.insertDocument(dataBaseName, collectionName, restaurant);
    }

    @Override
    public Boolean updateRestaurant(String restaurant, String restaurantId) {
        return mongoDBHandler.updateDocument(dataBaseName, collectionName, restaurant, restaurantId);
    }

    @Override
    public Boolean deleteRestaurant(String condition) {
        return mongoDBHandler.dropDocument(dataBaseName, collectionName, condition);
    }

}
