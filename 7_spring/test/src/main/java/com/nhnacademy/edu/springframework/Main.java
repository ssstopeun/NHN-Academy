package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.greeter.Greeter;
import com.nhnacademy.edu.springframework.greeter.GreetingService;
import com.nhnacademy.edu.springframework.sender.MessageSendService;
import com.nhnacademy.edu.springframework.sender.MessageSender;
import com.nhnacademy.edu.springframework.sender.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        String email = "asdf111@naver.com";
        String phoneNumber = "01012341234";
        User user = new User(email, phoneNumber);
        String message = "hello world!";



        try ( AnnotationConfigApplicationContext context =
                      new AnnotationConfigApplicationContext("com.nhnacademy.edu.springFramework.sender")) {

//
            MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
            messageSendService.doSendMessage(user,"jieun");
//
//            GreetingService greetingService = context.getBean("greetingService", GreetingService.class);
//            greetingService.greet();
        }
    }
}