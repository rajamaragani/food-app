/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodworld.pojo.FoodAppResponse;
import com.foodworld.pojo.Restaurant;
import com.foodworld.service.IRestaurantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author raja maragani
 *
 */
@RestController
@RequestMapping(value = "/foodworld/restaurant", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api("This API call for the manage the All Restaurants")
public class RestaurantController {

    @Autowired
    IRestaurantService restaurantService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantController.class);

    @RequestMapping(value = "/user/city/{city}", method = { RequestMethod.GET })
    @ApiOperation("Get the all restaurants in a perticular city")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Restaurants not found") })
    public ResponseEntity<FoodAppResponse> getRestaurants(
            @ApiParam(value = "city that need to be fetch restatuants", required = true) @PathVariable("city") String city) {
        try {
            LOGGER.debug("Start getRestaurants");
            List<Restaurant> list = restaurantService.getRestaurantByCity(city);
            if (list.isEmpty()) {
                LOGGER.info("Records not found");
                LOGGER.debug("Records not found  for city :" + city);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Restaurants not found", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End getRestaurants");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", list), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got exception in getRestaurants :" + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal server issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/all", method = { RequestMethod.GET })
    @ApiOperation("Get the all restaurants in a perticular city")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Restaurants not found") })
    public ResponseEntity<FoodAppResponse> getRestaurants() {
        try {
            LOGGER.debug("Start getRestaurants");
            List<Restaurant> list = restaurantService.getRestaurantByCity();
            if (list.isEmpty()) {
                LOGGER.info("Records not found");
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Restaurants not found", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End getRestaurants");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", list), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got exception in getRestaurants :" + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal server issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/{restaurantId}", method = RequestMethod.GET)
    @ApiOperation("Get a Restaurant based on the restaurant id")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Restaurant not found") })
    public ResponseEntity<FoodAppResponse> getRestaurantById(
            @ApiParam(value = "restaurant id that need to be fetch restatuant", required = true) @PathVariable("restaurantId") String restaurantId) {
        try {
            LOGGER.debug("Start getRestaurantById");
            Restaurant restaurant = restaurantService.getRestaurantByID(restaurantId);
            if (restaurant == null) {
                LOGGER.info("Records not found");
                LOGGER.debug("Records not found  for restaurant id :" + restaurantId);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Restaurant not found", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("Start getRestaurantById");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", restaurant), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got exception in getRestaurantById :" + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/", method = RequestMethod.POST)
    @ApiOperation("Create the new Restaurant")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public ResponseEntity<FoodAppResponse> createRestaurant(
            @ApiParam(value = "Restaurant json object that needs to be added to the database", required = true) @RequestBody Restaurant restaurant) {
        try {
            LOGGER.debug("Start createRestaurant");
            Boolean isInserted = restaurantService.createRestaurant(restaurant);
            if (!isInserted) {
                LOGGER.info("Unable to create Restaurant");
                LOGGER.debug("Unable to create Restaurant  :" + restaurant);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Not Inserted", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("Start createRestaurant");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Inserted Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got exception in createRestaurant :" + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.PUT)
    @ApiOperation("update the Restaurant")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Restaurant not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public ResponseEntity<?> updateRestaurant(
            @ApiParam(value = "new restaurant object for replace", required = true) @RequestBody Restaurant restaurant) {
        try {
            LOGGER.debug("Start updateRestaurant");
            Boolean isInserted = restaurantService.updateRestaurant(restaurant);
            if (!isInserted) {
                LOGGER.info("Unable to update Restaurant");
                LOGGER.debug("Unable to update Restaurant  :" + restaurant);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Not Updated", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End updateRestaurant");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Updated Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got exception in updateRestaurant :" + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/{restaurantId}/{status}", method = RequestMethod.PUT)
    @ApiOperation("update the Restaurant status by id")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Restaurant not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public ResponseEntity<?> updateRestaurantStatus(
            @ApiParam(value = "restaurant id requied for update status", required = true) @PathVariable("restaurantId") String restaurantId,
            @ApiParam(value = "current status for restaurant", required = true) @PathVariable("status") int status) {
        try {
            LOGGER.debug("Start updateRestaurantStatus");
            Boolean isUpdated = restaurantService.updateRestaurantStatus(restaurantId, status);
            if (!isUpdated) {
                LOGGER.info("Unable to update Restaurant Status");
                LOGGER.debug("Unable to update Restaurant Status :" + restaurantId);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Not Updated", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End updateRestaurantStatus");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Updated Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got exception in updateRestaurant :" + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/{restaurantId}/{itemId}/{status}", method = RequestMethod.PUT)
    @ApiOperation("update the Item Status status by itemId  and Restaurant Id")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Restaurant not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public ResponseEntity<?> updateItemStatus(
            @ApiParam(value = "Restaurant id requied for update status", required = true) @PathVariable("restaurantId") String restaurantId,
            @ApiParam(value = "Item id requied for update status", required = true) @PathVariable("itemId") String itemId,
            @ApiParam(value = "current status for restaurant", required = true) @PathVariable("status") int status) {
        try {
            LOGGER.debug("Start updateItemStatus");
            Boolean isUpdated = restaurantService.updateItemStatus(restaurantId, itemId, status);
            if (!isUpdated) {
                LOGGER.info("Unable to update Item Status");
                LOGGER.debug("Unable to update Item Status  :" + restaurantId);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Not Updated", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End updateItemStatus");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Updated Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got exception in updateRestaurant :" + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/{restaurantId}/", method = RequestMethod.DELETE)
    @ApiOperation("Delete the Restaurant")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid restaurantId supplied"),
            @ApiResponse(code = 404, message = "restaurant not found") })
    public ResponseEntity<FoodAppResponse> deleteRestaurant(
            @ApiParam(value = "restaurant id for delete", required = true) @PathVariable("restaurantId") String restaurantId) {
        try {
            LOGGER.debug("Start deleteRestaurant");
            Boolean isInserted = restaurantService.deleteRestaurant(restaurantId);
            if (!isInserted) {
                LOGGER.info("Unable to delete Restaurant");
                LOGGER.debug("Unable to delete Restaurant  :" + restaurantId);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Not deleted", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End deleteRestaurant");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Deleted Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got exception in deleteRestaurant :" + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
