package com.nhnacademy.edu.springframework.greeter;

public class KoreanGreeter implements Greeter {
    public KoreanGreeter() {
        System.out.println("KoreanGreeter initiated!!");
    }
    @Override
    public boolean sayHello() {
        System.out.println("안녕 세상!");
        return true;
    }
}
