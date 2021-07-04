package com.example.eas.service;

import com.example.eas.entity.Course;
import com.example.eas.entity.Page;

import java.util.ArrayList;

public interface CourseService {

    //分页查询所有课程
    ArrayList<Course> selectCoursesByPageService(Page<Course> page);

    //根据老师id查教授的课程
    ArrayList<Course> selectCoursesByTeacherid(int teacherid);

    //2021年6月30日13:12:59 根据课程名模糊查询所有课程
    ArrayList<Course> selectCoursesByCourseNameLike(String coursename);
}
