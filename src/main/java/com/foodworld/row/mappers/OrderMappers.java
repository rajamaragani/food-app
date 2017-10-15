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
import java.util.ArrayList;
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
public class OrderMappers implements RowMapper {

    @Autowired
    IUsersRepository usersRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    String itemSql = "SELECT * FROM ITEM WHERE ID=?";

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        String[] orderIdsCommaSep = rs.getString("ITEM_ID").split(",");
        String[] orderQuntitysCommaSep = rs.getString("QUANTITY").split(",");
        List<Item> items = new ArrayList<Item>();
        List<User> user = usersRepository.getUser("WHERE ID=  \"" + rs.getString("USER_ID")+"\"");
        for (int i = 0; i < orderIdsCommaSep.length; i++) {
            Item item = (Item) jdbcTemplate.queryForObject(itemSql, new Object[] { orderIdsCommaSep[i] },
                    new BeanPropertyRowMapper(Item.class));
            item.setItemQuantity(Integer.parseInt(orderQuntitysCommaSep[i]));
            items.add(item);
        }

        return new OrderDetails(rs.getString("ID"), OrderStatus.valueOf(rs.getString("ORDER_STATUS")),
                new OrderInfo(rs.getString("REST_NAME"), rs.getString("REST_ID"), rs.getString("FOODTYPE"),
                        items, rs.getString("ORDERED_TIME"), rs.getInt("TOTAL_AMOUNT")),
                user.get(0),rs.getInt("ORDER_DELIVERY_REMAIN_TIME"));

    }

}
