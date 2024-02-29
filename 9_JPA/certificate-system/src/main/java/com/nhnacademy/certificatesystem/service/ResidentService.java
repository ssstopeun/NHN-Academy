package com.nhnacademy.certificatesystem.service;

import com.nhnacademy.certificatesystem.domain.FamilyInformDTO;
import com.nhnacademy.certificatesystem.entity.FamilyRelationship;
import com.nhnacademy.certificatesystem.entity.Resident;
import com.nhnacademy.certificatesystem.exception.ResidentNotFoundException;
import com.nhnacademy.certificatesystem.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@Transactional
public class ResidentService {

    private final ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }
    @Transactional(readOnly = true)
    public Page<Resident> getResidents(Pageable pageable) {
        return residentRepository.findAll(pageable);
    }

    public boolean exists(int residentId){
        return residentRepository.existsByResidentId(residentId);
    }

    public Resident getResident(int residentId){
        return exists(residentId)?residentRepository.findByResidentId(residentId) : null;
    }

    public void deleteResident(int residentId) throws Exception {
        if(!exists(residentId)){
            throw new ResidentNotFoundException();
        }
        residentRepository.deleteByResidentId(residentId);
    }

    public ArrayList<FamilyInformDTO> getFamilyInforms(int residentId, ArrayList<FamilyRelationship> familyInforms) {
        ArrayList<FamilyInformDTO> finalFamilyInform = new ArrayList<>();
        Resident resident = getResident(residentId);
        LocalDate birthDate = resident.getBirthDate().toLocalDate();
        String year = String.valueOf(birthDate.getYear());
        String month = String.format("%02d", birthDate.getMonthValue());
        String day = String.format("%02d", birthDate.getDayOfMonth());

        FamilyInformDTO familyInformDTO = new FamilyInformDTO("본인",resident.getName(),year+"년"+month+"월"+day+"일",resident.getRegistrationNumber().substring(0,6)+"-*******",resident.getGender());
        finalFamilyInform.add(familyInformDTO);
        for(FamilyRelationship familyRelationship : familyInforms){
            familyInformDTO = FamilyInformDTO.toDTO(familyRelationship);
            finalFamilyInform.add(familyInformDTO);
        }
        return finalFamilyInform;
    }
}
