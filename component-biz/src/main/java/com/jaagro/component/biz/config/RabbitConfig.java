package com.jaagro.component.biz.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author tony
 */
@Configuration
public class RabbitConfig {
    private static final String QUEUE = "comp-queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }
}
