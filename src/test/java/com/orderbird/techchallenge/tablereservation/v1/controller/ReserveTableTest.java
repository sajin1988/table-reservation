package com.orderbird.techchallenge.tablereservation.v1.controller;

import com.google.gson.Gson;
import com.orderbird.techchallenge.tablereservation.TableReservationApplicationTests;
import com.orderbird.techchallenge.tablereservation.entity.Restaurant;
import com.orderbird.techchallenge.tablereservation.entity.RestaurantTable;
import com.orderbird.techchallenge.tablereservation.entity.TableReservation;
import com.orderbird.techchallenge.tablereservation.repository.TableReservationRepository;
import com.orderbird.techchallenge.tablereservation.v1.message.TableReservationRequest;
import com.orderbird.techchallenge.tablereservation.v1.message.TimeSlot;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import javax.annotation.Resource;

import static com.google.common.truth.Truth.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test Class for {@link RestaurantTableController}
 */


public class ReserveTableTest extends TableReservationApplicationTests {

    @Resource
    TableReservationRepository tableReservationRepository;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testMakeTableReservation_whenInvokedWithCorrectRequest_shouldReturnTheSuccessResponse() throws Exception {

        TimeSlot timeSlot = TimeSlot.builder()
                .from("2018-04-20 13:00:00.000")
                .to("2018-04-20 14:00:00.000").build();
        TableReservationRequest tableReservationRequest = TableReservationRequest.builder()
                .customerName("NewCustomer")
                .timeSlot(timeSlot).build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table/1/reservation")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(tableReservationRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", equalTo("Booking successful")));

        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .restaurantName("Restaurant")
                .build();

        RestaurantTable restaurantTableOne = RestaurantTable.builder()
                .id(1L)
                .tableName("tableOne")
                .restaurant(restaurant)
                .build();

        TableReservation tableReservation = tableReservationRepository.getByRestaurantTableAndReservationStatusAndCustomerName
                (restaurantTableOne, "BOOKED", "NewCustomer").get(0);
        assertThat(tableReservation.getId()).isEqualTo(6);
        assertThat(dateFormat.format(tableReservation.getReservationStartTime())).isEqualTo("2018-04-20 13:00:00.000");
        assertThat(dateFormat.format(tableReservation.getReservationEndTime())).isEqualTo("2018-04-20 14:00:00.000");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testMakeTableReservation_whenInvokedWithoutFromTimeParameter_shouldThrowError() throws Exception {

        TimeSlot timeSlot = TimeSlot.builder()
                .to("2018-04-20 14:00:00.000").build();
        TableReservationRequest tableReservationRequest = TableReservationRequest.builder()
                .customerName("NewCustomer")
                .timeSlot(timeSlot).build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table/1/reservation")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(tableReservationRequest)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testMakeTableReservation_whenInvokedWithoutToTimeParameter_shouldThrowError() throws Exception {

        TimeSlot timeSlot = TimeSlot.builder()
                .from("2018-04-20 13:00:00.000").build();
        TableReservationRequest tableReservationRequest = TableReservationRequest.builder()
                .customerName("NewCustomer")
                .timeSlot(timeSlot).build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table/1/reservation")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(tableReservationRequest)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testMakeTableReservation_whenInvokedWithFromTimeLessThanCurrentTime_shouldThrowError() throws Exception {

        TimeSlot timeSlot = TimeSlot.builder()
                .from("2018-04-10 13:00:00.000")
                .to("2018-04-10 14:00:00.000").build();
        TableReservationRequest tableReservationRequest = TableReservationRequest.builder()
                .customerName("NewCustomer")
                .timeSlot(timeSlot).build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table/1/reservation")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(tableReservationRequest)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testMakeTableReservation_whenInvokedWithToTimeLessThanFromTime_shouldThrowError() throws Exception {

        TimeSlot timeSlot = TimeSlot.builder()
                .from("2018-04-20 13:00:00.000")
                .to("2018-04-20 12:00:00.000").build();
        TableReservationRequest tableReservationRequest = TableReservationRequest.builder()
                .customerName("NewCustomer")
                .timeSlot(timeSlot).build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table/1/reservation")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(tableReservationRequest)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testMakeTableReservation_whenInvokedWithoutCustomerName_shouldThrowError() throws Exception {

        TimeSlot timeSlot = TimeSlot.builder()
                .from("2018-04-20 13:00:00.000")
                .to("2018-04-20 14:00:00.000").build();
        TableReservationRequest tableReservationRequest = TableReservationRequest.builder()
                .timeSlot(timeSlot).build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table/1/reservation")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(tableReservationRequest)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testMakeTableReservation_whenInvokedWithNonExistingTableId_shouldThrowError() throws Exception {

        TimeSlot timeSlot = TimeSlot.builder()
                .from("2018-04-20 13:00:00.000")
                .to("2018-04-20 14:00:00.000").build();
        TableReservationRequest tableReservationRequest = TableReservationRequest.builder()
                .customerName("NewCustomer")
                .timeSlot(timeSlot).build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table/4/reservation")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(tableReservationRequest)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testMakeTableReservation_whenThereIsAReservationExistsForTheTime_shouldRespondWithConflictMessage() throws Exception {

        TimeSlot timeSlot = TimeSlot.builder()
                .from("2018-04-20 10:00:00.000")
                .to("2018-04-20 11:00:00.000").build();
        TableReservationRequest tableReservationRequest = TableReservationRequest.builder()
                .customerName("NewCustomer")
                .timeSlot(timeSlot).build();
        Gson gson = new Gson();

        mockMvc.perform(post("/api/v1/table/1/reservation")
                .contentType(CONTENT_TYPE_HAL_JSON).content(gson.toJson(tableReservationRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", equalTo("CONFLICT")));
    }
}
