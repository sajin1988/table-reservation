package com.orderbird.techchallenge.tablereservation.v1.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request class for making table reservation
 */
@Data
@Builder
@ApiModel
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TableReservationRequest {

    @ApiModelProperty(notes = "Name of the customer who booked the table.", required = true)
    @NotNull(message = "customerName must be provided")
    @Size(max = 40, message = "customer name must be 40 characters max")
    private String customerName;

    @ApiModelProperty(notes = "Time slot for the reservation", required = true)
    @NotNull(message = "timeSlot must be provided")
    @Valid
    private TimeSlot timeSlot;

}
