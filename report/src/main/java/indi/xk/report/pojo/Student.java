package indi.xk.report.pojo;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author xk
 * @Date 2019/12/17 11:52
 * @Version 1.0
 */
public class Student {
    private Integer id;

    @NotNull(message = "学号不能为空！")
    @Min(value = 1000,message = "学号必须为四位！")
    @Max(value = 9999,message = "学号必须为四位！")
    private Integer studentId;

    @NotBlank(message = "姓名不能为空！")
    private String name;

    @NotNull(message = "年龄不能为空！")
    @Min(value = 7,message = "年龄必须大于6！")
    @Max(value = 30,message = "年龄必须小于30！")
    private Integer age;

    @NotBlank(message = "性别不能为空！")
    private String sex;

    @NotBlank(message = "生日不能为空！")
    private String birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
