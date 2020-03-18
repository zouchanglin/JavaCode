package edu.xpu.catchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CatchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatchDemoApplication.class, args);
    }

}
