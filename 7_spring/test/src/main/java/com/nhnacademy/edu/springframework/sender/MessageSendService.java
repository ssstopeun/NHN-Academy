package com.nhnacademy.edu.springframework.sender;

public class MessageSendService {
    private MessageSender messageSender;
//    public MessageSendService(MessageSender messageSender) {
//        this.messageSender = messageSender;
//    }

    public void setSmsMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
//    public void setEmailMessageSender(MessageSender messageSender){
//        this.messageSender=messageSender;
//    }

    public void doSendMessage(User user, String message) {
        messageSender.sendMessage(user, message);
    }
}
