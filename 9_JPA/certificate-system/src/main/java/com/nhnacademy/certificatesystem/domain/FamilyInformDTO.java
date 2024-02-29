package com.nhnacademy.certificatesystem.domain;

import com.nhnacademy.certificatesystem.entity.FamilyRelationship;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class FamilyInformDTO {

    private String relation;
    private String familyName;
    private String birthDate;
    private String resistrationNumber;
    private String gender;



    public static FamilyInformDTO toDTO(FamilyRelationship familyRelationship){
        LocalDate date = familyRelationship.getFamilyResident().getBirthDate().toLocalDate();
        String year = String.valueOf(date.getYear());
        String month = String.format("%02d", date.getMonthValue());
        String day = String.format("%02d", date.getDayOfMonth());
        return FamilyInformDTO.builder()
                .relation(familyRelationship.getFamilyRelationshipCode())
                .familyName(familyRelationship.getFamilyResident().getName())
                .birthDate(year+"년"+month+"월"+day+"일")
                .resistrationNumber(familyRelationship.getFamilyResident().getRegistrationNumber().substring(0,6)+"-*******")
                .gender(familyRelationship.getFamilyResident().getGender())
                .build();
    }
}
