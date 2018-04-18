package com.orderbird.techchallenge.tablereservation;

import com.orderbird.techchallenge.tablereservation.entity.Restaurant;
import com.orderbird.techchallenge.tablereservation.entity.RestaurantTable;
import com.orderbird.techchallenge.tablereservation.entity.TableReservation;
import com.orderbird.techchallenge.tablereservation.repository.RestaurantRepository;
import com.orderbird.techchallenge.tablereservation.repository.RestaurantTableRepository;
import com.orderbird.techchallenge.tablereservation.repository.TableReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TableReservationApplicationTests {

	private static final Charset UTF_8 = Charset.forName("utf8");
	protected static final MediaType CONTENT_TYPE_HAL_JSON = new MediaType(MediaTypes.HAL_JSON.getType(),
			MediaTypes.HAL_JSON.getSubtype(), UTF_8);

	protected DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	protected MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	@Resource
	private RestaurantRepository restaurantRepository;

	@Resource
	private TableReservationRepository tableReservationRepository;

	@Resource
	private RestaurantTableRepository restaurantTableRepository;

	protected MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
	);

	@Before
	public void setup() throws ParseException {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		tableReservationRepository.deleteAll();
		restaurantTableRepository.deleteAll();
		restaurantRepository.deleteAll();

		Restaurant restaurant = Restaurant.builder()
				.id(1L)
				.restaurantName("Restaurant")
				.build();
		restaurantRepository.save(restaurant);

		RestaurantTable restaurantTableOne = RestaurantTable.builder()
				.id(1L)
				.tableName("tableOne")
				.restaurant(restaurant)
				.build();

		RestaurantTable restaurantTableTwo = RestaurantTable.builder()
				.id(2L)
				.tableName("tableTwo")
				.restaurant(restaurant)
				.build();

		RestaurantTable restaurantTableThree = RestaurantTable.builder()
				.id(3L)
				.tableName("tableThree")
				.restaurant(restaurant)
				.build();

		restaurantTableRepository.save(restaurantTableOne);
		restaurantTableRepository.save(restaurantTableTwo);
		restaurantTableRepository.save(restaurantTableThree);

		TableReservation tableReservationOne = TableReservation.builder()
				.id(1L)
				.customerName("CustomerOne")
				.restaurantTable(restaurantTableOne)
				.reservationStartTime(dateFormat.parse("2018-04-20 10:00:00.000"))
				.reservationEndTime(dateFormat.parse("2018-04-20 11:00:00.000"))
				.reservationStatus("BOOKED").build();
		TableReservation tableReservationTwo = TableReservation.builder()
				.id(2L)
				.customerName("CustomerTwo")
				.restaurantTable(restaurantTableOne)
				.reservationStartTime(dateFormat.parse("2018-04-20 15:00:00.000"))
				.reservationEndTime(dateFormat.parse("2018-04-20 16:00:00.000"))
				.reservationStatus("BOOKED").build();
		TableReservation tableReservationThree = TableReservation.builder()
				.id(3L)
				.customerName("CustomerThree")
				.restaurantTable(restaurantTableOne)
				.reservationStartTime(dateFormat.parse("2018-04-20 18:00:00.000"))
				.reservationEndTime(dateFormat.parse("2018-04-20 19:00:00.000"))
				.reservationStatus("BOOKED").build();
		TableReservation tableReservationFour = TableReservation.builder()
				.id(4L)
				.customerName("CustomerFour")
				.restaurantTable(restaurantTableTwo)
				.reservationStartTime(dateFormat.parse("2018-04-20 10:00:00.000"))
				.reservationEndTime(dateFormat.parse("2018-04-20 11:00:00.000"))
				.reservationStatus("BOOKED").build();
		TableReservation tableReservationFive = TableReservation.builder()
				.id(5L)
				.customerName("CustomerFive")
				.restaurantTable(restaurantTableThree)
				.reservationStartTime(dateFormat.parse("2018-04-20 10:00:00.000"))
				.reservationEndTime(dateFormat.parse("2018-04-20 11:00:00.000"))
				.reservationStatus("BOOKED").build();

		tableReservationRepository.save(tableReservationOne);
		tableReservationRepository.save(tableReservationTwo);
		tableReservationRepository.save(tableReservationThree);
		tableReservationRepository.save(tableReservationFour);
		tableReservationRepository.save(tableReservationFive);
	}


	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
	public void contextLoads() {
	}

}
