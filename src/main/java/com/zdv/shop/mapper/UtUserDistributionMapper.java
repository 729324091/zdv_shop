package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtUserDistribution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UtUserDistributionMapper extends MyMapper<UtUserDistribution> {


    List<UtUserDistribution> queryDustributionList(UtUserDistribution userDistribution);


    UtUserDistribution selectUserDistributionLastLevel(@Param("udistributionid") String udistributionid, @Param("ucustomerid") String ucustomerid);


    Double queryProfitSumByUcustomerid(@Param("ucustomerid") String ucustomerid, @Param("udistributionid") String udistributionid);

    int addUserDistribution(UtUserDistribution userDistribution);

    int updateUserDistributionByIds(UtUserDistribution userDistribution);

    int delUserDistributions(@Param("ulevel") String ulevel, @Param("ucustomerid") String ucustomerid);

    UtUserDistribution queryUserDistributionByUuserid(@Param("uuserid")String uuserid);
}
