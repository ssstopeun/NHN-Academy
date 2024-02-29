package com.nhnacademy.certificatesystem.repository;


import com.nhnacademy.certificatesystem.entity.FamilyRelationship;
import com.nhnacademy.certificatesystem.entity.Resident;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
    @Override
    ArrayList<Resident> findAll();

    Resident findByResidentId(int residentId);

    boolean existsByResidentId(int residentId);

//    @Transactional
    void deleteByResidentId(int residentId);


}
