package com.nhnacademy.homework1.repository;

import com.nhnacademy.homework1.domain.Student;
import com.nhnacademy.homework1.exception.StudentNotFoundException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class StudentRepositoryImpl implements StudentRepository {
    private final Map<Long, Student> students = new HashMap<>();
    @Override
    public boolean exists(long id) {
        return students.containsKey(id);
    }

    @Override
    public Student register(String name, String email, int score, String evaluation) {
        long id = students.keySet()
                .stream()
                .max(Comparator.comparing(Function.identity()))
                .map(l->l+1)
                .orElse(1L);
        Student student = Student.create(name,email,score,evaluation);
        student.setStudentId(id);
        students.put(id,student);

        return student;
    }

    @Override
    public void modify(Student student) {
        Student student1 = students.get(student.getStudentId());
        if(Objects.isNull(student1)){
            throw new StudentNotFoundException();
        }
        student1.setName(student.getName());
        student1.setEmail(student.getEmail());
        student1.setScore(student.getScore());
        student1.setEvaluation(student.getEvaluation());
        students.replace(student.getStudentId(),student1);
    }

    @Override
    public Student getStudent(long id) {
        return exists(id) ? students.get(id) : null;
    }
}
