package indi.xk.report.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author xk
 * @Date 2019/12/17 10:44
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "student")
public class StudentProperties {
    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
