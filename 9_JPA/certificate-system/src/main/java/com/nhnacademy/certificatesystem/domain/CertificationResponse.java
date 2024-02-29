package com.nhnacademy.certificatesystem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificationResponse {
    private long id;
    private String residentId;
    private String certificateTypeCode;
    private LocalDate certificateIssueDate;
}
