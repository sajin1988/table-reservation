package com.orderbird.techchallenge.tablereservation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
@Table(name = "Reservations", schema = "ORDERBIRD")
public class TableReservation {

    @Id
    @Column(name = "TR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TR_T_ID")
    private RestaurantTable restaurantTable;

    @Column(name = "TR_CUSTOMER_NAME")
    private String customerName;

    @Column(name = "TR_START_TIME")
    private Date reservationStartTime;

    @Column(name = "TR_END_TIME")
    private Date reservationEndTime;

    @Column(name = "TR_STATUS")
    private String reservationStatus;

}
