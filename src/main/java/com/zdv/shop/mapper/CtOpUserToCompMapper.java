package com.zdv.shop.mapper;

import java.util.List;
import java.util.Map;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.CtOpUserToComp;
import com.zdv.shop.model.vo.AppQueryOpuserVO;


public interface CtOpUserToCompMapper extends MyMapper<CtOpUserToComp> {
    int updateOpusercomp(CtOpUserToComp opUserToComp);
    
    /**
     * APP查询经销商管理工作人员
     * @author LBY
     * @data 2019年1月15日
     * @param ucompid
     * @param uroleid
     * @return
     */
    List<Map<String, Object>> appQueryOpuser(AppQueryOpuserVO argVo);
    //获得零售店下所有管理员ID
    List<Map<String,Object>> opusercomplistByCompid(String ucompid);
}