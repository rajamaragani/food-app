/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.foodworld.pojo.Item;
import com.foodworld.pojo.Restaurant;
import com.foodworld.row.mappers.ItemMapper;
import com.foodworld.row.mappers.RestaurantMappers;

@Service
public class RestaurantRepository implements IRestaurantRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Restaurant> getRestaurant(String condition) {

        List<Restaurant> result = jdbcTemplate.query(
                "SELECT R.*, RD.* FROM RESTAURANT R,REST_ADDRESS RD WHERE R.STATUS=1 AND RD.RESTAURANT_ID =R.ID AND "
                        + condition,
                new RestaurantMappers());
        for (int i = 0; i < result.size(); i++) {
            List<Item> itemList = jdbcTemplate.query(
                    "SELECT * FROM ITEM WHERE STATUS=1 AND RESTAURANT_ID=\"" + result.get(i).get_id() + "\"",
                    new ItemMapper());

            result.get(i).setItems(itemList);
        }
        return result;

    }

    @Override
    public List<Restaurant> getRestaurant() {

        List<Restaurant> result = jdbcTemplate.query(
                "SELECT R.*, RD.* FROM RESTAURANT R,REST_ADDRESS RD WHERE RD.RESTAURANT_ID =R.ID",
                new RestaurantMappers());
        for (int i = 0; i < result.size(); i++) {
            List<Item> itemList = jdbcTemplate.query(
                    "SELECT * FROM ITEM WHERE RESTAURANT_ID=\"" + result.get(i).get_id() + "\"", new ItemMapper());

            result.get(i).setItems(itemList);
        }
        return result;

    }

    @Override
    public Boolean createRestaurant(Restaurant restaurant) {
        String itemInsert = "INSERT INTO ITEM (TYPE,ITEMNAME,PRICE,DISCOUNT,TOTAL_PRICE,WHICH_PRICE_SHOW,ITEM_LOGO,RESTAURANT_ID)  VALUES (?, ?, ?,?,?,?,?,?)";
        try {
            String sql1 = "INSERT INTO RESTAURANT (ID,TYPE,NAME,RESTLOGO,RATING,DELIVERY_TIME,DELIVERY_CHARGES) VALUES (?, ?, ?,?,?,?,?)";
            int update1 = jdbcTemplate.update(sql1,
                    new Object[] { restaurant.get_id(), restaurant.getType(), restaurant.getName(),
                            restaurant.getRestLogo(), restaurant.getRating(), restaurant.getDliveryTimeInMin(),
                            restaurant.getDliveryCharges() });

            String sql = "INSERT INTO REST_ADDRESS (RESTAURANT_ID, STREET, LOCATION,PIN,CITY,STATE) VALUES (?, ?, ?,?,?,?)";
            int update = jdbcTemplate.update(sql,
                    new Object[] { restaurant.get_id(), restaurant.getAddress().getStreet(),
                            restaurant.getAddress().getLocation(), restaurant.getAddress().getPin(),
                            restaurant.getAddress().getCity(), restaurant.getAddress().getState() });

            for (Item item : restaurant.getItems()) {
                int updateItem = jdbcTemplate.update(itemInsert,
                        new Object[] { item.getType(), item.getItemName(), item.getPrice(), item.getDiscount(),
                                item.getTotalPrice(), item.getWhichPriceShow(), item.getItemLogo(),
                                restaurant.get_id() });

            }

            if (update > 0 && update1 > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            System.out.println("Exception ::" + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public Boolean updateRestaurant(Restaurant restaurant) {

        String itemInsert = "UPDATE ITEM  SET TYPE=?,ITEMNAME=?,PRICE=?,DISCOUNT=?,TOTAL_PRICE=?,WHICH_PRICE_SHOW=?,ITEM_LOGO=?, STATUS=? WHERE ID=?";
        try {
            String sql1 = "UPDATE RESTAURANT SET TYPE=?,NAME=?,RESTLOGO=?,RATING=?,DELIVERY_TIME=?,DELIVERY_CHARGES=? ,STATUS=? WHERE ID=?";
            int update1 = jdbcTemplate.update(sql1, restaurant.getType(), restaurant.getName(),
                    restaurant.getRestLogo(), restaurant.getRating(), restaurant.getDliveryTimeInMin(),
                    restaurant.getDliveryCharges(), restaurant.getStatus(), restaurant.get_id());

            String sql = "UPDATE REST_ADDRESS SET STREET=?, LOCATION=?,PIN=?,CITY=?,STATE=?  WHERE RESTAURANT_ID=?";

            int update = jdbcTemplate.update(sql, restaurant.getAddress().getStreet(),
                    restaurant.getAddress().getLocation(), restaurant.getAddress().getPin(),
                    restaurant.getAddress().getCity(), restaurant.getAddress().getState(), restaurant.get_id());

            for (Item item : restaurant.getItems()) {
                int updateItem = jdbcTemplate.update(itemInsert, item.getType(), item.getItemName(), item.getPrice(),
                        item.getDiscount(), item.getTotalPrice(), item.getWhichPriceShow(), item.getItemLogo(),
                        item.getStatus(), item.getId());

            }

            if (update > 0 && update1 > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            System.out.println("Exception ::" + e.getLocalizedMessage());
            return false;
        }

    }

    @Override
    public Boolean updateRestaurantStatus(String id, int status) {

        try {
            String sql1 = "UPDATE RESTAURANT SET STATUS=? WHERE ID=?";
            int update1 = jdbcTemplate.update(sql1, status, id);
            if (update1 > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            System.out.println("Exception ::" + e.getLocalizedMessage());
            return false;
        }

    }

    @Override
    public Boolean deleteRestaurant(String restId) {
        final String deleteItemSql = "DELETE FROM ITEM WHERE RESTAURANT_ID = ?";
        final String deleteOrderSql = "DELETE FROM ORDER_DETAILS WHERE RESTAURANT_ID = ?";
        final String deleteRESTAddressSql = "DELETE FROM REST_ADDRESS WHERE RESTAURANT_ID = ?";
        final String deleteRESTAURANTSql = "DELETE FROM RESTAURANT WHERE ID = ?";
        Object[] params = { restId };
        int[] types = { Types.VARCHAR };

        int itemDeletedRows = jdbcTemplate.update(deleteItemSql, params, types);
        int orderDeletedRows = jdbcTemplate.update(deleteOrderSql, params, types);
        int restAddressDeletedRows = jdbcTemplate.update(deleteRESTAddressSql, params, types);
        int restaurantDeletedRows = jdbcTemplate.update(deleteRESTAURANTSql, params, types);
        System.out.println("No items are deleted :" + itemDeletedRows);
        System.out.println("No Orders are deleted :" + orderDeletedRows);
        System.out.println("No Rest Address are deleted :" + restAddressDeletedRows);
        System.out.println("No Restaurants are deleted :" + restaurantDeletedRows);
        if (restaurantDeletedRows > 0)
            return true;
        return false;

    }

}
