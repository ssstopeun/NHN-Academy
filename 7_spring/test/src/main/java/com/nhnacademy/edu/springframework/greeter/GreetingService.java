package com.nhnacademy.edu.springframework.greeter;

import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public class GreetingService {
    private Greeter greeter;

    @Autowired
    public GreetingService(@Lang Greeter greeter) {
        this.greeter = greeter;
    }
//    @Autowired
//    public void setKoreanGreeter(@Qualifier("koreanGreeter") Greeter greeter){
//        System.out.println("setKoreanGreeter invoked!");
//        this.greeter=greeter;
//    }

//    @Autowired(required = true)
//    public void setKoreanGreeter(Optional<Greeter> greeter) {
//        System.out.println("setGreeter invoked!");
//        this.greeter = greeter.orElseThrow();
//    }


    public void greet() {
        greeter.sayHello();
    }
}
