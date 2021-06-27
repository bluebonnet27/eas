package com.example.eas.service.impl;

import com.example.eas.dao.CourseMapper;
import com.example.eas.dao.SelectedcourseMapper;
import com.example.eas.entity.Course;
import com.example.eas.entity.Selectedcourse;
import com.example.eas.entity.pro.CoursePro;
import com.example.eas.service.SelectedcourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SelectedcourseServiceImpl implements SelectedcourseService {

    @Autowired
    private SelectedcourseMapper selectedcourseMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Selectedcourse selectSelectedCourseByStudentidAndCourseid(int studentid, int courseid) {
        return selectedcourseMapper.selectSelectedCourseByStudentidAndCourseid(studentid,courseid);
    }

    @Override
    public int insertSelectedCourseByStudentidAndCourseid(int studentid, int courseid) {
        Selectedcourse selectedcourse = new Selectedcourse(courseid,studentid);
        return selectedcourseMapper.insertSelective(selectedcourse);
    }

    @Override
    public ArrayList<Course> selectCoursesByStudentid(int studentid) {
        ArrayList<Selectedcourse> selectedcourses =
                selectedcourseMapper.selectSelectedCourseByStudentid(studentid);

        ArrayList<Course> courses = new ArrayList<>();

        for(Selectedcourse selectedcourse : selectedcourses){
            int courseid = selectedcourse.getCourseid();
            Course course = courseMapper.selectByPrimaryKey(courseid);
            courses.add(course);
        }

        return courses;
    }

    @Override
    public ArrayList<CoursePro> selectCourseProsMarkedByStudentid(int studentid) {
        ArrayList<Selectedcourse> selectedcourses =
                selectedcourseMapper.selectSelectedMarkedCourseByStudentid(studentid);

        ArrayList<CoursePro> coursePros = new ArrayList<>();

        for(Selectedcourse selectedcourse : selectedcourses){
            int courseid = selectedcourse.getCourseid();
            System.out.println("courseid "+courseid);
            int mark = selectedcourse.getMark();

            CoursePro coursePro = new CoursePro();
            Course course = courseMapper.selectByPrimaryKey(courseid);

            coursePro.setCourseid(courseid);
            coursePro.setCoursename(course.getCoursename());
            coursePro.setCoursetime(course.getCoursetime());
            coursePro.setCoursetype(course.getCoursetype());
            coursePro.setCourseweek(course.getCourseweek());
            coursePro.setClassroom(course.getClassroom());
            coursePro.setCollegeid(course.getCollegeid());
            coursePro.setScore(course.getScore());
            coursePro.setTeacherid(course.getTeacherid());
            coursePro.setMark(mark);

            coursePros.add(coursePro);
        }

        return coursePros;
    }
}
