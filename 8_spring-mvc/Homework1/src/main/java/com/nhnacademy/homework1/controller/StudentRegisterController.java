package com.nhnacademy.homework1.controller;

import com.nhnacademy.homework1.domain.Student;
import com.nhnacademy.homework1.domain.StudentRequest;
import com.nhnacademy.homework1.exception.ValidationFailedException;
import com.nhnacademy.homework1.repository.StudentRepository;
import com.nhnacademy.homework1.validator.StudentValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/student/register")
public class StudentRegisterController {
    private final StudentRepository studentRepository;

    private final StudentValidator validator;

    public StudentRegisterController(StudentRepository postRepository, StudentValidator validator) {
        this.studentRepository = postRepository;
        this.validator = validator;
    }

    @GetMapping
    public String studentRegisterForm() {
        return "studentRegister";
    }

    @PostMapping
    public ModelAndView registerPost(@Valid @ModelAttribute StudentRequest studentRequest,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Student student = studentRepository.register(studentRequest.getName(),studentRequest.getEmail(),studentRequest.getScore(),studentRequest.getEvaluation());

        ModelAndView mav = new ModelAndView("studentView");
        mav.addObject("student", student);

        return mav;
    }
    @InitBinder("studentRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}