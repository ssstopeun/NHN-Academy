package com.nhnacademy.edu.springframework.greeter;

public class GreetingService {
    private final Greeter greeter;

    public GreetingService(Greeter greeter) {
        this.greeter = greeter;
    }

    public boolean greet() {
        return greeter.sayHello();
    }
}
