package com.nhnacademy.edu.springframework.greeter;

public class EnglishGreeter implements Greeter {
    public EnglishGreeter() {
        System.out.println("EnglishGreeter initiated!!");
    }
    @Override
    public boolean sayHello() {
        System.out.println("Hello World!");
        return true;
    }
}
