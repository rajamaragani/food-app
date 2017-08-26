package com.foodworld.controller;

import java.util.List;

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

    @RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
    @ApiOperation("Get the all restaurants in a perticular city")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Restaurants not found") })
    public ResponseEntity<FoodAppResponse> getRestaurants(
            @ApiParam(value = "city that need to be fetch restatuants", required = true) @PathVariable("city") String city) {
        try {
            List<Restaurant> list = restaurantService.getRestaurantByCity(city);
            if (list.isEmpty()) {
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Restaurants not found", null),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", list), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal server issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{restaurantId}", method = RequestMethod.GET)
    @ApiOperation("Get a Restaurant based on the restaurant id")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Restaurant not found") })
    public ResponseEntity<FoodAppResponse> getRestaurantById(
            @ApiParam(value = "restaurant id that need to be fetch restatuant", required = true) @PathVariable("restaurantId") String restaurantId) {
        try {
            Restaurant restaurant = restaurantService.getRestaurantByID(restaurantId);
            if (restaurant == null) {
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Restaurant not found", null),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("", restaurant), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("=============================================================================");
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation("Create the new Restaurant")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public ResponseEntity<FoodAppResponse> createRestaurant(
            @ApiParam(value = "Restaurant json object that needs to be added to the database", required = true) @RequestBody Restaurant restaurant) {
        try {
            Boolean isInserted = restaurantService.createRestaurant(restaurant);
            if (!isInserted) {
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Not Inserted", null),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Inserted Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{restaurantId}", method = RequestMethod.PUT)
    @ApiOperation("update the Restaurant")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server issue"),
            @ApiResponse(code = 404, message = "Restaurant not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    public ResponseEntity<?> updateRestaurant(
            @ApiParam(value = "existing restaurant id", required = true) @PathVariable("restaurantId") String restaurantId,
            @ApiParam(value = "new restaurant object for replace", required = true) @RequestBody Restaurant restaurant) {
        try {
            Boolean isInserted = restaurantService.updateRestaurant(restaurant, restaurantId);
            if (!isInserted) {
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Not Updated", null),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Updated Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{restaurantId}", method = RequestMethod.DELETE)
    @ApiOperation("Delete the Restaurant")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid restaurantId supplied"),
            @ApiResponse(code = 404, message = "restaurant not found") })
    public ResponseEntity<FoodAppResponse> deleteRestaurant(
            @ApiParam(value = "restaurant id for delete", required = true) @PathVariable("restaurantId") String restaurantId) {
        try {
            Boolean isInserted = restaurantService.deleteRestaurant(restaurantId);
            if (!isInserted) {
                return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Not deleted", null),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Deleted Successfully", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<FoodAppResponse>(new FoodAppResponse("Internal Server Issue", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
