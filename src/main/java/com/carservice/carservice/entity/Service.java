package com.carservice.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "service")
@Data
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", allocationSize = 10)
    private Long id;

    @Column
    String mechanicName;

    @Column
    Date date = new Date();

//    @Column
//    Time time = new Time();

    @Column
    String workDescription;

    @Column
    double price;

    @Column
    boolean paidOrNot;

    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;
}
