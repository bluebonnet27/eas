package com.example.eas.controller;

import com.example.eas.entity.JsonResult;
import com.example.eas.entity.Userlogin;
import com.example.eas.service.UserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auserlogin")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    //登录跳转
//    @RequestMapping(value = "/login", method = {RequestMethod.GET})
//    public String loginGET() throws Exception {
//        return "../../login";
//    }

    //role = 0 is admin,role = 1 is teacher,role = 2 is student
    @RequestMapping("/login")
    public @ResponseBody String getUserLoginByUserNameAndPassword(String username,
                                                                  String pwd,
                                                                  HttpSession session){
        JsonResult loginResult = new JsonResult();
        Userlogin userlogin = userLoginService.selectUserLoginByUserName(username);
        if (userlogin==null){
            System.out.println("EMPTY!");
            loginResult.setResult(false);
            loginResult.setErrMsg("查无此人！");
            return loginResult.toString();
        }else {
            String upwd = userlogin.getPassword();
            if(!upwd.equals(pwd)){
                System.out.println("PASSWARD IS WRONG!");
                loginResult.setResult(false);
                loginResult.setErrMsg("密码错了！");
                return loginResult.toString();
            }else {
                System.out.println("LOGIN SUCCESS!");
                loginResult.setResult(true);
                if(userlogin.getRole()==0){
                    loginResult.setResMsg("0");
                }else if (userlogin.getRole()==1){
                    loginResult.setResMsg("1");
                }else {
                    loginResult.setResMsg("2");
                }
                session.setAttribute("user",userlogin.getUsername());
                return loginResult.toString();
            }
        }
    }

    //登录表单处理
    @RequestMapping(value = "/loginold")
    public String login(Userlogin userlogin) throws Exception {
        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getUsername(),
                userlogin.getPassword());

        // 获取Subject单例对象
        Subject subject = SecurityUtils.getSubject();

        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        //调用login(token)方法时会调用LoginRealm中的doGetAuthenticationInfo方法
        subject.login(token);

        //当调用subject.hasRole()方法时，就会执行LoginRealm中的doGetAuthorizationInfo方法
        if (subject.hasRole("admin")) {
            return "redirect:/admin/showStudent";
        } else if (subject.hasRole("teacher")) {
            return "redirect:/teacher/showCourse";
        } else if (subject.hasRole("student")) {
            return "redirect:/astudent/studentallcourses";
        }

        return "/login";
    }

}
