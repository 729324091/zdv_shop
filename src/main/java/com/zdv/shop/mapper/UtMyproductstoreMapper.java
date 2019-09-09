package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtMyproductstore;

import java.util.List;


public interface UtMyproductstoreMapper extends MyMapper<UtMyproductstore> {


    int addMyproductstore(UtMyproductstore utMyproductstore);

    int delMyproductstore(UtMyproductstore utMyproductstore);

    List<UtMyproductstore> queryListByUserid(String uuserid, String ucustomerid);
}
