package com.nhnacademy.homework1.domain;

import lombok.Getter;
import lombok.Setter;


public class Student {
    @Getter
    @Setter
    private long studentId;
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private Integer score;

    @Getter
    @Setter
    private String evaluation;

    public static Student create(String name, String email, int score, String evaluation){
        return new Student(name,email,score,evaluation);
    }

    private Student(String name, String email, int score, String evaluation){
        this.name = name;
        this.email=email;
        this.score=score;
        this.evaluation=evaluation;
    }

//    public static Student constructPasswordMaskedUser(Student student) {
//        Student newStudent = Student.create(student.getName(),student.getEmail(),null,null);
//        newStudent.setStudentId(student.getStudentId());
//
//        return newUser;
//    }

}
