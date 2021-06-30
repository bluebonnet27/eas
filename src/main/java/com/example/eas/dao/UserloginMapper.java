package com.example.eas.dao;

import com.example.eas.entity.Userlogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserloginMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Userlogin record);

    int insertSelective(Userlogin record);

    Userlogin selectByPrimaryKey(Integer userid);

    //登录使用的自定义方法
    Userlogin selectByUserName(String username);

    int updateByPrimaryKeySelective(Userlogin record);

    int updateByPrimaryKey(Userlogin record);

    //2021年6月28日23:03:29 根据name删除
    int deleteByUserName(String username);
}