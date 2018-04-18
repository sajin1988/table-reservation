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
 * Request class for add new table
 */
@Data
@Builder
@ApiModel
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTableRequest {

    @ApiModelProperty(notes = "Table name in the restaurant.", required = true)
    @NotNull(message = "tableName must be provided")
    private String tableName;

    @ApiModelProperty(notes = "Id of the restaurant", required = true)
    @NotNull(message = "restaurantId must be provided")
    private Long restaurantId;

}
