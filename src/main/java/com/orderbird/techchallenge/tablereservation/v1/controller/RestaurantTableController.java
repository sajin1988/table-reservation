package com.orderbird.techchallenge.tablereservation.v1.controller;

import com.orderbird.techchallenge.tablereservation.entity.Restaurant;
import com.orderbird.techchallenge.tablereservation.repository.RestaurantRepository;
import com.orderbird.techchallenge.tablereservation.service.RestaurantTableService;
import com.orderbird.techchallenge.tablereservation.v1.message.RestaurantTableRequest;
import com.orderbird.techchallenge.tablereservation.v1.message.RestaurantTableResponse;
import com.orderbird.techchallenge.tablereservation.v1.message.TableReservationRequest;
import com.orderbird.techchallenge.tablereservation.v1.message.TableReservationResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Controller for listing the payment methods.
 */
@Slf4j
@RestController
public class RestaurantTableController extends BaseController {

    @Resource
    private RestaurantTableService restaurantTableService;

    @Resource
    private RestaurantRepository restaurantRepository;

    @ApiOperation(value = "Add a new table to the restaurant", response = RestaurantTableResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTTP_MESSAGE_200, response = RestaurantTableResponse.class),
    })
    @PostMapping(value = "/table", produces = APPLICATION_JSON)
    public ResponseEntity<RestaurantTableResponse> addNewTable(@Valid @RequestBody RestaurantTableRequest restaurantTableRequest) {

        log.debug("addNewTable: restaurantTableRequest=<{}>", restaurantTableRequest);

        RestaurantTableResponse restaurantTableResponse = RestaurantTableResponse.builder()
                .id(restaurantTableService.addNewTable(restaurantTableRequest.getTableName(), restaurantTableRequest.getRestaurantId())).build();
        return new ResponseEntity<>(restaurantTableResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "save restaurant", response = Long.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTTP_MESSAGE_200, response = Long.class),
    })
    @PostMapping(value = "/restaurant", produces = APPLICATION_JSON)
    public ResponseEntity<Long> saveRestaurant(
            @ApiParam(value = "restaurant name")
            @RequestParam(value = "restaurantName", required = true) String restaurantName,
            @ApiParam(value = "restaurant address")
            @RequestParam(value = "restaurantAddress", required = true) String restaurantAddress) {

        log.debug("saveRestaurant: restaurantName=<{}> restaurantAddress=<{}>", restaurantName, restaurantAddress);

        Restaurant restaurant = Restaurant.builder()
                .restaurantName(restaurantName)
                .restaurantAddress(restaurantAddress).build();
        Long restaurantId = restaurantRepository.save(restaurant).getId();
        return new ResponseEntity<>(restaurantId, HttpStatus.OK);
    }

    @ApiOperation(value = "Make reservation for a table", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTTP_MESSAGE_200, response = String.class),
    })
    @PostMapping(value = "/table/{id}/reservation", produces = APPLICATION_JSON)
    public ResponseEntity<String> makeTableReservation(
            @ApiParam(value = "table id", required = true)
            @PathVariable("id") long tableId,
            @Valid @RequestBody TableReservationRequest tableReservationRequest) {

        log.debug("saveRestaurant: tableId=<{}> tableReservationRequest=<{}>", tableId, tableReservationRequest);

        String status = restaurantTableService.reserveTable(tableId, tableReservationRequest);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @ApiOperation(value = "Show reservations for a table", response = TableReservationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTTP_MESSAGE_200, response = TableReservationResponse.class),
    })
    @GetMapping(value = "/table/{id}", produces = APPLICATION_JSON)
    public ResponseEntity<TableReservationResponse> listTableReservations(
            @ApiParam(value = "table id", required = true)
            @PathVariable("id") long tableId) {

        log.debug("saveRestaurant: tableId=<{}>", tableId);

        TableReservationResponse tableReservationResponse = restaurantTableService.listTableReservations(tableId);
        return new ResponseEntity<>(tableReservationResponse, HttpStatus.OK);
    }

}
