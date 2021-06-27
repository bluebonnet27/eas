package com.example.eas.entity.pro;

import com.example.eas.entity.Course;

public class CoursePro extends Course {

    private Integer mark;

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public CoursePro(Integer mark) {
        this.mark = mark;
    }

    public CoursePro() {
    }

    @Override
    public String toString() {
        return super.toString()+
        "CoursePro{" +
                "mark=" + mark +
                '}';
    }
}
