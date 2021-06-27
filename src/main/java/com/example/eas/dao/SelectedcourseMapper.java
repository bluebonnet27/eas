package com.example.eas.dao;

import com.example.eas.entity.Selectedcourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface SelectedcourseMapper {
    int insert(Selectedcourse record);

    int insertSelective(Selectedcourse record);

    //2021年6月26日20:27:20 查询学生是否选择某门课程
    Selectedcourse selectSelectedCourseByStudentidAndCourseid(int studentid,int courseid);

    //2021年6月27日00:05:00 查询某个学生选择的课程集合
    ArrayList<Selectedcourse> selectSelectedCourseByStudentid(int studentid);

    //2021年6月27日00:37:14 查询某个学生修的课程集合
    ArrayList<Selectedcourse> selectSelectedMarkedCourseByStudentid(int studentid);
}