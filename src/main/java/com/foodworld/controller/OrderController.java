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
import com.foodworld.pojo.OrderDetails;
import com.foodworld.service.IOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/foodworld/order", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api("This API call for the manage the Orders")
public class OrderController {

    @Autowired
    IOrderService orderService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(value = "/admin/all", method = RequestMethod.GET)
    @ApiOperation("Get the all OrderDetails")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "OrderDetails not found") })
    public ResponseEntity<FoodAppResponse> getOrderDetails() {
        try {
            LOGGER.debug("Start getOrderDetails");
            List<OrderDetails> list = orderService.getAllOrderDetails();
            if (list.isEmpty()) {
                LOGGER.info("Records not found");
                LOGGER.debug("End getOrderDetails response empty list");
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("OrderDetails not found", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End getOrderDetails with response");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", list), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got error in getOrderDetails error msg : " + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal server issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/{orderDetailsId}", method = RequestMethod.GET)
    @ApiOperation("Get the OrderDetails by orderDetails ID")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "OrderDetails not found") })
    public ResponseEntity<FoodAppResponse> getOrderDetailsByID(
            @ApiParam(value = "existing orderDetails id", required = true) @PathVariable("orderDetailsId") String orderDetailsId) {
        try {
            LOGGER.debug("Start getOrderDetailsByID");
            OrderDetails orderDetails = orderService.getOrderDetailsByID(orderDetailsId);
            if (orderDetails == null) {
                LOGGER.info("Record not found");
                LOGGER.error("Record not found for  : " + orderDetailsId);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("OrderDetails not found", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End getOrderDetailsByID");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", orderDetails), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got error in getOrderDetailsByID error msg : " + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal server issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @ApiOperation("Get the OrderDetails by user Id")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "OrderDetails not found") })
    public ResponseEntity<FoodAppResponse> getOrderDetailsByUserID(
            @ApiParam(value = "existing orderDetails userId", required = true) @PathVariable("userId") String userId) {
        try {
            LOGGER.debug("Start getOrderDetailsByuserId");
            List<OrderDetails> orderDetails = orderService.getOrderDetailsByUserID(userId);
            if (orderDetails == null || orderDetails.isEmpty()) {
                LOGGER.info("Record not found");
                LOGGER.error("Record not found for  : " + userId);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("OrderDetails not found", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End getOrderDetailsByUserID");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", orderDetails), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got error in getOrderDetailsByID error msg : " + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal server issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    @ApiOperation("Create the new orderDetails")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public ResponseEntity<FoodAppResponse> createOrderDetails(
            @ApiParam(value = "OrderDetails json object that needs to be added to the database", required = true) @RequestBody OrderDetails orderDetails) {
        try {
            LOGGER.debug("End createOrderDetails");
            Boolean isInserted = orderService.createOrderDetails(orderDetails);
            if (!isInserted) {
                LOGGER.info("Record not created");
                LOGGER.error("Record not created : " + orderDetails);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("OrderDetails Not Inserted", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End createOrderDetails");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Inserted Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got error in createOrderDetails error msg : " + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/{orderDetailsId}", method = RequestMethod.PUT)
    @ApiOperation("update the OrderDetails")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "OrderDetails not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public ResponseEntity<?> updateOrderDetails(
            @ApiParam(value = "existing orderDetails id", required = true) @PathVariable("orderDetailsId") String orderDetailsId,
            @ApiParam(value = "new restaurant object for replace", required = true) @RequestBody OrderDetails orderDetails) {
        try {
            LOGGER.debug("Start updateOrderDetails");
            Boolean isInserted = orderService.updateOrderDetails(orderDetails, orderDetailsId);
            if (!isInserted) {
                LOGGER.info("Record not updated");
                LOGGER.error("Record not updated : " + orderDetails);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("OrderDetails Not Updated", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End updateOrderDetails");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Updated Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got error in updateOrderDetails error msg : " + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/{orderDetailsId}", method = RequestMethod.DELETE)
    @ApiOperation("Delete the OrderDetails")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid orderDetailsId supplied"),
            @ApiResponse(code = 404, message = "OrderDetails not found") })
    public ResponseEntity<FoodAppResponse> deleteOrderDetails(
            @ApiParam(value = "orderDetails id for delete", required = true) @PathVariable("orderDetailsId") String orderDetailsId) {
        try {
            LOGGER.debug("Start updateOrderDetails");
            Boolean isInserted = orderService.deleteOrderDetails(orderDetailsId);
            if (!isInserted) {
                LOGGER.info("Record not deleted");
                LOGGER.error("Record not deleted : " + orderDetailsId);
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("OrderDetails Not deleted", null),
                        HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("End updateOrderDetails");
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Deleted Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Got error in deleteOrderDetails error msg : " + e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
