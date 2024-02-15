package com.nhnacademy.edu.springframework.greeter;

import org.springframework.context.annotation.Bean;

public interface BaseJavaConfig {
    @Bean
    default String dbms(){
        return new String("MYSQL");
    }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public default GreetingService greetingService(Greeter greeter) {
        GreetingService greetingService = new GreetingService(greeter);
        return greetingService;
    }
}

