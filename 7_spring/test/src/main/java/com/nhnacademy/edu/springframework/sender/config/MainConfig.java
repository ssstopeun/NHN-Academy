package com.nhnacademy.edu.springframework.sender.config;

import com.nhnacademy.edu.springframework.sender.MessageSendService;
import com.nhnacademy.edu.springframework.sender.SmsMessageSender;
import com.nhnacademy.edu.springframework.sender.SmsMessageSenderMatch;
import org.springframework.context.annotation.*;

@Configuration
@ImportResource("classpath:/beans.xml")
public class MainConfig {


    @Bean(value = "smsMessageSender", initMethod = "init",destroyMethod = "cleanup")
    @Scope("prototype")
    public SmsMessageSender smsMessageSender(){
        return new SmsMessageSender();
    }

    @Conditional(SmsMessageSenderMatch.class)
    @Bean("messageSendService")
    public MessageSendService messageSendService() {
        return new MessageSendService(smsMessageSender());
    }

}
