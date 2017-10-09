/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.row.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foodworld.pojo.Address;
import com.foodworld.pojo.Item;
import com.foodworld.pojo.OrderDetails;
import com.foodworld.pojo.OrderInfo;
import com.foodworld.pojo.OrderStatus;
import com.foodworld.pojo.Restaurant;
import com.foodworld.pojo.User;
import com.foodworld.repository.IUsersRepository;

public class RestaurantMappers implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Restaurant(rs.getString("R.ID"), rs.getString("R.TYPE"), rs.getString("R.NAME"),
                rs.getString("R.RESTLOGO"),
                new Address(rs.getString("RD.STREET"), rs.getString("RD.LOCATION"), rs.getString("RD.CITY"),
                        rs.getInt("RD.PIN"), rs.getString("RD.STATE")),
                rs.getInt("R.RATING"), rs.getInt("R.DELIVERY_TIME"), rs.getInt("R.DELIVERY_CHARGES"), null,
                rs.getInt("R.STATUS"));
    }

}
