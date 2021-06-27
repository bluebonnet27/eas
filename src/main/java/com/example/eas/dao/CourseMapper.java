package com.example.eas.dao;

import com.example.eas.entity.Course;
import com.example.eas.entity.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(Integer courseid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer courseid);

    //2021年6月26日11:22:12 分页查询所有课程
    ArrayList<Course> selectCoursesByPage(Page<Course> page);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}