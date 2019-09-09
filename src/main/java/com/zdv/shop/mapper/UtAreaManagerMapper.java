package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtAreaManager;

import java.util.List;

public interface UtAreaManagerMapper extends MyMapper<UtAreaManager> {

    int delAreaManagerById(String umanagerid);

    int updateAreaManager(UtAreaManager areaManager);

    int addAreaManager(UtAreaManager areaManager);

    List<UtAreaManager> areaManagerList(UtAreaManager areaManager);


}
