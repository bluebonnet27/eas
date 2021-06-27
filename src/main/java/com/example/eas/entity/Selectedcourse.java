package com.example.eas.entity;

public class Selectedcourse {
    private Integer courseid;

    private Integer studentid;

    private Integer mark;

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Selectedcourse(Integer courseid, Integer studentid) {
        this.courseid = courseid;
        this.studentid = studentid;
    }

    public Selectedcourse() {
    }
}