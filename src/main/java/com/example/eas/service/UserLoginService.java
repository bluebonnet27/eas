package com.example.eas.service;

import com.example.eas.entity.Userlogin;

public interface UserLoginService {
    //Test
    Userlogin selectUserLoginByUserid(int userid);

    //登录
    Userlogin selectUserLoginByUserName(String username);
}
