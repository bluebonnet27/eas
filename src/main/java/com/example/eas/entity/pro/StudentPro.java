package com.example.eas.entity.pro;

import com.example.eas.entity.Student;

public class StudentPro extends Student {

    private String mark;

    private Boolean isMarked;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Boolean getMarked() {
        return isMarked;
    }

    public void setMarked(Boolean marked) {
        isMarked = marked;
    }

    public StudentPro() {
    }

    @Override
    public String toString() {
        return "StudentPro{" +
                "mark='" + mark + '\'' +
                ", isMarked=" + isMarked +
                "} " + super.toString();
    }
}
