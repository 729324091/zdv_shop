package com.zdv.shop.service;

import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.mapper.UtMyproductstoreMapper;
import com.zdv.shop.model.UtMyproductstore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UtMyproductstoreService extends AbstratService<UtMyproductstore> {
    @Autowired
    private UtMyproductstoreMapper myproductstoreMapper;


    public AjaxResult addMyproductstore(UtMyproductstore utMyproductstore) {
        String result = "收藏成功";

        int i = myproductstoreMapper.addMyproductstore(utMyproductstore);
        if (i < 1) {
            result = "操作失败";
            return new AjaxResult(0, result);
        }
        return new AjaxResult(1, result);
    }

    public AjaxResult delMyproductstore(UtMyproductstore utMyproductstore) {
        String result = "取消收藏成功";
        int i = myproductstoreMapper.delMyproductstore(utMyproductstore);
        if (i < 1) {
            result = "操作失败";
            return new AjaxResult(0, result);
        }
        return new AjaxResult(1, result);
    }

    public List<UtMyproductstore> queryListByUserid(String uuserid, String ucustomerid) {

        UtMyproductstore utMyproductstore = new UtMyproductstore();
        utMyproductstore.setUcustomerid(ucustomerid);
        utMyproductstore.setUuserid(uuserid);
        return myproductstoreMapper.select(utMyproductstore);

    }
}
