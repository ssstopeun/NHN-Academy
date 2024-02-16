package com.nhnacademy.edu.springframework.sender;


import org.springframework.stereotype.Service;

@Service
public class DoorayMessageSender implements MessageSender{
    @Override
    public boolean sendMessage(User user, String message) {
        new DoorayHookSender(new RestTemplate(), "https://hook.dooray.com/services/3204376758577275363/3738651962004364986/HByzZBRuRu69-EUTsWR0tg")
                .send(DoorayHook.builder()
                        .botName("강병구")
                        .text("안녕하세요")
                        .build());
        return true;
    }
}