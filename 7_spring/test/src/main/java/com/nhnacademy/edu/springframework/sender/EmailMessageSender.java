package com.nhnacademy.edu.springframework.sender;

public class EmailMessageSender implements MessageSender{
    public EmailMessageSender() {
        System.out.println("EmailMessageSender initiated!!");
    }

    public void init() {
        System.out.println("init method called in EmailMessageSender");
    }

    public void cleanup() {
        System.out.println("destroy method called in EmailMessageSender");
    }
    @Override
    public void sendMessage(User user, String message) {
        System.out.println("Email Message Sent " + user.getEmail() + " : " + message);
    }
}
