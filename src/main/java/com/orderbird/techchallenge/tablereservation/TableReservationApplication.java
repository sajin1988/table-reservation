package com.orderbird.techchallenge.tablereservation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TableReservationApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(TableReservationApplication.class)
				.run(args);
	}
}
