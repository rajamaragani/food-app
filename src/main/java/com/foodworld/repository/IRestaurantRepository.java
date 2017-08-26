package com.foodworld.repository;

import java.util.ArrayList;

public interface IRestaurantRepository {

    ArrayList<String> getRestaurant(String condition);

    Boolean createRestaurant(String restaurant);

    Boolean updateRestaurant(String restaurant, String condition);

    Boolean deleteRestaurant(String condition);

}
