package com.example.eas.controller;

import com.example.eas.entity.*;
import com.example.eas.entity.pro.CoursePro;
import com.example.eas.service.CourseService;
import com.example.eas.service.SelectedcourseService;
import com.example.eas.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
@RequestMapping("/astudent")
public class StudentController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SelectedcourseService selectedcourseService;

    @RequestMapping("/studentallcourses")
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

        return "student/allCourses";
    }

    @RequestMapping("/selectcourse")
    public String selectCourse(int courseid,
                               HttpSession session,
                               Model model){
        int studentid = Integer.parseInt(session.getAttribute("user").toString());
        Selectedcourse selectedcourse =
                selectedcourseService.selectSelectedCourseByStudentidAndCourseid(studentid,courseid);

        if(selectedcourse != null){
            //System.out.println("选课错误 Course id "+selectedcourse.getCourseid());
            //System.out.println("选课错误 Student id "+selectedcourse.getStudentid());
            model.addAttribute("title","选课错误");
            model.addAttribute("userID",session.getAttribute("user"));
            model.addAttribute("desc","你已经选择了这门课，你不能再选！");
            return "errorall";
        }else {
            //System.out.println("选课Right Course id "+courseid);
            //System.out.println("选课Right Student id "+studentid);

            int result = selectedcourseService.insertSelectedCourseByStudentidAndCourseid(studentid,courseid);
            if(result == 0){
                model.addAttribute("title","插入失败");
                model.addAttribute("userID",session.getAttribute("user"));
                model.addAttribute("desc","发生了一个未知的错误导致插入失败！");
                return "errorall";
            }else {
                return "redirect:/astudent/studentallcourses";
            }
        }
    }

    @RequestMapping("/studentselectedcourses")
    public String showCoursesSelectedFromCourseByStudentid(Model model,
                                                           HttpSession session){
        int studentid = Integer.parseInt(session.getAttribute("user").toString());
        ArrayList<Course> courses = selectedcourseService.selectCoursesByStudentid(studentid);

        model.addAttribute("courses",courses);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("pageTitle","所有已选课程");

        return "student/selectedCourses";
    }

    @RequestMapping("/studentmarkedcourses")
    public String showCoursesMarkedFromCourseByStudentid(Model model,
                                                         HttpSession session){
        int studentid = Integer.parseInt(session.getAttribute("user").toString());
        System.out.println("studentid="+studentid);
        ArrayList<CoursePro> coursePros =
                selectedcourseService.selectCourseProsMarkedByStudentid(studentid);
        System.out.println("CONTROLLER coursePros size"+coursePros.size());
        for(CoursePro coursePro:coursePros){
            System.out.println(coursePro);
        }

        model.addAttribute("coursePros",coursePros);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("pageTitle","所有已修课程");

        return "student/markedCourses";
    }

    @RequestMapping("/studentchangepassword")
    public String jumpToChangePasswordPage(Model model,
                                           HttpSession session){
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("pageTitle","修改密码");
        return "student/changePassword";
    }

    @RequestMapping("/changepwd")
    public @ResponseBody String changeStudentPassword(String oldpwd,
                                                      String newpwd,
                                                      String newpwd2,
                                                      HttpSession session){
        JsonResult changePwdResult = new JsonResult();
        String username = session.getAttribute("user").toString();
        Userlogin userlogin = userLoginService.selectUserLoginByUserName(username);
        String selectedPwd = userlogin.getPassword();
        if(!selectedPwd.equals(oldpwd)){
            changePwdResult.setResult(false);
            changePwdResult.setErrMsg("旧密码不对，检查下是否输错了");
            return changePwdResult.toString();
        }else{
            if(!newpwd.equals(newpwd2)){
                changePwdResult.setResult(false);
                changePwdResult.setErrMsg("两次输入的密码不一致");
                return changePwdResult.toString();
            }else {
                int userid = userlogin.getUserid();
                int backValue = userLoginService.updateUserLoginByUserIdAndPassword(userid,newpwd);

                if(backValue==1){
                    changePwdResult.setResult(true);
                    changePwdResult.setResMsg("修改成功，你的登录信息已被清除，请重新登录");
                    return changePwdResult.toString();
                }else {
                    changePwdResult.setResult(false);
                    changePwdResult.setErrMsg("遇到了一个数据库方面的错误");
                    return changePwdResult.toString();
                }
            }
        }
    }

    @RequestMapping("/logout")
    public String studentLogout(Model model,
                                HttpSession session){
        session.removeAttribute("user");
        return "index";
    }
}
