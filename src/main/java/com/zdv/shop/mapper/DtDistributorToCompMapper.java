package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.DtDistributorTComp;

import java.util.List;

/**
 * 经销商管理的销售商中间表Mapper
 * @author LBY
 * @date: 2018年12月17日
 */
public interface DtDistributorToCompMapper extends MyMapper<DtDistributorTComp> {

    int addDistribtorAddComp(DtDistributorTComp dtDistributorTComp);

    List<DtDistributorTComp> queryDistributorToComp(DtDistributorTComp dtDistributorTComp);
}
