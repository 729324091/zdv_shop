package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtUserLevel;

import java.util.List;

public interface UtUserLevelMapper extends MyMapper<UtUserLevel>{

    int delUserLevelById(String uuserlevelid);

    int updateUserLevel(UtUserLevel userLevel);

    int addUserLevel(UtUserLevel userLevel);

    List<UtUserLevel> levelList(UtUserLevel userLevel);
}
