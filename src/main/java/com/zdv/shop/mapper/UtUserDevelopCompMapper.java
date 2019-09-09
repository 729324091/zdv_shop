package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtUserDevelopComp;
import com.zdv.shop.model.vo.TeamUserVo;

import java.util.List;

public interface UtUserDevelopCompMapper extends MyMapper<UtUserDevelopComp> {

    UtUserDevelopComp getByUcompid(String ucompid);

    List<TeamUserVo> listChildren(String uuserid);
}
