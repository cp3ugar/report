package indi.xk.report.pojo;

import javax.validation.constraints.NotNull;

/**
 * @Author xk
 * @Date 2019/12/17 11:52
 * @Version 1.0
 */
public class Student {

    private Integer id;

    @NotNull(message = "学号不能为空！")
    private Integer studentId;

    @NotNull(message = "姓名不能为空！")
    private String name;

    @NotNull(message = "年龄不能为空！")
    private Integer age;

    @NotNull(message = "性别不能为空！")
    private String sex;

    @NotNull(message = "生日不能为空！")
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
