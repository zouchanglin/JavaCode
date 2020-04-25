package xpu.edu.tim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//为带有@ConfigurationProperties注解的Bean提供有效的支持。
// 这个注解可以提供一种方便的方式来将带有@ConfigurationProperties注解的类注入为Spring容器的Bean。
@EnableConfigurationProperties(PersonProperties.class)//开启属性注入,通过@autowired注入
@ConditionalOnClass(Person.class)//判断这个类是否在classpath中存在，如果存在，才会实例化一个Bean
@ConditionalOnProperty(prefix="person", value="enabled", matchIfMissing = true)
public class PersonAutoConfiguration {
    @Autowired
    private PersonProperties personProperties;

    @Bean
    @ConditionalOnMissingBean(Person.class)//容器中如果没有Person这个类,那么自动配置这个Person
    public Person person() {
        Person person = new Person();
        person.setName(personProperties.getName());
        return person;
    }
}