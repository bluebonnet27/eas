package com.example.eas.service;

import com.example.eas.entity.Loginrecord;
import com.example.eas.entity.Page;

import java.util.ArrayList;

public interface LoginrecordService {

    //插入登录记录
    int addNewLoginrecord(Loginrecord loginrecord);

    //查找所有登录记录，并且分页
    ArrayList<Loginrecord> selectAllLoginrecordsByPage(Page<Loginrecord> page);
}
