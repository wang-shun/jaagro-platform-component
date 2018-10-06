package com.jaagro.component.web.controller;

import com.jaagro.component.biz.message.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 */
@RestController
public class TestController {

    @Autowired
    private Producer producer;

    @GetMapping("/test1")
    public void test1() {
        producer.send();
    }
}
