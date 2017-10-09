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

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foodworld.pojo.Item;

@Component
public class ItemMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Item(rs.getInt("ID"), rs.getString("TYPE"), rs.getString("ITEMNAME"), rs.getInt("PRICE"),
                rs.getInt("DISCOUNT"), rs.getInt("TOTAL_PRICE"), rs.getString("WHICH_PRICE_SHOW"),
                rs.getString("ITEM_LOGO"), rs.getInt("STATUS"));
    }

}
