package com.nhnacademy.edu.springframework.greeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class GreetingService {
    private final Greeter greeter;

//    @Autowired
//    public GreetingService(@Lang Greeter greeter) {
//        this.greeter = greeter;
//    }

    @Autowired
    public GreetingService(@GreeterQualifier(language = Language.ENGLISH, dummy = false) Greeter greeter) {
        this.greeter = greeter;
    }
    public void greet() {
        greeter.sayHello();
    }

    public void init() {
        System.out.println(this.getClass().getCanonicalName()+ ": init!!");
    }

    public void cleanup() {
        System.out.println(this.getClass().getCanonicalName()+ ": cleanup!!");
    }
}


