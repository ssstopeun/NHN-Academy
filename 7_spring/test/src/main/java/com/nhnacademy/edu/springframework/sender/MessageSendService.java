package com.nhnacademy.edu.springframework.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageSendService {
    MessageSender messageSender;

    public MessageSendService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }


    @Autowired
    public void setSmsMessageSender(@Qualifier("doorayMessageSender") MessageSender messageSender) {
        this.messageSender = messageSender;
    }


    public void doSendMessage(User user, String message) {
        messageSender.sendMessage(user, message);
    }
}
