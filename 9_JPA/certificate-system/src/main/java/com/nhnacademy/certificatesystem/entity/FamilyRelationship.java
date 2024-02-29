package com.nhnacademy.certificatesystem.entity;

import com.nhnacademy.certificatesystem.repository.ResidentRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="family_relationship")
public class FamilyRelationship {

    @EmbeddedId
    private Pk pk;

    @Column(name = "family_relationship_code", nullable = false, length = 20)
    private String familyRelationshipCode;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_resident_serial_number")
    private Resident baseResident;

    @MapsId("familyResidentSerialNumber")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_resident_serial_number")
    private Resident familyResident;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "base_resident_serial_number")
        private int baseResidentSerialNumber;

        @Column(name = "family_resident_serial_number")
        private int familyResidentSerialNumber;
    }


}
