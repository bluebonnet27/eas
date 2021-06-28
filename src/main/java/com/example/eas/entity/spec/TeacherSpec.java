package com.example.eas.entity.spec;

import com.example.eas.entity.Teacher;

import java.util.Date;

public class TeacherSpec extends Teacher {
    private String birthyearSpec;

    private String gradeSpec;

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

    public TeacherSpec() {
    }

    @Override
    public String toString() {
        return "TeacherSpec{" +
                "birthyearSpec='" + birthyearSpec + '\'' +
                ", gradeSpec='" + gradeSpec + '\'' +
                ", collegeidSpec='" + collegeidSpec + '\'' +
                "} " + super.toString();
    }
}
