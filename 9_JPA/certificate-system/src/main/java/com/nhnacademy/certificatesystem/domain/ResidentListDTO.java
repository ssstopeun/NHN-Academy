package com.nhnacademy.certificatesystem.domain;

import com.nhnacademy.certificatesystem.entity.Certification;
import com.nhnacademy.certificatesystem.entity.Resident;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class ResidentListDTO {
    @Id
    private int residentId;
    private String name;

    //    private Integer birthCertificate = null;
    //    private Integer deathCertificate = null;

    private List<Certification> certificationList;

    public static ResidentListDTO toDTO(Resident resident){
        return ResidentListDTO.builder()
            .residentId(resident.getResidentId())
                .name(resident.getName())
                .certificationList(resident.getCertificationList())
                .build();
    }
}