package tim.edu.spring_study.depend;

import org.springframework.stereotype.Component;

@Component
public class B {
    private A a;

    public void setA(A a) {
        this.a = a;
    }
}
