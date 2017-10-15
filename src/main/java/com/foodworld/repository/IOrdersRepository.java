/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.repository;

import java.util.List;

import com.foodworld.pojo.OrderDetails;

public interface IOrdersRepository {

    List<OrderDetails> getOrderDetails(String condition);

    Boolean updateOrderDetails(OrderDetails orderDetails);

    Boolean deleteOrderDetails(String condition);

    Boolean createOrderDetails(OrderDetails orderDetails);

    Boolean updateOrderStatus(String orderDetailsId, String status);

    List<OrderDetails> getTodayOrderDetails();

    List<OrderDetails> getOneWeekOrderDetails();

}
