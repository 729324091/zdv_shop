package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.mapper.UtAreaManagerMapper;
import com.zdv.shop.model.UtAreaManager;
import com.zdv.shop.model.UtUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtAreaManagerService extends AbstratService<UtAreaManager> {

    @Autowired
    private UtAreaManagerMapper areaManagerMapper;

    /**
     * 获取页面
     * @param page
     * @param areaManager
     * @return
     */
    public PageAjax<UtAreaManager> queryAreaManagerPage(PageAjax<UtAreaManager> page, UtAreaManager areaManager) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<UtAreaManager> list = areaManagerMapper.areaManagerList(areaManager);
        return new PageAjax<UtAreaManager>(list);
    }

    public AjaxResult updateAreaManager(UtAreaManager areaManager) {

        try {
            boolean b = areaManagerMapper.updateAreaManager(areaManager) > 0;

            if (b) {
                return new AjaxResult(1, "修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new AjaxResult();
    }

    public AjaxResult delAreaManager(String umanagerid) {
        try {
            boolean b = areaManagerMapper.delAreaManagerById(umanagerid) > 0;

            if (b) {
                return new AjaxResult(1, "删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new AjaxResult();
    }

    public AjaxResult addAreaManager(UtAreaManager areaManager) {
        try {
            boolean b = areaManagerMapper.addAreaManager(areaManager) > 0;

            if (b) {
                return new AjaxResult(1, "添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new AjaxResult();
    }
}
