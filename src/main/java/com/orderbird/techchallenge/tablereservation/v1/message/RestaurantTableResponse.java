package com.orderbird.techchallenge.tablereservation.v1.message;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Response class for add new table
 */
@Data
@Builder
@ApiModel
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTableResponse {

    private Long id;

}
