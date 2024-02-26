package com.nhnacademy.homework1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        HttpSession httpSession = request.getSession(false);
        if(httpSession!=null&&httpSession.getAttribute("id")!=null){
            model.addAttribute("id",httpSession.getAttribute("id"));
        }
        return "index";
    }

}
