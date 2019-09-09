package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtCompProductType;
import com.zdv.shop.model.OtProducttype;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售商自定义产品类型
 * @author LBY
 * @date: 2018年12月7日
 */
public interface OtCompProductTypeMapper extends MyMapper<OtCompProductType> {

    List<OtProducttype> selectFirstComProductTypeList(@Param("ucustomerid")String ucustomerid);

    List<OtProducttype> selectChildComProductTypeList(@Param("uproducttypeid") String uproducttypeid,@Param("ucustomerid")String ucustomerid);

    Integer get_SN(Integer flag);

}
