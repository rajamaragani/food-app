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
import com.foodworld.pojo.OrderDetails;
import com.foodworld.row.mappers.OrderMappers;

@Service
public class OrdersRepository implements IOrdersRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    IUsersRepository usersRepository;

    @Autowired
    OrderMappers orderMappers;

    @Override
    public Boolean createOrderDetails(OrderDetails orderDetails) {

        String orderIdsCommaSep = "";
        String orderQuntitysCommaSep = "";
        for (Item item : orderDetails.getOrderInfos().getItems()) {
            orderIdsCommaSep = orderIdsCommaSep + "," + item.getId();
            orderQuntitysCommaSep = orderQuntitysCommaSep + "," + item.getItemQuantity();
        }
        orderIdsCommaSep=orderIdsCommaSep.substring(1);
        orderQuntitysCommaSep=orderQuntitysCommaSep.substring(1);
        String sql = "INSERT INTO ORDER_DETAILS "
                + "(ID, ORDER_STATUS, USER_ID,REST_NAME,REST_ID,FOODTYPE,ITEM_ID,QUANTITY,ORDERED_TIME,TOTAL_AMOUNT) VALUES (?, ?, ?,?,?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql,
                new Object[] { orderDetails.get_id(), orderDetails.getOrderStatus().toString(),
                        orderDetails.getUser().get_id(), orderDetails.getOrderInfos().getRestaurantName(),
                        orderDetails.getOrderInfos().getRestaurantId(), orderDetails.getOrderInfos().getFoodType(),
                        orderIdsCommaSep, orderQuntitysCommaSep, orderDetails.getOrderInfos().getOrderedTime(), orderDetails.getOrderInfos().getTotalAmount()});
        if (update > 0)
            return true;
        else
            return false;
    }

    @Override
    public List<OrderDetails> getOrderDetails(String condition) {
        List<OrderDetails> result = jdbcTemplate.query("SELECT * FROM ORDER_DETAILS " + condition, orderMappers);
        return result;
    }

    @Override
    public Boolean updateOrderDetails(OrderDetails orderDetails) {
        String orderIdsCommaSep = "";
        String orderQuntitysCommaSep = "";
        for (Item item : orderDetails.getOrderInfos().getItems()) {
            orderIdsCommaSep = orderIdsCommaSep + "," + item.getId();
            orderQuntitysCommaSep = orderQuntitysCommaSep + "," + item.getItemQuantity();
        }
        String sql = "UPDATE ORDER_DETAILS SET ORDER_STATUS=?,REST_NAME=?,REST_ID=?,FOODTYPE=?,ITEM_ID=?,QUANTITY=?,ORDERED_TIME=?, TOTAL_AMOUNT=? WHERE ID=? AND USER_ID";
        int update = jdbcTemplate.update(sql, orderDetails.getOrderStatus().toString(),
                orderDetails.getOrderInfos().getRestaurantName(), orderDetails.getOrderInfos().getRestaurantId(),
                orderDetails.getOrderInfos().getFoodType(), orderIdsCommaSep,orderQuntitysCommaSep,
                orderDetails.getOrderInfos().getOrderedTime(),orderDetails.getOrderInfos().getTotalAmount(), orderDetails.get_id(), orderDetails.getUser().get_id());
        if (update > 0)
            return true;
        else
            return false;

    }

    @Override
    public Boolean deleteOrderDetails(String orderId) {
        final String deleteOrderSql = "DELETE FROM ORDER_DETAILS WHERE ID = ?";
        Object[] params = { orderId };
        int[] types = { Types.VARCHAR };
        int orderDeletedRows = jdbcTemplate.update(deleteOrderSql, params, types);
        System.out.println("No items are deleted :" + orderDeletedRows);
        if (orderDeletedRows > 0)
            return true;
        return false;

    }

}
