package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.repository.Score;

public class Student {
    public int getSeq() {
        return seq;
    }

    private final int seq;
    private final String name;
    private Score score;

    public Student(int seq, String name) {
        this.seq = seq;
        this.name = name;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Score getScore(){
        return this.score;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "seq=" + seq +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}'+ '\n';
    }
}
