package com.zdv.shop.service;

import com.zdv.shop.model.OtProducttype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.mapper.OtCompProductTypeMapper;
import com.zdv.shop.model.OtCompProductType;

import java.util.List;

/**
 * 销售商自定义产品类型
 * @author LBY
 * @date: 2018年12月7日
 */
@Service
public class OtCompProductTypeService extends AbstratService<OtCompProductType> {

	@Autowired
	OtCompProductTypeMapper mapper;

    public List<OtProducttype> queryComProductTypeListWithH5(String ucustomerid) {

//        Integer cucustomerid = mapper.get_SN(2);
        //查询产品类型表所有父级数据
        List<OtProducttype> list = mapper.selectFirstComProductTypeList(ucustomerid);
        if (list != null && list.size() > 0) {
            for (OtProducttype otProducttype : list)
                otProducttype.setUchildList(mapper.selectChildComProductTypeList(otProducttype.getUproducttypeid(),ucustomerid));
        }
        return list;
    }
}
