package com.nhnacademy.edu.springframework;ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")

import com.nhnacademy.edu.springframework.greeter.Greeter;
import com.nhnacademy.edu.springframework.greeter.GreetingService;
import com.nhnacademy.edu.springframework.sender.MessageSendService;
import com.nhnacademy.edu.springframework.sender.MessageSender;
import com.nhnacademy.edu.springframework.sender.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        String email = "asdf111@naver.com";
        String phoneNumber = "01012341234";
        User user = new User(email, phoneNumber);
        String message = "hello world!";

        try () {
//            System.out.println("---------");
//            new GreetingService(context.getBean("koreanGreeter", Greeter.class)).greet();
//            System.out.println("---------");
//            new GreetingService(context.getBean("koreanGreeter", Greeter.class)).greet();
//            System.out.println("---------");
//            new GreetingService(context.getBean("englishGreeter", Greeter.class)).greet();
//            System.out.println("---------");
//            new GreetingService(context.getBean("englishGreeter", Greeter.class)).greet();
//            System.out.println("---------");
//
//           koreanGreeter.sayHello();
//           englishGreeter.sayHello();
//
//            MessageSender smsMessageSender = context.getBean("smsMessageSender", MessageSender.class);
//            MessageSender smsMessageSender2 = context.getBean("smsMessageSender", MessageSender.class);
//            MessageSender emailMessageSender = context.getBean("emailMessageSender", MessageSender.class);
//            MessageSender emailMessageSender2 = context.getBean("emailMessageSender", MessageSender.class);
//
//            new MessageSendService(smsMessageSender).doSendMessage(user, message);
//            new MessageSendService(smsMessageSender2).doSendMessage(user, message);
//            new MessageSendService(emailMessageSender).doSendMessage(user, message);
//            new MessageSendService(emailMessageSender2).doSendMessage(user, message);

            MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
            messageSendService.doSendMessage(user,"jieun");
        }
    }
}