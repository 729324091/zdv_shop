package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtUserfreeproduct;
import org.apache.ibatis.annotations.Param;

public interface UtUserfreeproductMapper extends MyMapper<UtUserfreeproduct> {
    //获得最近的免费商品购买记录
    UtUserfreeproduct getLastFreeproduct(UtUserfreeproduct utUserfreeproduct);

    int saveUserfreeproduct(UtUserfreeproduct userfreeproduct);

    int delUserfreeproductByUorderitemid(@Param("uorderitemid") String uorderitemid);
}
