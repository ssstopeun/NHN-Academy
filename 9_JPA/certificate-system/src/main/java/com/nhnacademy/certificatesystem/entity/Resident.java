package com.nhnacademy.certificatesystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "resident")
public class Resident {

    @Id
    @Column(name = "resident_serial_number")
    private int residentId;

    @Column(name = "name")
    private String name;

    @Column(name = "resident_registration_number")
    private String registrationNumber;

    @Column(name = "gender_code")
    private String gender;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code")
    private String birthPlaceCode;

    @Column(name = "registration_base_address")
    private String registrationAddress;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code")
    private String deathPlaceCode;

    @Column(name = "death_place_address")
    private String deathAddress;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "resident")
    private List<Certification> certificationList;


}
