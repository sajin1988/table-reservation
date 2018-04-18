package com.orderbird.techchallenge.tablereservation.repository;

import com.orderbird.techchallenge.tablereservation.entity.RestaurantTable;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository operations for {@link RestaurantTable}
 */
public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Long> {

}