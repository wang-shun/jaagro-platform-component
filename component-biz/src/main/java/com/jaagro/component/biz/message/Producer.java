package com.jaagro.component.biz.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tony
 */
@Component
public class Producer {

    private static final String QUEUE = "comp-queue";
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String message = "你好，消息";
        rabbitTemplate.convertAndSend(QUEUE, message);
        System.out.println(message + ": 我是消息发送者");
    }
}
