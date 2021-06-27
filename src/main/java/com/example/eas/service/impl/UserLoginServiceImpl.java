package com.example.eas.service.impl;

import com.example.eas.dao.UserloginMapper;
import com.example.eas.entity.Userlogin;
import com.example.eas.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserLoginService")
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserloginMapper userloginMapper;

    @Override
    public Userlogin selectUserLoginByUserid(int userid) {
        return userloginMapper.selectByPrimaryKey(userid);
    }

    @Override
    public Userlogin selectUserLoginByUserName(String username) {
        return userloginMapper.selectByUserName(username);
    }

    @Override
    /**
     * 返回的数字即为受影响的条数
     */
    public int updateUserLoginByUserIdAndPassword(int userid, String pwd) {
        Userlogin userlogin = new Userlogin();
        userlogin.setPassword(pwd);
        userlogin.setUserid(userid);

        return userloginMapper.updateByPrimaryKeySelective(userlogin);
    }
}
