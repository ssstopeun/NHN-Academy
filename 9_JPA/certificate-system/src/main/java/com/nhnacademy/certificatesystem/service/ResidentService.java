package com.nhnacademy.certificatesystem.service;

import com.nhnacademy.certificatesystem.entity.Resident;
import com.nhnacademy.certificatesystem.exception.ResidentNotFoundException;
import com.nhnacademy.certificatesystem.repository.ResidentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public boolean exists(int residentId){
        return residentRepository.existsByResidentId(residentId);
    }

    public Resident getResident(int residentId){
        return exists(residentId)?residentRepository.findById((long) residentId).get() : null;
    }
    public ArrayList<Resident> getAllResident(){
        return residentRepository.findAll();
    }

    public long residentCount(){
        return residentRepository.count();
    }
    public void deleteResident(int residentId) throws Exception {
        if(!exists(residentId)){
            throw new ResidentNotFoundException();
        }
        residentRepository.deleteByResidentId(residentId);
    }
}
