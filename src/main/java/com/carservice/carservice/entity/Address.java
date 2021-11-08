package com.carservice.carservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable //jer se ona ugrađuje
@Getter
@Setter
public class Address {
    //embeddable klasa NE smije imati @id
    @Column(name = "cityPostNumber")
    private int postNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;
}
