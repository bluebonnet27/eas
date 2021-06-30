package com.example.eas.service;

import com.example.eas.entity.Page;
import com.example.eas.entity.Student;
import com.example.eas.entity.spec.StudentSpec;

import java.util.ArrayList;

//student service
public interface StudentService {

    //2021年6月27日22:59:35 查询所有学生
    ArrayList<StudentSpec> selectAllStudentsByPage(Page<Student> page);

    //主键查询学生
    Student selectStudentByStudentid(int studentid);

    //更新学生信息
    int updateStudentByStudent(Student student);

    //根据id删除学生
    int delStudentByStudentId(int studentid);

    //插入学生
    int addStudent(Student student);
}
