package com.zdv.shop.service;

import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.mapper.PtAreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.model.PtArea;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 区域 Service层
 * @author LBY
 * @date: 2018年12月17日
 */
@Service
public class PtAreaService extends AbstratService<PtArea> {

    @Autowired
    private PtAreaMapper mapper;

    public List<PtArea> fuzzyQueryArea(String uareaname) {
        Example example = new Example(PtArea.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("uareaname","%"+uareaname+"%");

        List<PtArea> list = mapper.selectByExample(example);
        return list;

    }

    @ServiceLog("添加地区")
    public AjaxResult addArea(PtArea area) {
        String result = null;
        try {
            boolean b = mapper.insert(area) > 0;
            if (b) {
                result = "添加成功";
                return new AjaxResult(1, result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult();

    }


    public PtArea queryAreaById(String uareaid) {
        PtArea ptArea = mapper.selectByPrimaryKey(uareaid);
        return ptArea;
    }

    public AjaxResult updateArea(PtArea area) {
        String result = null;
        try {
            int i = mapper.updateByPrimaryKey(area);

            if (i > 0) {
                result = "更新成功";
                return new AjaxResult(1, result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult();
    }


    /**
     * 查询省级
     * @return
     */
    public List<PtArea> queryTopArea() {
        return mapper.queryTopArea();
    }


    public List<PtArea> queryAreaByUparentid(String uparentid) {
        return mapper.queryAreaByUparentid(uparentid);
    }

}
