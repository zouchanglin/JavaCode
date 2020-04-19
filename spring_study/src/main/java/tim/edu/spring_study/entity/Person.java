package tim.edu.spring_study.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("person")
public class Person {
    @Value("1")
    private Integer id;

    @Value("Tim")
    private String name;

    private Pet pet;

    public Person(Pet pet) {
        this.pet = pet;
    }

    public void call(){
        pet.move();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
