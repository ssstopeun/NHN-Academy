package com.nhnacademy.certificatesystem.controller;

import com.nhnacademy.certificatesystem.domain.FamilyInformLayerDTO;
import com.nhnacademy.certificatesystem.domain.FamilyInformDTO;
import com.nhnacademy.certificatesystem.entity.FamilyRelationship;
import com.nhnacademy.certificatesystem.service.CertificationService;
import com.nhnacademy.certificatesystem.service.FamilyCertificationService;
import com.nhnacademy.certificatesystem.service.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/certification")
public class CertificationController {

    private final ResidentService residentService;

    private final FamilyCertificationService familyCertificationService;

    private final CertificationService certificationService;


    public CertificationController(ResidentService residentService, FamilyCertificationService familyCertificationService, CertificationService certificationService) {
        this.residentService = residentService;
        this.familyCertificationService = familyCertificationService;
        this.certificationService = certificationService;
    }

    @ModelAttribute("familyCertificationLayer")
    public FamilyInformLayerDTO getFamilyCertificationLayer(@PathVariable("residentId") int residentId) {
        return FamilyInformLayerDTO.toDTO(residentService.getResident(residentId),certificationService.getFamilyCertificationId());
    }

    @ModelAttribute("familyInform")
    public ArrayList<FamilyInformDTO> getFamilyInform(@PathVariable("residentId") int residentId){
        ArrayList<FamilyRelationship> familyRelationships = familyCertificationService.getAllFamilyRelationshipByBaseResidentId(residentId);
        return residentService.getFamilyInforms(residentId,familyRelationships);
    }

    @GetMapping("/familyRelationship/{residentId}")
    public String familyCertificationForm(@ModelAttribute("familyCertificationLayer") FamilyInformLayerDTO familyCertificationLayer,
                                          @ModelAttribute("familyInform") ArrayList<FamilyRelationship> familyInforms,
                                          ModelMap modelMap){
        modelMap.put("familyCertificationLayer",familyCertificationLayer);
        modelMap.put("familyInforms",familyInforms);
        return "familyRelationship";
    }
}
