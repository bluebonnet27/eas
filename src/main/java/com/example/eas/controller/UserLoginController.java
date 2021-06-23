package com.example.eas.controller;

import com.example.eas.entity.JsonResult;
import com.example.eas.entity.Userlogin;
import com.example.eas.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auserlogin")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping("/getUserLogin")
    public @ResponseBody void getUserLoginByUserid(int userid){
        Userlogin userlogin = userLoginService.selectUserLoginByUserid(userid);

        if(userlogin==null){
            System.out.println("EMPTY!");
        }else {
            String name = userlogin.getUsername();
            int role = userlogin.getRole();
            System.out.println("Name"+name);
            System.out.println("Role"+role);
        }
    }

    //role = 0 is admin,role = 1 is teacher,role = 2 is student
    @RequestMapping("/login")
    public @ResponseBody String getUserLoginByUserNameAndPassword(String username, String pwd){
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
                return loginResult.toString();
            }
        }
    }
}
