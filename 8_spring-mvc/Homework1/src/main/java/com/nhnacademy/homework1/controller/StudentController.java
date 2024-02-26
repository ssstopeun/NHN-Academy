package com.nhnacademy.homework1.controller;

import com.nhnacademy.homework1.domain.Student;
import com.nhnacademy.homework1.domain.StudentRequest;
import com.nhnacademy.homework1.exception.StudentNotFoundException;
import com.nhnacademy.homework1.exception.ValidationFailedException;
import com.nhnacademy.homework1.repository.StudentRepository;
import com.nhnacademy.homework1.validator.StudentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentValidator validator;

    public StudentController(StudentRepository studentRepository,  StudentValidator validator) {
        this.studentRepository = studentRepository;
        this.validator = validator;
    }

    @ModelAttribute("student")
    public Student getStudent(@PathVariable("studentId") Long studentId){
        if(!studentRepository.exists(studentId)){
            throw new StudentNotFoundException();
        }
        return studentRepository.getStudent(studentId);
    }

    @GetMapping(value = "/{studentId}")
    public String viewStudent(@ModelAttribute Student student,
                              @RequestParam(name = "hideScore", required = false, defaultValue = "no") String hideScore,
                              ModelMap modelMap) {
        if(Objects.isNull(student)){
            modelMap.put("exception",new StudentNotFoundException());
        }
        modelMap.put("hideScore",hideScore);
        modelMap.put("student",student);
        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@ModelAttribute("student") Student student, Model model) {
        if(Objects.isNull(student)){
            model.addAttribute("exception",new StudentNotFoundException());
            return "error";
        }
        model.addAttribute("student",student);
        return "studentModify";
    }

    @PostMapping("/{studentId}/modify")
    public String modifyStudent(@ModelAttribute Student student,
                                @Valid @ModelAttribute StudentRequest studentRequest,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        student.setName(studentRequest.getName());
        student.setName(studentRequest.getEmail());
        student.setScore(studentRequest.getScore());
        student.setEvaluation(studentRequest.getEvaluation());

        studentRepository.modify(student);

        model.addAttribute("student", studentRepository.getStudent(student.getStudentId()));

        return "studentView";
    }

    @InitBinder("studentRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }
}
