package com.nhnacademy.certificatesystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "certificate_issue")
public class Certification {
    @Id
    @Column(name = "certificate_confirmation_number")
    private long certificationId;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;


    @Column(name = "certificate_type_code")
    private String certificationTypeCode;

    @Column(name = "certificate_issue_date")
    private LocalDate certificateIssueDate;

}
