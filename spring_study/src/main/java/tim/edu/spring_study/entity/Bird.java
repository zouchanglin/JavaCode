package tim.edu.spring_study.entity;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Bird implements Pet {
    @Override
    public void move() {
        System.out.println("flying...");
    }
}
