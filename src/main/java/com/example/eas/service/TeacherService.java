package com.example.eas.service;

import com.example.eas.entity.Page;
import com.example.eas.entity.Teacher;
import com.example.eas.entity.spec.TeacherSpec;

import java.util.ArrayList;

public interface TeacherService {

    ArrayList<TeacherSpec> selectTeachersByPage(Page<Teacher> page);
}
