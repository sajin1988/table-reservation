package com.orderbird.techchallenge.tablereservation.v1.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Timeslot class with start time and end time for the reservation
 */
@Data
@Builder
@ApiModel
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot {

    @ApiModelProperty(notes = "Start time of the reservation", required = true)
    @NotNull(message = "Start time of the reservation must be provided")
    private String from;

    @ApiModelProperty(notes = "End time of the reservation", required = true)
    @NotNull(message = "End time of the reservation must be provided")
    private String to;

}
