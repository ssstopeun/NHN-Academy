package com.nhnacademy.jdbc.student.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Student {


    public Student(String id, String name, String gender, int age, Date created_at) {
        this.id = id;
        this.name=name;
        this.gender = GENDER.valueOf(gender);
        this.age = age;
        this.createdAt =  Instant.ofEpochMilli(created_at.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public enum GENDER{
        M,F
    }
    private final String id;
    private final String name;
    private final GENDER gender;
    private final Integer age;
    private final LocalDateTime createdAt;

    public Student(String id, String name, GENDER gender, Integer age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }

    public Student(String id, String name, GENDER gender, Integer age, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GENDER getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    //todo#0 필요한 method가 있다면 추가합니다.
}
