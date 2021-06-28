package com.example.eas.dao;

import com.example.eas.entity.Page;
import com.example.eas.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TeacherMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    //分页查询所有教师信息
    ArrayList<Teacher> selectTeachersByPage(Page<Teacher> page);
}