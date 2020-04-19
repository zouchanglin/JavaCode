package com.example.emaildemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceImplTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void sendSimpleMessage() {
        emailService.sendSimpleMessage("1610984228@qq.com", "聚餐主题", "今晚10点半老地方见");
    }
}