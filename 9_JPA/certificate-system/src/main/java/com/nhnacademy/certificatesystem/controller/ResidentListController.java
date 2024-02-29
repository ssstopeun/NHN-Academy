package com.nhnacademy.certificatesystem.controller;

import com.nhnacademy.certificatesystem.entity.Resident;
import com.nhnacademy.certificatesystem.service.CertificationService;
import com.nhnacademy.certificatesystem.service.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/resident")
public class ResidentListController {
    private final ResidentService residentService;

    public ResidentListController(ResidentService residentService){
        this.residentService = residentService;
    }


    @GetMapping
    public String residentListForm(@PageableDefault(size = 5) Pageable pageable, ModelMap modelMap){
        Page<Resident> residentPage = residentService.getResidents(pageable);
        List<Resident> residents = residentPage.getContent();
        int totalPages = residentPage.getTotalPages();

        modelMap.addAttribute("residentList", residents);
        modelMap.addAttribute("totalPages", totalPages);

        return "residentList";
    }

    @PostMapping("/{residentId}/delete")
    public String deleteResident(@PathVariable("residentId") int residentId) throws Exception {
        residentService.deleteResident(residentId);

        return "redirect:/resident";
    }

}
