package com.nhnacademy.certificatesystem.domain;

import org.springframework.beans.factory.annotation.Value;



public interface ResidentDto{
    @Value("#{target.residentId}")
    int getResidentId();


}
