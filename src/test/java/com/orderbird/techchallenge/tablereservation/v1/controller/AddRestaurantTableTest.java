package com.orderbird.techchallenge.tablereservation.v1.controller;

import com.google.gson.Gson;
import com.orderbird.techchallenge.tablereservation.TableReservationApplicationTests;
import com.orderbird.techchallenge.tablereservation.v1.message.RestaurantTableRequest;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test Class for {@link RestaurantTableController}
 */


public class AddRestaurantTableTest extends TableReservationApplicationTests {

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testAddNewTable_whenInvokedWithCorrectRequest_shouldReturnTheTableId() throws Exception {

        RestaurantTableRequest restaurantTableRequest = RestaurantTableRequest.builder()
                .restaurantId(1L)
                .tableName("NewTable").build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(restaurantTableRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", equalTo(4)));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testAddNewTable_whenInvokedWithoutTableNameParameter_shouldThrowError() throws Exception {

        RestaurantTableRequest restaurantTableRequest = RestaurantTableRequest.builder()
                .restaurantId(1L).build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(restaurantTableRequest)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testAddNewTable_whenInvokedWithoutRestaurantIdParameter_shouldThrowError() throws Exception {

        RestaurantTableRequest restaurantTableRequest = RestaurantTableRequest.builder()
                .tableName("NewTable").build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(restaurantTableRequest)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testAddNewTable_whenInvokedWithNonExistingRestaurantIdParameter_shouldThrowError() throws Exception {

        RestaurantTableRequest restaurantTableRequest = RestaurantTableRequest.builder()
                .restaurantId(2L)
                .tableName("NewTable").build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(restaurantTableRequest)))
                .andExpect(status().is4xxClientError());
    }
}
