package com.nhnacademy.edu.springframework.greeter;

public class EnglishGreeter implements Greeter {
    public EnglishGreeter() {
    }
    @Override
    public boolean sayHello() {
        System.out.println("Hello World!");
        return true;
    }

    private void init() {
        System.out.println("EnglishGreeter initiated!!");
    }
}
