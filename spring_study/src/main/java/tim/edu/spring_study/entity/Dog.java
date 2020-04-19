package tim.edu.spring_study.entity;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Pet {
    @Override
    public void move() {
        System.out.println("Running...");
    }
}
