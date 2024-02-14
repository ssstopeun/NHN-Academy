package com.nhnacademy.edu.springframework.greeter;

public class test {
    public static void main(String[] args) {
        GreetingService greetingService = new GreetingService(new EnglishGreeter());
    }
}
