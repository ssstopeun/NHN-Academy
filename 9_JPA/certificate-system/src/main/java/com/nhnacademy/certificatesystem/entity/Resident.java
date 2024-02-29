package com.nhnacademy.certificatesystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "resident")
public class Resident {

    @Id
    @Column(name = "resident_serial_number")
    private int residentId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "resident_registration_number", nullable = false, length = 300)
    private String registrationNumber;

    @Column(name = "gender_code", nullable = false, length = 20)
    private String gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code", nullable = false, length = 20)
    private String birthPlaceCode;

    @Column(name = "registration_base_address", nullable = false, length = 500)
    private String registrationAddress;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code", length = 20)
    private String deathPlaceCode;

    @Column(name = "death_place_address", length = 500)
    private String deathAddress;

}
