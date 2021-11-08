package com.carservice.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", allocationSize = 10)
    private Long id;

    @Column(name = "car_type")
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name = "year")
    private int year;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;

    @Column(name = "paint")
    private String paint;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
    //https://www.javaguides.net/2019/08/jpa-hibernate-one-to-many-bidirectional-mapping-example.html

    @OneToMany
    @JoinColumn(name = "serviceId")
    private List<Service> serviceList = new ArrayList<>();
}
