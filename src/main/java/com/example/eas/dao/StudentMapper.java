package com.example.eas.dao;

import com.example.eas.entity.Page;
import com.example.eas.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    //2021年6月27日22:58:36 查询所有学生
    ArrayList<Student> selectAllStudentsByPage(Page<Student> page);
}