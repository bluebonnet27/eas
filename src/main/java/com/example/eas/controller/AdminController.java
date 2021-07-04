package com.example.eas.controller;

import com.example.eas.controller.converter.DateConverter;
import com.example.eas.entity.*;
import com.example.eas.entity.spec.StudentSpec;
import com.example.eas.entity.spec.TeacherSpec;
import com.example.eas.service.*;
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
@RequestMapping("/admincontroller")
public class AdminController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private SelectedcourseService selectedcourseService;

    @Autowired
    private LoginrecordService loginrecordService;

    //用于表单填充的全局变量
    private int astudentid;

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
        model.addAttribute("enum",pages.getTotalRecord());

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
        model.addAttribute("enum",pages.getTotalRecord());

        return "admin/allstudents";
    }

    @RequestMapping("/achangestudent")
    public String jumpToChangeStudent(Model model,
                                      int studentid,
                                      HttpSession session){
        astudentid = studentid;
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("studetid",studentid);
        model.addAttribute("pageTitle","修改学生信息");
        return "admin/changestudent";
    }

    @RequestMapping("/changestudent")
    public @ResponseBody String changeStudent(String studentname,
                                              String sex,
                                              String birthyear,
                                              String gradeyear,
                                              String collegeid){
        JsonResult changeResult = new JsonResult();
        Student findStudent = studentService.selectStudentByStudentid(astudentid);

        if(findStudent==null){
            changeResult.setResult(false);
            changeResult.setErrMsg("没有在数据库找到要修改的学生");
            return changeResult.toString();
        }else {
            System.out.println("Studentname= "+studentname);
            System.out.println("Studentsex= "+sex);
            System.out.println("Studentbirthyear= "+birthyear);
            System.out.println("Studentgradeyear= "+gradeyear);
            System.out.println("Studentcollegeid= "+collegeid);
            Student newStudent = new Student();
            DateConverter dateConverter = new DateConverter();

            newStudent.setUserid(astudentid);
            newStudent.setUsername(studentname);
            newStudent.setSex(sex);
            newStudent.setBirthyear(dateConverter.convertEasy(birthyear));
            newStudent.setGrade(dateConverter.convertEasy(gradeyear));
            newStudent.setCollegeid(Integer.parseInt(collegeid));

            int backValue = studentService.updateStudentByStudent(newStudent);

            if(backValue==1){
                changeResult.setResult(true);
                changeResult.setResMsg("更新数据成功！");
                return changeResult.toString();
            }else {
                changeResult.setResult(false);
                changeResult.setErrMsg("更新数据失败，原因来自数据库！");
                return changeResult.toString();
            }
        }
    }

    @RequestMapping("/delstudent")
    public String delStudent(Model model,
                             int studentid){
        //JsonResult delResult = new JsonResult();
        Student student = studentService.selectStudentByStudentid(studentid);
        String studentname = student.getUsername();

        //学生表删除
        int backValueStudent = studentService.delStudentByStudentId(studentid);
        //选课表删除
        int backValueSelectedCourse = selectedcourseService.delSelectedCourse(studentid);
        //登录表删除
        int backValueUserLogin = userLoginService.delUserLogin(studentname);

        if(backValueStudent==1){
            model.addAttribute("title","删除成功！");
            model.addAttribute("desc","删除的学生信息为：    "+
                    "id："+studentid+"    "+
                    "名字："+studentname+"    "+
                    "选课表删除数据个数："+backValueSelectedCourse+"    "+
                    "登录表删除数据个数："+backValueUserLogin+"    "+
                    "删除成功！");
            return "errorall";
        }else {
            model.addAttribute("title","删除失败！");
            model.addAttribute("desc","原因来自数据库!");
            return "errorall";
        }
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
        model.addAttribute("enum",pages.getTotalRecord());

        return "admin/allteachers";
    }

    @RequestMapping("/ashowallloginrecords")
    public String showAllLoginRecordsByPage(Model model,
                                            @RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer limit,
                                            HttpSession session){
        Page<Loginrecord> pages = new Page<>(page,limit);
        ArrayList<Loginrecord> loginrecords = loginrecordService.selectAllLoginrecordsByPage(pages);

        model.addAttribute("pages",pages);
        model.addAttribute("loginrecords",loginrecords);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("pageTitle","所有登录记录");
        model.addAttribute("enum",pages.getTotalRecord());

        return "admin/loginrecords";
    }

    @RequestMapping("/achangeuser")
    public String jumpToChangeUser(Model model,
                                   HttpSession session){
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("pageTitle","更改非管理员账户的信息");

        return "admin/changeuserall";
    }

    @RequestMapping("/changeuser")
    public @ResponseBody String changeUser(String username,
                                           String upwd,
                                           String upwd2){
        JsonResult changeResult = new JsonResult();
        Userlogin findUser = userLoginService.selectUserLoginByUserName(username);
        if(findUser==null){
            changeResult.setResult(false);
            changeResult.setErrMsg("该用户不存在！");
            return changeResult.toString();
        }else {
            if(findUser.getRole()==0){
                changeResult.setResult(false);
                changeResult.setErrMsg("你正试图更改管理员账户！");
                return changeResult.toString();
            }else {
                if(!upwd.equals(upwd2)){
                    changeResult.setResult(false);
                    changeResult.setErrMsg("两次输入的密码不一致！");
                    return changeResult.toString();
                }
                int newuserid = findUser.getUserid();
                int backValue = userLoginService.updateUserLoginByUserIdAndPassword(newuserid,upwd);
                //newUser.setRole(findUser.getRole());
                if(backValue==1){
                    changeResult.setResult(true);
                    changeResult.setResMsg("修改成功");
                    return changeResult.toString();
                }else {
                    changeResult.setResult(false);
                    changeResult.setErrMsg("遇到来自数据库的错误！");
                    return changeResult.toString();
                }
            }
        }
    }

    @RequestMapping("/aaddstudent")
    public String jumpToAddStudent(Model model,
                                   HttpSession session){
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("pageTitle","加入一个新学生");
        return "admin/addstudentwow";
    }

    @RequestMapping("/addstudent")
    public @ResponseBody String addStudent(int studentid,
                                           String studentname,
                                           String sex,
                                           String birthyear,
                                           String gradeyear,
                                           String collegeid){
        JsonResult addResult = new JsonResult();
        Student findStudent = studentService.selectStudentByStudentid(studentid);
        if(findStudent!=null){
            addResult.setResult(false);
            addResult.setErrMsg("学号重复！");
        }else {
            Student newStudent = new Student();
            DateConverter dateConverter = new DateConverter();

            newStudent.setUserid(studentid);
            newStudent.setUsername(studentname);
            newStudent.setSex(sex);
            newStudent.setBirthyear(dateConverter.convertEasy(birthyear));
            newStudent.setGrade(dateConverter.convertEasy(gradeyear));
            newStudent.setCollegeid(Integer.parseInt(collegeid));

            int backValue = studentService.addStudent(newStudent);
            addResult.setResult(true);
            addResult.setErrMsg(String.valueOf(backValue));
        }

        return addResult.toString();
    }

    @RequestMapping("/logout")
    public String adminLogout(Model model,
                              HttpSession session){
        session.removeAttribute("user");
        return "index";
    }
}
