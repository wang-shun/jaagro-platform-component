package com.jaagro.component.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tony
 */
@EnableEurekaClient
@EnableCircuitBreaker
@ComponentScan("com.jaagro.component")
@MapperScan("com.jaagro.component.biz.mapper")
@SpringBootApplication
public class ComponentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComponentApplication.class, args);
    }
}
