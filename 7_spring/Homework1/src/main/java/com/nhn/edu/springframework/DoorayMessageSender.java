package com.nhn.edu.springframework;


import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DoorayMessageSender implements MessageSender {

    @Override
    public void sendMessage(User user, String message) {
        new DoorayHookSender(new RestTemplate(), "https://hook.dooray.com/services/3204376758577275363/3738658763105354275/jLOc1FfKRPqMEU3VeI95mA")
                .send(DoorayHook.builder()
                        .botName("지은")
                        .text("test")
                        .build());
    }
}