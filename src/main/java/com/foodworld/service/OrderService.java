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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodworld.pojo.OrderDetails;
import com.foodworld.repository.IOrdersRepository;
import com.foodworld.utils.Util;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrdersRepository ordersRepository;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<OrderDetails> getAllOrderDetails() throws JsonParseException, JsonMappingException, IOException {
        List<OrderDetails> orderDetails = ordersRepository.getOrderDetails("");
        return orderDetails;
    }

    @Override
    public Boolean createOrderDetails(OrderDetails orderDetails) throws JsonProcessingException {
        if (orderDetails.get_id() == null)
            orderDetails.set_id(Util.generateUUID());
        return ordersRepository.createOrderDetails(orderDetails);
    }

    @Override
    public OrderDetails getOrderDetailsByID(String orderDetailsId)
            throws JsonParseException, JsonMappingException, IOException {
        List<OrderDetails> orderDetails = ordersRepository.getOrderDetails("WHERE ID=\"" + orderDetailsId+"\"");
        return orderDetails.get(0);
    }

    @Override
    public Boolean updateOrderDetails(OrderDetails orderDetails, String orderDetailsId) throws JsonProcessingException {
        return ordersRepository.updateOrderDetails(orderDetails);
    }

    @Override
    public Boolean deleteOrderDetails(String orderDetailsId) {
        return ordersRepository.deleteOrderDetails(orderDetailsId);
    }

    @Override
    public List<OrderDetails> getOrderDetailsByUserID(String userId) {
        return ordersRepository.getOrderDetails("WHERE USER_ID=\"" + userId+"\"");
    }

}
