package com.example.eas.service;

import com.example.eas.entity.Userlogin;

public interface UserLoginService {
    //Test
    Userlogin selectUserLoginByUserid(int userid);

    //登录
    Userlogin selectUserLoginByUserName(String username);

    //2021年6月27日09:52:02 更新用户数据
    int updateUserLoginByUserIdAndPassword(int userid,String pwd);

    //2021年6月28日23:04:32 根据username删除数据
    int delUserLogin(String username);
}
