package com.nhnacademy.certificatesystem.domain;

import com.nhnacademy.certificatesystem.entity.Resident;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class FamilyInformLayerDTO {

    @Id
    private int residentId;
    private String name;

    private String address;

    private LocalDate issueDate;

    private String certificationId;


    public static FamilyInformLayerDTO toDTO(Resident resident, Long certificationId){
        return FamilyInformLayerDTO.builder()
                .residentId(resident.getResidentId())
                .name(resident.getName())
                .address(resident.getRegistrationAddress())
                .issueDate(LocalDate.now())
                .certificationId(String.valueOf(certificationId).substring(0,8)+"-"+String.valueOf(certificationId).substring(9))
                .build();
    }
}
