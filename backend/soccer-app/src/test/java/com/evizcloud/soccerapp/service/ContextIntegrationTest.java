package com.evizcloud.soccerapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class ContextIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void whenContextIsLoad_thenNoExceptions() {
        System.out.println();
    }

    @Test
    public void whenContextIsLoad_thenNoExceptions2() {
        System.out.println();
    }

}
