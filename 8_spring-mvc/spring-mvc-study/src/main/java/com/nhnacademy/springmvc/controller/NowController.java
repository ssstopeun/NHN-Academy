package com.nhnacademy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class NowController {
//    @GetMapping("/now")
//    public String now(Model model){
//        model.addAttribute("date",new Date());
//        return "now";
//    }
    @GetMapping("/now")
    public ModelAndView now(){
        ModelAndView modelAndView = new ModelAndView("now");
        modelAndView.addObject("now",new Date());
        return modelAndView;
    }
}
