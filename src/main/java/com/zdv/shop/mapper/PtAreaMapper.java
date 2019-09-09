package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.PtArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 区域表mapper
 * @author LBY
 * @date: 2018年12月17日
 */
public interface PtAreaMapper extends MyMapper<PtArea> {
    List<PtArea> queryTopArea();

    List<PtArea> queryAreaByUparentid(@Param("uparentid") String uparentid);
}
