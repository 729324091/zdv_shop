package com.zdv.shop.service;

import com.zdv.shop.mapper.UtUserfreeproductMapper;
import com.zdv.shop.model.UtUserfreeproduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UtUserfreeproductService extends AbstratService<UtUserfreeproduct> {

    @Autowired
    private UtUserfreeproductMapper userfreeproductMapper;

    public UtUserfreeproduct getLastFreeproduct(UtUserfreeproduct utUserfreeproduct) {

        return userfreeproductMapper.getLastFreeproduct(utUserfreeproduct);
    }

    public int saveUserfreeproduct(UtUserfreeproduct userfreeproduct) {
        return userfreeproductMapper.saveUserfreeproduct(userfreeproduct);
    }

    public int delUserfreeproductByUorderitemid(String uorderitemid) {
        return userfreeproductMapper.delUserfreeproductByUorderitemid(uorderitemid);

    }
}
