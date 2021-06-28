package com.example.eas.controller;

import com.example.eas.entity.Course;
import com.example.eas.entity.JsonResult;
import com.example.eas.entity.Selectedcourse;
import com.example.eas.entity.pro.StudentPro;
import com.example.eas.service.CourseService;
import com.example.eas.service.SelectedcourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/ateacher")
public class TeacherController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SelectedcourseService selectedcourseService;

    //傻逼的全局变量
    private int xstudentid;

    private int xcourseid;

    @RequestMapping("/teachercourses")
    public String showCoursesByTeacherId(Model model,
                                         HttpSession session){
        int teacherid = Integer.parseInt(session.getAttribute("user").toString());
        ArrayList<Course> courses = courseService.selectCoursesByTeacherid(teacherid);
        model.addAttribute("courses",courses);
        model.addAttribute("user",teacherid);
        model.addAttribute("pageTitle","我教授的课程");
        model.addAttribute("enum",courses.size());
        return "teacher/mycourses";
    }

    @RequestMapping("/scorecourses")
    public String showMarkedStudentProsByCourseId(Model model,
                                                  int courseid,
                                                  HttpSession session){
        int teacherid = Integer.parseInt(session.getAttribute("user").toString());
        ArrayList<StudentPro> studentPros =
                selectedcourseService.selectStudentProsByCourseid(courseid);
        model.addAttribute("studentpros",studentPros);
        model.addAttribute("user",teacherid);
        model.addAttribute("pageTitle","已选该课程的学生名单");
        model.addAttribute("courseid",courseid);
        model.addAttribute("enum",studentPros.size());
        return "teacher/markcourses";
    }

    @RequestMapping("/markstudentjump")
    public String jumpToMarkStudent(Model model,
                                    int studentid,
                                    int courseid,
                                    String studentname,
                                    HttpSession session){
        int teacherid = Integer.parseInt(session.getAttribute("user").toString());

        xstudentid = studentid;
        xcourseid = courseid;

        model.addAttribute("user",teacherid);
        model.addAttribute("studentid",studentid);
        model.addAttribute("studentname",studentname);
        model.addAttribute("courseid",courseid);
        model.addAttribute("pageTitle","为这名学生打分");
        model.addAttribute("enum",0);

        return "teacher/markstudent";
    }

    @RequestMapping("/markstudent")
    public @ResponseBody String markStudent(int mark,
                                            HttpSession session){
        JsonResult updateResult = new JsonResult();
        Selectedcourse selectedcourse = new Selectedcourse(xcourseid,xstudentid,mark);
        int backValue = selectedcourseService.updateStudentMarkBySelectedCourse(selectedcourse);

        if(backValue==1){
            updateResult.setResult(true);
            updateResult.setResMsg("更新分数成功");
            return updateResult.toString();
        }else {
            updateResult.setResult(false);
            updateResult.setErrMsg("更新分数失败，原因来自数据库");
            return updateResult.toString();
        }
    }

    @RequestMapping("/markstudentfm")
    public String markStudentFm(String studentid,
                                String courseid,
                                String studentname,
                                String studentmark,
                                HttpSession session,
                                Model model){
        int sid = Integer.parseInt(studentid);
        int cid = Integer.parseInt(courseid);
        int sname = Integer.parseInt(studentname);
        int mk = Integer.parseInt(studentmark);

        Selectedcourse selectedcourse = new Selectedcourse(sid,cid,mk);
        int backValue = selectedcourseService.updateStudentMarkBySelectedCourse(selectedcourse);

        if(backValue==1){
            model.addAttribute("title","SUCCESS");
            model.addAttribute("desc","Update");
            return "errorall";
        }else {
            model.addAttribute("title","FUCK");
            model.addAttribute("desc","FUCK");
            return "errorall";
        }
    }
}
