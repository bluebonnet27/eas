package com.example.eas.service.impl;

import com.example.eas.controller.converter.DateConverter;
import com.example.eas.dao.CollegeMapper;
import com.example.eas.dao.TeacherMapper;
import com.example.eas.entity.Page;
import com.example.eas.entity.Teacher;
import com.example.eas.entity.spec.TeacherSpec;
import com.example.eas.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public ArrayList<TeacherSpec> selectTeachersByPage(Page<Teacher> page) {
        ArrayList<Teacher> teachers = teacherMapper.selectTeachersByPage(page);
        ArrayList<TeacherSpec> teacherSpecs = new ArrayList<>();
        DateConverter dateConverter = new DateConverter();

        for(Teacher teacher:teachers){
            TeacherSpec teacherSpec = new TeacherSpec();
            //有用的数据
            teacherSpec.setUserid(teacher.getUserid());
            teacherSpec.setUsername(teacher.getUsername());
            teacherSpec.setSex(teacher.getSex());
            teacherSpec.setDegree(teacher.getDegree());
            teacherSpec.setTitle(teacher.getTitle());
            //时间
            teacherSpec.setBirthyearSpec(dateConverter.formatDate(teacher.getBirthyear()));
            teacherSpec.setGradeSpec(dateConverter.formatDate(teacher.getGrade()));
            //系名
            teacherSpec.setCollegeidSpec(
                    collegeMapper.selectByPrimaryKey(teacher.getCollegeid()).getCollegename());

            teacherSpecs.add(teacherSpec);
        }

        return teacherSpecs;
    }
}
