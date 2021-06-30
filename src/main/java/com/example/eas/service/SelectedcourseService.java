package com.example.eas.service;

import com.example.eas.entity.Course;
import com.example.eas.entity.Selectedcourse;
import com.example.eas.entity.pro.CoursePro;
import com.example.eas.entity.pro.StudentPro;

import java.util.ArrayList;

public interface SelectedcourseService {

    //根据学生id，课程id查看他是否选了这门课
    Selectedcourse selectSelectedCourseByStudentidAndCourseid(int studentid,int courseid);

    //2021年6月26日21:48:28 插入选课条目
    int insertSelectedCourseByStudentidAndCourseid(int studentid,int courseid);

    //2021年6月27日00:06:55 查询某一学生选择的所有课
    ArrayList<Course> selectCoursesByStudentid(int studentid);

    //2021年6月27日00:43:18 查询某一学生修的所有课 使用拓展类
    ArrayList<CoursePro> selectCourseProsMarkedByStudentid(int studentid);

    //2021年6月27日14:01:40 查询选某一门课的所有学生 使用拓展类
    ArrayList<StudentPro> selectStudentProsByCourseid(int courseid);

    //2021年6月27日16:12:47 根据类来更新分数
    int updateStudentMarkBySelectedCourse(Selectedcourse selectedcourse);

    //2021年6月28日23:05:29 根据studentid删除选课信息
    int delSelectedCourse(int studentid);
}
