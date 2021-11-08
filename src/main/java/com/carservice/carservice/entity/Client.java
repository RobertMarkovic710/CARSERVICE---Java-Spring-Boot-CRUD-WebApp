package com.carservice.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Data //ovo ima u sebi Gettere i Settere
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", allocationSize = 10)
    private Long id;

    @Column(name = "oib")
    private Long oib;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Embedded //entity address se ovdje injecta
    private Address address;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Vehicle> carList = new ArrayList<>();

    @Column(name = "e_mail")
    private String e_mail;
}
