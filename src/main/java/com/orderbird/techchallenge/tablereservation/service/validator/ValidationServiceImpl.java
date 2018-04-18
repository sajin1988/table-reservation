package com.orderbird.techchallenge.tablereservation.service.validator;

import com.orderbird.techchallenge.tablereservation.entity.Restaurant;
import com.orderbird.techchallenge.tablereservation.entity.RestaurantTable;
import com.orderbird.techchallenge.tablereservation.exception.TableReservationApplicationException;
import com.orderbird.techchallenge.tablereservation.v1.message.TableReservationRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of {@link ValidationService} interface
 */
@Service
public class ValidationServiceImpl implements ValidationService {


    @Override
    public void validateTableNameAndRestaurant(String tableName, Optional<Restaurant> restaurant) {
        if(!restaurant.isPresent()){
            throw new TableReservationApplicationException("The restaurant reference provided is not valid");
        }
        if(StringUtils.isEmpty(tableName)){
            throw new TableReservationApplicationException("Table name cannot be null or blank");
        }
    }

    @Override
    public void validateReservationTime(Date startTime, Date endTime) {
        if(startTime.before(new Date())){
            throw new TableReservationApplicationException("Reservation time cannot be a past date");
        }
        if(endTime.before(startTime)){
            throw new TableReservationApplicationException("Reservation end time time cannot be before start time");
        }
    }

    @Override
    public void validateTable(Optional<RestaurantTable> restaurantTable) {
        if(!restaurantTable.isPresent()){
            throw new TableReservationApplicationException("The table you are trying to reserve is invalid");
        }
    }
}
