package com.orderbird.techchallenge.tablereservation.service;

import com.orderbird.techchallenge.tablereservation.entity.Restaurant;
import com.orderbird.techchallenge.tablereservation.entity.RestaurantTable;
import com.orderbird.techchallenge.tablereservation.entity.TableReservation;
import com.orderbird.techchallenge.tablereservation.repository.RestaurantRepository;
import com.orderbird.techchallenge.tablereservation.repository.RestaurantTableRepository;
import com.orderbird.techchallenge.tablereservation.repository.TableReservationRepository;
import com.orderbird.techchallenge.tablereservation.service.validator.ValidationService;
import com.orderbird.techchallenge.tablereservation.v1.message.Reservation;
import com.orderbird.techchallenge.tablereservation.v1.message.TableReservationRequest;
import com.orderbird.techchallenge.tablereservation.v1.message.TableReservationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * implementation of {@link RestaurantTableService}
 */
@Slf4j
@Service
@Transactional
public class RestaurantTableServiceImpl implements RestaurantTableService {

    @Resource
    private ValidationService validationService;

    @Resource
    private RestaurantRepository restaurantRepository;

    @Resource
    private RestaurantTableRepository restaurantTableRepository;

    @Resource
    private TableReservationRepository tableReservationRepository;

    public final String BOOKED = "BOOKED";
    public final String CONFLICT = "CONFLICT";
    public final String BOOKING_SUCCESSFUL = "Booking successful";


    @Override
    public Long addNewTable(String tableName, Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        validationService.validateTableNameAndRestaurant(tableName, restaurant);
        RestaurantTable restaurantTable = RestaurantTable.builder()
                .restaurant(restaurant.get())
                .tableName(tableName)
                .build();
        restaurantTableRepository.save(restaurantTable);
        return restaurantTable.getId();
    }

    @Override
    public String reserveTable(long tableId, TableReservationRequest tableReservationRequest) {
        Optional<RestaurantTable> restaurantTable = restaurantTableRepository.findById(tableId);
        validationService.validateTable(restaurantTable);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = dateFormat.parse(tableReservationRequest.getTimeSlot().getFrom());
            endTime = dateFormat.parse(tableReservationRequest.getTimeSlot().getTo());
        } catch (ParseException ignore) {
        }
        validationService.validateReservationTime(startTime, endTime);
        Optional<TableReservation> tableReservation = tableReservationRepository.findByRestaurantTableAndReservationStartTimeAndReservationEndTimeAndReservationStatus
                (restaurantTable.get(), startTime, endTime, BOOKED);
        if (tableReservation.isPresent()) {
            return CONFLICT;
        }
        TableReservation tableReservationEntity = TableReservation.builder()
                .customerName(tableReservationRequest.getCustomerName())
                .reservationStartTime(startTime)
                .reservationEndTime(endTime)
                .restaurantTable(restaurantTable.get())
                .reservationStatus(BOOKED)
                .build();
        tableReservationRepository.save(tableReservationEntity);
        return BOOKING_SUCCESSFUL;
    }

    @Override
    public TableReservationResponse listTableReservations(long tableId) {
        Optional<RestaurantTable> restaurantTable = restaurantTableRepository.findById(tableId);
        validationService.validateTable(restaurantTable);
        List<Reservation> reservationList = tableReservationRepository.getByRestaurantTableAndReservationStatusOrderById
                (restaurantTable.get(), "BOOKED").stream()
                .map(Reservation::mapFromModel)
                .collect(Collectors.toList());
        return TableReservationResponse.builder()
                .name(restaurantTable.get().getTableName())
                .id(tableId)
                .reservations(reservationList).build();
    }
}
