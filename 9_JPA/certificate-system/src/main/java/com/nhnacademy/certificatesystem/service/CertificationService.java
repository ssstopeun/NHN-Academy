package com.nhnacademy.certificatesystem.service;

import com.nhnacademy.certificatesystem.repository.CertificationRepository;
import org.springframework.stereotype.Service;

@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;

    public CertificationService(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

//    public List<Certification> getCertificationList(int residentId) throws Exception {
//        Optional<Certification> certification = certificationRepository.existsResidentBy(residentId);
//        if(certification.isEmpty()){
//            return null;
//        }
//
//        return certificationRepository.findByResidentId(residentId);
//    }

    public boolean exists(Integer residentId){
        return certificationRepository.existsByResidentResidentId(residentId);
    }

    public Long getFamilyCertificationId() {
       return certificationRepository.getLastFamilyCertificationId()+1;
    }
}
