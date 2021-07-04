package com.example.eas.service.impl;

import com.example.eas.dao.LoginrecordMapper;
import com.example.eas.entity.Loginrecord;
import com.example.eas.entity.Page;
import com.example.eas.service.LoginrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginrecordServiceImpl implements LoginrecordService {

    @Autowired
    private LoginrecordMapper loginrecordMapper;

    @Override
    public int addNewLoginrecord(Loginrecord loginrecord) {
        return loginrecordMapper.insertSelective(loginrecord);
    }

    @Override
    public ArrayList<Loginrecord> selectAllLoginrecordsByPage(Page<Loginrecord> page) {
        return loginrecordMapper.selectAllByPage(page);
    }
}
