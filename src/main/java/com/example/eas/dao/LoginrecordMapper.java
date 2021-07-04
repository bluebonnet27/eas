package com.example.eas.dao;

import com.example.eas.entity.Loginrecord;
import com.example.eas.entity.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface LoginrecordMapper {
    int deleteByPrimaryKey(String time);

    int insert(Loginrecord record);

    int insertSelective(Loginrecord record);

    Loginrecord selectByPrimaryKey(String time);

    int updateByPrimaryKeySelective(Loginrecord record);

    int updateByPrimaryKey(Loginrecord record);

    //分页查询所有登录记录
    ArrayList<Loginrecord> selectAllByPage(Page<Loginrecord> page);
}