package com.orderbird.techchallenge.tablereservation.repository;

import com.orderbird.techchallenge.tablereservation.entity.RestaurantTable;
import com.orderbird.techchallenge.tablereservation.entity.TableReservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repository operations for {@link TableReservation}
 */
public interface TableReservationRepository extends CrudRepository<TableReservation, Long> {

    Optional<TableReservation> findByRestaurantTableAndReservationStartTimeAndReservationEndTimeAndReservationStatus
            (RestaurantTable restaurantTable, Date startTime, Date endTime, String reservationStatus);

    List<TableReservation> getByRestaurantTableAndReservationStatusOrderById(RestaurantTable restaurantTable, String status);

    List<TableReservation> getByRestaurantTableAndReservationStatusAndCustomerName
            (RestaurantTable restaurantTable, String status, String cutomerName);
}