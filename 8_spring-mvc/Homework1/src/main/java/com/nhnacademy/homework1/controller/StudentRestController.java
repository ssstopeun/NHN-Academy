package com.nhnacademy.homework1.controller;

import com.nhnacademy.homework1.domain.Student;
import com.nhnacademy.homework1.domain.StudentRequest;
import com.nhnacademy.homework1.exception.StudentNotFoundException;
import com.nhnacademy.homework1.exception.ValidationFailedException;
import com.nhnacademy.homework1.repository.StudentRepository;
import com.nhnacademy.homework1.validator.StudentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class StudentRestController {

    private final StudentRepository studentRepository;

    private final StudentValidator validator;

    public StudentRestController(StudentRepository postRepository, StudentValidator validator) {
        this.studentRepository = postRepository;
        this.validator = validator;
    }

    @PostMapping("/students/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@Valid @RequestBody StudentRequest studentRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return studentRepository.register(studentRequest.getName(), studentRequest.getEmail(), studentRequest.getScore(),
                studentRequest.getEvaluation());
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable long studentId) {
            if (!studentRepository.exists(studentId)) {
                throw new StudentNotFoundException();
            }
            Student student = studentRepository.getStudent(studentId);
            return student ;
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable long studentId, @Validated @RequestBody StudentRequest modifyRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        if (!studentRepository.exists(studentId)) {
            throw new StudentNotFoundException();
        }
        Student student = studentRepository.getStudent(studentId);
        student.setName(modifyRequest.getName());
        student.setEmail(modifyRequest.getEmail());
        student.setScore(modifyRequest.getScore());
        student.setEvaluation(modifyRequest.getEvaluation());

        studentRepository.modify(student);
        Student student1 = studentRepository.getStudent(studentId);
        return student1;
    }

    @InitBinder("studentRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }
}