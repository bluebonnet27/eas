package com.example.eas.dao;

import com.example.eas.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}