package com.mazasoft.sfgjms.receiver;

import com.mazasoft.sfgjms.config.JmsConfig;
import com.mazasoft.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HelloMessageReceiver {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void receive(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) {
     /*   System.out.println("HelloMessageReceiver. I got a message");
        System.out.println("HelloMessageReceiver. " + helloWorldMessage);*/
    }

    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void receiveFromHello(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) throws JMSException {
        HelloWorldMessage payLoadMsg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("World!")
                .build();
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payLoadMsg);
    }
}

