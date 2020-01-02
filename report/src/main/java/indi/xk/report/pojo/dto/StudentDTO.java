package indi.xk.report.pojo.dto;

import indi.xk.report.pojo.Student;

/**
 * @Author xk
 * @Date 2019/12/18 15:22
 * @Version 1.0
 */
public class StudentDTO extends Student {
    private String sexStr;

    public String getSexStr() {
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "sexStr='" + sexStr + '\'' +
                '}';
    }
}
