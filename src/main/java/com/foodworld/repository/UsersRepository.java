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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.foodworld.pojo.Address;
import com.foodworld.pojo.User;
import com.foodworld.row.mappers.UserMappers;

@Service
public class UsersRepository implements IUsersRepository {

    /**
     * @Value("${collection.user.name}") private String collectionName;
     * 
     * @Value("${database.name}") private String dataBaseName;
     */

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Boolean createUser(User user) {
        String sql1 = "INSERT INTO USER (ID,PHONENUMBER,NAME,EMAIL,PASSWORD) VALUES (?, ?, ?,?,?)";
        int update1 = jdbcTemplate.update(sql1, new Object[] { user.get_id(), user.getPhoneNumber(), user.getName(),
                user.getEmail(), user.getPassword() });

        String sql = "INSERT INTO USER_ADDRESS (USER_ID, STREET, LOCATION,PIN,CITY,STATE) VALUES (?, ?, ?,?,?,?)";
        int update = jdbcTemplate.update(sql,
                new Object[] { user.get_id(), user.getAddress().getStreet(), user.getAddress().getLocation(),
                        user.getAddress().getPin(), user.getAddress().getCity(), user.getAddress().getState() });
        if (update > 0 && update1 > 0)
            return true;
        else
            return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUser(String condition) {
        String addressSql = "SELECT * FROM USER_ADDRESS WHERE USER_ID=?";
        List<User> result = jdbcTemplate.query("SELECT * FROM USER " + condition, new UserMappers());
        for (int i = 0; i < result.size(); i++) {
            Address customer = (Address) jdbcTemplate.queryForObject(addressSql,
                    new Object[] { result.get(i).get_id() }, new BeanPropertyRowMapper(Address.class));

            result.get(i).setAddress(customer);
        }
        return result;
    }

    @Override
    public Boolean updateUser(User user) {

        String sql1 = "UPDATE USER SET PHONENUMBER=?,NAME=?,EMAIL=?,PASSWORD=? WHERE ID =?";
        int update1 = jdbcTemplate.update(sql1, user.getPhoneNumber(), user.getName(), user.getEmail(),
                user.getPassword(), user.get_id());

        String sql = "UPDATE USER_ADDRESS SET STREET=?, LOCATION=?,PIN=?,CITY=?,STATE=? WHERE USER_ID=?";
        int update = jdbcTemplate.update(sql, user.getAddress().getStreet(), user.getAddress().getLocation(),
                user.getAddress().getPin(), user.getAddress().getCity(), user.getAddress().getState(), user.get_id());
        if (update > 0 && update1 > 0)
            return true;
        else
            return false;

    }

    @Override
    public Boolean deleteUser(String userId) {
        final String deleteItemSql = "DELETE FROM ORDER_DETAILS WHERE USER_ID = ?";
        final String deleteAddressSql = "DELETE FROM USER_ADDRESS WHERE USER_ID = ?";
        final String deleteUserSql = "DELETE FROM USER WHERE ID = ?";
        Object[] params = { userId };
        int[] types = {Types.VARCHAR};

        int itemDeletedRows = jdbcTemplate.update(deleteItemSql, params, types);
        int addressDeletedRows = jdbcTemplate.update(deleteAddressSql, params, types);
        int userDeletedRows = jdbcTemplate.update(deleteUserSql, params, types);

        System.out.println("No items are deleted :" + itemDeletedRows);
        System.out.println("No Address are deleted :" + addressDeletedRows);
        System.out.println("No User are deleted :" + userDeletedRows);
        if (userDeletedRows > 0)
            return true;
        return false;
    }

    @Override
    public User login(User user) {
        String addressSql = "SELECT * FROM USER_ADDRESS WHERE USER_ID=?";
        List<User> result = jdbcTemplate.query("SELECT * FROM USER WHERE EMAIL=\"" + user.getEmail()
                + "\", AND PASSWORD=\"" + user.getPassword() + "\"", new UserMappers());
        for (int i = 0; i < result.size(); i++) {
            Address customer = (Address) jdbcTemplate.queryForObject(addressSql,
                    new Object[] { result.get(i).get_id() }, new BeanPropertyRowMapper(Address.class));

            result.get(i).setAddress(customer);
        }
        return result.isEmpty() ? null : result.get(0);
    }
    
    @Override
    public User getUserByPhoneNum(String phoneNumber) {
        String addressSql = "SELECT * FROM USER_ADDRESS WHERE USER_ID=?";
        List<User> result = jdbcTemplate.query("SELECT * FROM USER WHERE PHONENUMBER=\"" + phoneNumber
                + "\"", new UserMappers());
        for (int i = 0; i < result.size(); i++) {
            Address customer = (Address) jdbcTemplate.queryForObject(addressSql,
                    new Object[] { result.get(i).get_id() }, new BeanPropertyRowMapper(Address.class));

            result.get(i).setAddress(customer);
        }
        return result.isEmpty() ? null : result.get(0);
    }

}
