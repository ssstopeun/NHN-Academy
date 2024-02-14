package com.nhnacademy.edu.springframework.sender;

public interface MessageSender {
    void sendMessage(User user, String message);
}
