package com.nhnacademy.certificatesystem.repository;

import com.nhnacademy.certificatesystem.entity.Certification;
import com.nhnacademy.certificatesystem.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
//    List<Certification> findByResidentId(int residentId);
//    Optional<Certification> existsResidentBy(int residentId);


    boolean existsByResidentResidentId(int residentId);
}
