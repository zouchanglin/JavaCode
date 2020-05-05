package com.example.demo;

import com.github.zouchanglin.examplespringbootstarter.service.AESHandleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AESHandleServiceTest {
    @Autowired
    private AESHandleService aesHandleService;

    @Test
    public void encryptAndDecrypt() {
        String src = "Hello, SpringBootStarter";
        String password = "123321";
        System.out.println("源字符串:" + src);

        byte[] encryptResult = aesHandleService.encrypt(src, password);
        String encryptString = new String(encryptResult);
        System.out.println("加密后:" + encryptString);

        byte[] decryptResult = aesHandleService.decrypt(encryptResult, password);
        String decryptString = new String(decryptResult);
        System.out.println("解密后:" + decryptString);

        assertEquals(src, decryptString);
    }
}