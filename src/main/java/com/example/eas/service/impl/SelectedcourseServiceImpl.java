package com.example.eas.service.impl;

import com.example.eas.dao.CourseMapper;
import com.example.eas.dao.SelectedcourseMapper;
import com.example.eas.dao.StudentMapper;
import com.example.eas.entity.Course;
import com.example.eas.entity.Selectedcourse;
import com.example.eas.entity.Student;
import com.example.eas.entity.pro.CoursePro;
import com.example.eas.entity.pro.StudentPro;
import com.example.eas.service.SelectedcourseService;
import org.apache.ibatis.annotations.Arg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SelectedcourseServiceImpl implements SelectedcourseService {

    @Autowired
    private SelectedcourseMapper selectedcourseMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

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

    @Override
    public ArrayList<StudentPro> selectStudentProsByCourseid(int courseid) {
        ArrayList<Selectedcourse> selectedcourses =
                selectedcourseMapper.selectSelectedCoursesByCourseid(courseid);

        ArrayList<StudentPro> studentPros = new ArrayList<>();

        for(Selectedcourse selectedcourse:selectedcourses){
            int studentid = selectedcourse.getStudentid();
            Student student = studentMapper.selectByPrimaryKey(studentid);

            StudentPro studentPro = new StudentPro();
            studentPro.setUserid(studentid);
            studentPro.setUsername(student.getUsername());
            //表中不展示出来的参数就置空
            if(selectedcourse.getMark()==null){
                studentPro.setMark("无成绩");
                studentPro.setMarked(false);
            }else {
                studentPro.setMark(selectedcourse.getMark().toString());
                studentPro.setMarked(true);
            }

            studentPros.add(studentPro);
        }

        return studentPros;
    }

    @Override
    public int updateStudentMarkBySelectedCourse(Selectedcourse selectedcourse) {
        System.out.println("MAPPER");
        System.out.println(selectedcourse.toString());
        return selectedcourseMapper.updateMarkByStudentidAndCourseId(selectedcourse);
    }
}
