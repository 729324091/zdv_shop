package com.zdv.shop.service;

import com.zdv.shop.mapper.UtUserDevelopCompMapper;
import com.zdv.shop.model.UtUserDevelopComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UtUserDevelopCompService extends AbstratService<UtUserDevelopComp> {

    @Autowired
    private UtUserDevelopCompMapper userDevelopCompMapper;


    public UtUserDevelopComp getByUcompid(String ucompid) {
        return userDevelopCompMapper.getByUcompid(ucompid);
    }
}
