package com.nhn.edu.springframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        String email = "stopeun@naver.com";
        String phoneNumber = "01067006817";
        User user = new User(email, phoneNumber);
        String message = "hello world!";



        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhn.edu.springFramework")) {

            MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
            messageSendService.doSendMessage(user,"jieun");

        }
    }
}