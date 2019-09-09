package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.mapper.PtOperationMapper;
import com.zdv.shop.model.PtOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PtOperationService extends AbstratService<PtOperation> {
    @Autowired
    private PtOperationMapper ptOperationMapper;


    @ServiceLog("获得功能列表")
    public List<PtOperation> queryAllOpers() {
        return ptOperationMapper.queryAllOpers();
    }

    public PtOperation selectOperationById(String uopid){
        return ptOperationMapper.selectOperationById(uopid);
    }

    @ServiceLog("分页查询")
    public PageAjax<PtOperation> queryPage(PageAjax<PtOperation> page, PtOperation operation){
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<PtOperation> list = queryList(operation);
        return new PageAjax<PtOperation>(list);
    }

    @ServiceLog("获得功能列表")
    public List<PtOperation> queryOpers(String uoptype) {
        return ptOperationMapper.queryOpers(uoptype);
    }
    @ServiceLog("根据经销商角色id获得权限")
    public List<PtOperation> queryByDTUroleid(String uroleid) {
        return ptOperationMapper.queryByDTUroleid(uroleid);

    }

    @ServiceLog("根据销售商角色id获得权限")
    public List<PtOperation> queryByCTUroleid(String uroleid) {
        return ptOperationMapper.queryByCTUroleid(uroleid);

    }


  /*  public List<PtOperation> queryOperByUroleid(String uroleid){
        return ptOperationMapper.queryOperByUroleid(uroleid);
    }*/

}
