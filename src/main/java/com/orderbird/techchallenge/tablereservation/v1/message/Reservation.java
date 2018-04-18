package com.orderbird.techchallenge.tablereservation.v1.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orderbird.techchallenge.tablereservation.entity.TableReservation;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Table Reservation data
 */
@Data
@Builder
@ApiModel
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("from")
    private String startTime;

    @JsonProperty("to")
    private String endTime;

    public static Reservation mapFromModel(TableReservation tableReservation){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return Reservation.builder()
                .customerName(tableReservation.getCustomerName())
                .startTime(dateFormat.format(tableReservation.getReservationStartTime()))
                .endTime(dateFormat.format(tableReservation.getReservationEndTime())).build();
    }


}
