package com.example.eas.service.impl;

import com.example.eas.dao.CourseMapper;
import com.example.eas.entity.Course;
import com.example.eas.entity.Page;
import com.example.eas.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public ArrayList<Course> selectCoursesByPageService(Page<Course> page) {
        return courseMapper.selectCoursesByPage(page);
    }

    @Override
    public ArrayList<Course> selectCoursesByTeacherid(int teacherid) {
        return courseMapper.selectCoursesByTeacherid(teacherid);
    }

    @Override
    public ArrayList<Course> selectCoursesByCourseNameLike(String coursename) {
        return courseMapper.selectCoursesByCourseNameLike(coursename);
    }
}
