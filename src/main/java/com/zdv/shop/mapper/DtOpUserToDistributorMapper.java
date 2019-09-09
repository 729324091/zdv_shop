package com.zdv.shop.mapper;

import java.util.List;
import java.util.Map;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.DtOpUserToDistributor;
import com.zdv.shop.model.vo.AppQueryOpuserVO;

/**
 * 管理员与经销商（供应商）中间表 Mapper
 * @author LBY
 * @date: 2018年12月14日
 */
public interface DtOpUserToDistributorMapper extends MyMapper<DtOpUserToDistributor> {

    int updateUTD(DtOpUserToDistributor opUserToDistributor);
    
    /**
     * APP查询经销商管理人员
     * @author LBY
     * @data 2019年1月15日
     * @param udistributorid
     * @param uroleid
     * @return
     */
    List<Map<String, Object>> appQueryPossessUsers(AppQueryOpuserVO argVo);
    //商家销售产品中对应的经销商
    List<Map<String,Object>> opuserDistributorBycomproductid(String ucomproductid);

    List<Map<String,Object>> opuserDistributorBydistributorid(String udistributorid);

}
