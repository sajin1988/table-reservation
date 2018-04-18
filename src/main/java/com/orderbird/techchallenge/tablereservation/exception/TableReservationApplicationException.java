package com.orderbird.techchallenge.tablereservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TableReservationApplicationException extends RuntimeException {

    public TableReservationApplicationException(String message) {
        super(message);
    }
}
