package com.example.eas.entity.spec;

import com.example.eas.entity.Student;

import java.util.Date;

/**
 * @Description:为解决学生日期问题设计的spec类
 */
public class StudentSpec extends Student {

    //短的日期
    private String birthyearSpec;

    private String gradeSpec;

    //学院
    private String collegeidSpec;

    public String getBirthyearSpec() {
        return birthyearSpec;
    }

    public void setBirthyearSpec(String birthyearSpec) {
        this.birthyearSpec = birthyearSpec;
    }

    public String getGradeSpec() {
        return gradeSpec;
    }

    public void setGradeSpec(String gradeSpec) {
        this.gradeSpec = gradeSpec;
    }

    public String getCollegeidSpec() {
        return collegeidSpec;
    }

    public void setCollegeidSpec(String collegeidSpec) {
        this.collegeidSpec = collegeidSpec;
    }

    public StudentSpec() {
    }

    @Override
    public String toString() {
        return "StudentSpec{" +
                "birthyearSpec=" + birthyearSpec +
                ", gradeSpec=" + gradeSpec +
                ", collegeidSpec='" + collegeidSpec + '\'' +
                "} " + super.toString();
    }
}
