package com.nhnacademy.certificatesystem.repository;

import com.nhnacademy.certificatesystem.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface FamilyCertificationRepository extends JpaRepository<FamilyRelationship, Long> {


    ArrayList<FamilyRelationship> findAllByBaseResident_ResidentId(int baseResidentId);
}

