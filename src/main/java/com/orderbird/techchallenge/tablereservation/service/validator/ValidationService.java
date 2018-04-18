package com.orderbird.techchallenge.tablereservation.service.validator;

import com.orderbird.techchallenge.tablereservation.entity.Restaurant;
import com.orderbird.techchallenge.tablereservation.entity.RestaurantTable;

import java.util.Date;
import java.util.Optional;

/**
 * Service for detailed parameter validation.
 */
public interface ValidationService {

    /**
     * validates the table name and restaurant.
     *
     * @param tableName
     * @param restaurant
     */
    void validateTableNameAndRestaurant(String tableName, Optional<Restaurant> restaurant);

    /**
     * validates the reservation time.
     *
     * @param startTime
     * @param endTime
     */
    void validateReservationTime(Date startTime, Date endTime);

    /**
     * validates the table.
     *
     * @param {@link RestaurantTable}
     */
    void validateTable(Optional<RestaurantTable> restaurantTable);


}
