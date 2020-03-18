package xpu.tim.freemarker_bug;


public class Person {
    private String name;
    private Long age;

    public Person(){}
    public Person(String name, Long age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
