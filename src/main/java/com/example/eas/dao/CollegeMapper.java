package com.example.eas.dao;

import com.example.eas.entity.College;

public interface CollegeMapper {
    int deleteByPrimaryKey(Integer collegeid);

    int insert(College record);

    int insertSelective(College record);

    College selectByPrimaryKey(Integer collegeid);

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);
}