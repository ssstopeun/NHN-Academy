package com.nhnacademy.homework1.repository;

import com.nhnacademy.homework1.domain.Student;

public interface StudentRepository {
    boolean exists(long id);

    Student register(String name, String email, int score, String evaluation);
    void modify(Student student);

    Student getStudent(long id);

}
