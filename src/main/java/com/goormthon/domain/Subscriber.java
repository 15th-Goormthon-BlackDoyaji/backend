package com.goormthon.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique= true)
    private String email;

    @Enumerated(value= EnumType.STRING)
    private Education education;

    @Enumerated(value= EnumType.STRING)
    private Region region;

    @Enumerated(value= EnumType.STRING)
    private Residency residency;

    @Enumerated(value= EnumType.STRING)
    private Interest interest;


    public Subscriber(String email, Education education, Region region, Residency residency, Interest interest) {
       this(null, email, education, region, residency, interest);
    }
}
