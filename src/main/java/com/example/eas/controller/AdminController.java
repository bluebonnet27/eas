package com.example.eas.controller;

import com.example.eas.entity.Course;
import com.example.eas.entity.Page;
import com.example.eas.entity.Student;
import com.example.eas.entity.Teacher;
import com.example.eas.entity.spec.StudentSpec;
import com.example.eas.entity.spec.TeacherSpec;
import com.example.eas.service.CourseService;
import com.example.eas.service.StudentService;
import com.example.eas.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/admincontroller")
public class AdminController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/ashowallcourses")
    public String showCoursesFromCourseByPage(Model model,
                                              @RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "5") Integer limit,
                                              HttpSession session){
        Page<Course> pages = new Page<>(page,limit);

        ArrayList<Course> courses = courseService.selectCoursesByPageService(pages);

        model.addAttribute("pages",pages);
        model.addAttribute("courses",courses);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("pageTitle","所有课程");
        model.addAttribute("enum",courses.size());

        return "admin/allcourses";
    }

    @RequestMapping("/ashowallstudents")
    public String showStudentsFromStudentByPage(Model model,
                                              @RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "5") Integer limit,
                                              HttpSession session){
        Page<Student> pages = new Page<>(page,limit);

        ArrayList<StudentSpec> students = studentService.selectAllStudentsByPage(pages);

        model.addAttribute("pages",pages);
        model.addAttribute("students",students);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("pageTitle","所有学生");
        model.addAttribute("enum",students.size());

        return "admin/allstudents";
    }

    @RequestMapping("/ashowallteachers")
    public String showTeachersFromTeacherByPage(Model model,
                                                @RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "5") Integer limit,
                                                HttpSession session){
        Page<Teacher> pages = new Page<>(page,limit);

        ArrayList<TeacherSpec> teacherSpecs = teacherService.selectTeachersByPage(pages);

        model.addAttribute("pages",pages);
        model.addAttribute("teachers",teacherSpecs);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("pageTitle","所有学生");
        model.addAttribute("enum",teacherSpecs.size());

        return "admin/allteachers";
    }
}
