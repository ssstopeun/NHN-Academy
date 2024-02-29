package com.nhnacademy.certificatesystem.repository;

import com.nhnacademy.certificatesystem.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
//    List<Certification> findByResidentId(int residentId);
//    Optional<Certification> existsResidentBy(int residentId);


    boolean existsByResidentResidentId(int residentId);

    @Query("SELECT MAX(ci.certificationId) FROM Certification ci WHERE ci.certificationId <= 5000000000000000")
    Long getLastFamilyCertificationId();
}
