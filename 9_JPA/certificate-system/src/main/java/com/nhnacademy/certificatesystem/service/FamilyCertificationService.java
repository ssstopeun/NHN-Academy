package com.nhnacademy.certificatesystem.service;

import com.nhnacademy.certificatesystem.entity.FamilyRelationship;
import com.nhnacademy.certificatesystem.entity.Resident;
import com.nhnacademy.certificatesystem.repository.FamilyCertificationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FamilyCertificationService {

    private final FamilyCertificationRepository familyCertificationRepository;

    public FamilyCertificationService(FamilyCertificationRepository familyCertificationRepository) {
        this.familyCertificationRepository = familyCertificationRepository;
    }
    public ArrayList<FamilyRelationship> getAllFamilyRelationshipByBaseResidentId(int baseResidentId){
        return familyCertificationRepository.findAllByBaseResident_ResidentId(baseResidentId);
    }
}
