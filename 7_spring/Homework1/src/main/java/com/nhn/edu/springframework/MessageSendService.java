package com.nhn.edu.springframework;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageSendService {
    MessageSender messageSender;

    @Autowired
    public MessageSendService(MessageSender messageSender){
        this.messageSender=messageSender;
    }

    public void doSendMessage(User user, String message){
        messageSender.sendMessage(user, message);
    }

}
