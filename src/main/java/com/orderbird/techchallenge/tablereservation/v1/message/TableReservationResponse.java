package com.orderbird.techchallenge.tablereservation.v1.message;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Table Reservation Response to be returned
 */
@Data
@Builder
@ApiModel
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TableReservationResponse {

    private Long id;

    private String name;

    private List<Reservation> reservations;

}
