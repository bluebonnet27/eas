package com.example.eas.dao;

import com.example.eas.entity.Selectedcourse;

public interface SelectedcourseMapper {
    int insert(Selectedcourse record);

    int insertSelective(Selectedcourse record);
}