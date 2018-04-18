package com.orderbird.techchallenge.tablereservation.v1.controller;

import com.orderbird.techchallenge.tablereservation.TableReservationApplicationTests;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test Class for {@link RestaurantTableController}
 */


public class ShowTableReservationsTest extends TableReservationApplicationTests {

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testListTableReservations_whenInvokedWithCorrectRequest_shouldReturnTheResponse() throws Exception {

        mockMvc.perform(get("/api/v1/table/1")
                .contentType(CONTENT_TYPE_HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("tableOne")))
                .andExpect(jsonPath("$.reservations[0].customer_name", equalTo("CustomerOne")))
                .andExpect(jsonPath("$.reservations[0].from", equalTo("2018-04-20 10:00:00.000")))
                .andExpect(jsonPath("$.reservations[0].to", equalTo("2018-04-20 11:00:00.000")))
                .andExpect(jsonPath("$.reservations[1].customer_name", equalTo("CustomerTwo")))
                .andExpect(jsonPath("$.reservations[1].from", equalTo("2018-04-20 15:00:00.000")))
                .andExpect(jsonPath("$.reservations[1].to", equalTo("2018-04-20 16:00:00.000")))
                .andExpect(jsonPath("$.reservations[2].customer_name", equalTo("CustomerThree")))
                .andExpect(jsonPath("$.reservations[2].from", equalTo("2018-04-20 18:00:00.000")))
                .andExpect(jsonPath("$.reservations[2].to", equalTo("2018-04-20 19:00:00.000")));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testListTableReservations_whenInvokedWithCorrectRequestForTableTwo_shouldReturnTheResponse() throws Exception {

        mockMvc.perform(get("/api/v1/table/2")
                .contentType(CONTENT_TYPE_HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.name", equalTo("tableTwo")))
                .andExpect(jsonPath("$.reservations[0].customer_name", equalTo("CustomerFour")))
                .andExpect(jsonPath("$.reservations[0].from", equalTo("2018-04-20 10:00:00.000")))
                .andExpect(jsonPath("$.reservations[0].to", equalTo("2018-04-20 11:00:00.000")));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testListTableReservations_whenInvokedWithNonExistingTableId_shouldThrowError() throws Exception {

        mockMvc.perform(get("/api/v1/table/5")
                .contentType(CONTENT_TYPE_HAL_JSON))
                .andExpect(status().is4xxClientError());
    }

}
