package com.nhnacademy.homework1.controller;

import com.nhnacademy.homework1.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler(ValidationFailedException.class)
    public String handleException(Exception ex, Model model) {
        log.error("", ex);

        model.addAttribute("exception", ex);
        return "error";
    }


}
