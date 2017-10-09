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
import com.foodworld.pojo.OrderDetails;

public interface IOrderService {

    /**
     * This method for get the all registered
     * 
     * @return list of orderDetails
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    List<OrderDetails> getAllOrderDetails() throws JsonParseException, JsonMappingException, IOException;

    /**
     * This method is used for create a Order
     * 
     * @param orderDetails
     * @return
     * @throws JsonProcessingException
     */
    Boolean createOrderDetails(OrderDetails orderDetails) throws JsonProcessingException;

    /**
     * This method used for the get the orderDetails by Order ID
     * 
     * @param orderDetailsId
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    OrderDetails getOrderDetailsByID(String orderDetailsId)
            throws JsonParseException, JsonMappingException, IOException;

    /**
     * This method used for update the used by orderDetails id
     * 
     * @param orderDetails
     * @param orderDetailsId
     * @return
     * @throws JsonProcessingException
     */
    Boolean updateOrderDetails(OrderDetails orderDetails, String orderDetailsId) throws JsonProcessingException;

    /**
     * This method for the delete orderDetails by orderDetails id
     * 
     * @param orderDetailsId
     * @return
     */
    Boolean deleteOrderDetails(String orderDetailsId);

    List<OrderDetails> getOrderDetailsByUserID(String userId);

}
