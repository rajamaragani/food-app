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

import com.foodworld.pojo.Item;
import com.foodworld.pojo.OrderDetails;
import com.foodworld.pojo.OrderInfo;
import com.foodworld.pojo.OrderStatus;
import com.foodworld.pojo.User;
import com.foodworld.repository.IUsersRepository;

@Component
public class UserMappers implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getString("ID"), rs.getString("PHONENUMBER"), rs.getString("EMAIL"),
                rs.getString("PASSWORD"), rs.getString("NAME"), null);
    }

}
