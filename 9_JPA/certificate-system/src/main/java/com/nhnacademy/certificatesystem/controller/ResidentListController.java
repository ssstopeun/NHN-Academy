package com.nhnacademy.certificatesystem.controller;

import com.nhnacademy.certificatesystem.domain.ResidentListDTO;
import com.nhnacademy.certificatesystem.entity.Resident;
import com.nhnacademy.certificatesystem.service.CertificationService;
import com.nhnacademy.certificatesystem.service.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/resident")
public class ResidentListController {
    private final ResidentService residentService;
    private final CertificationService certificationService;

    public ResidentListController(ResidentService residentService, CertificationService certificationService){
        this.residentService = residentService;
        this.certificationService = certificationService;
    }

    @ModelAttribute("residentList")
    public ArrayList<Resident> getResident() throws Exception {
        return residentService.getAllResident();
    }



    @GetMapping
    public String residentListForm(@ModelAttribute ArrayList<Resident> residentList,
                                   ModelMap modelMap){
        ArrayList<ResidentListDTO> result = new ArrayList<>();
        for(Resident resident : residentList){
            ResidentListDTO residentListDTO = ResidentListDTO.toDTO(resident);
            result.add(residentListDTO);
        }

        modelMap.put("residentList",result);
        return "residentList";
    }

    @PostMapping("/{residentId}/delete")
    public String deleteResident(@PathVariable("residentId") int residentId) throws Exception {
        residentService.deleteResident(residentId);

        return "redirect:/resident";
    }

}
