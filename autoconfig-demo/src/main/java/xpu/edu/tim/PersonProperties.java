package xpu.edu.tim;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "person") //获取属性值
public class PersonProperties {
    private static final String NAME = "Jock.Tim";

    private String name = NAME ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}