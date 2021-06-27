package com.example.eas.service;

import com.example.eas.entity.Course;
import com.example.eas.entity.Page;

import java.util.ArrayList;

public interface CourseService {

    //分页查询所有课程
    ArrayList<Course> selectCoursesByPageService(Page<Course> page);
}
