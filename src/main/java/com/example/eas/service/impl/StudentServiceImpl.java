package com.example.eas.service.impl;

import com.example.eas.controller.converter.DateConverter;
import com.example.eas.dao.CollegeMapper;
import com.example.eas.dao.StudentMapper;
import com.example.eas.entity.Page;
import com.example.eas.entity.Student;
import com.example.eas.entity.spec.StudentSpec;
import com.example.eas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public ArrayList<StudentSpec> selectAllStudentsByPage(Page<Student> page) {
        ArrayList<Student> students = studentMapper.selectAllStudentsByPage(page);
        ArrayList<StudentSpec> studentSpecs = new ArrayList<>();
        DateConverter dateConverter = new DateConverter();

        for(Student student:students){
            StudentSpec studentSpec = new StudentSpec();
            //只加入有用的值
            studentSpec.setUserid(student.getUserid());
            studentSpec.setUsername(student.getUsername());
            studentSpec.setSex(student.getSex());
            //日期的特殊处理
            studentSpec.setBirthyearSpec(dateConverter.formatDate(student.getBirthyear()));
            studentSpec.setGradeSpec(dateConverter.formatDate(student.getGrade()));
            //系名的特殊处理
            studentSpec.setCollegeidSpec(
                    collegeMapper.selectByPrimaryKey(student.getCollegeid()).getCollegename());

            studentSpecs.add(studentSpec);
        }
        return studentSpecs;
    }

    @Override
    public Student selectStudentByStudentid(int studentid) {
        return studentMapper.selectByPrimaryKey(studentid);
    }

    @Override
    public int updateStudentByStudent(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public int delStudentByStudentId(int studentid) {
        return studentMapper.deleteByPrimaryKey(studentid);
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.insertSelective(student);
    }
}
