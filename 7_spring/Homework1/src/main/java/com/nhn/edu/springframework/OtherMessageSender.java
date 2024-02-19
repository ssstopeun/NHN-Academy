package com.nhn.edu.springframework;

public class OtherMessageSender implements MessageSender{

    public OtherMessageSender() {
        System.out.println("SmsMessageSender initiated!!");
    }

      @Override
    public void sendMessage(User user, String message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + message);
    }
}
