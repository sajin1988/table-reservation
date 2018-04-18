package com.orderbird.techchallenge.tablereservation.repository;

import com.orderbird.techchallenge.tablereservation.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository operations for {@link Restaurant}
 */
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

}