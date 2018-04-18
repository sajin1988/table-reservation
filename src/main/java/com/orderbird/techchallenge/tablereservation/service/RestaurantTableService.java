package com.orderbird.techchallenge.tablereservation.service;

import com.orderbird.techchallenge.tablereservation.v1.message.TableReservationRequest;
import com.orderbird.techchallenge.tablereservation.v1.message.TableReservationResponse;

/**
 * Service for managing the tables in Restaurant.
 */
public interface RestaurantTableService {

    /**
     * Adds a new table to the restaurant.
     *
     * @param tableName
     * @param restaurantId
     * @return the table id
     */
    Long addNewTable(String tableName, Long restaurantId);

    /**
     * makes a table reservation.
     *
     * @param tableId
     * @param {@link  TableReservationRequest}
     * @return the reservation status
     */
    String reserveTable(long tableId, TableReservationRequest tableReservationRequest);

    /**
     * Lists the table reservations
     *
     * @param tableId
     * @return the {@link TableReservationResponse}
     */
    TableReservationResponse listTableReservations(long tableId);
}
