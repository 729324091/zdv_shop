package com.zdv.shop.service;

import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.mapper.DtDistributorToCompMapper;
import com.zdv.shop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 经销商管理的销售商中间表
 * @author LBY
 * @date: 2018年12月17日
 */
@Service
@Transactional
public class DtDistributorToCompService extends AbstratService<DtDistributorTComp> {

    @Autowired
    private DtDistributorService distributorService;
    @Autowired
    private DtDistributorToCompMapper distributorToCompMapper;
    @Autowired
    private UtUserService userService;
    @Autowired
    private OpUsersService opUsersService;
    @Autowired
    private DtDistributorRoleService distributorRoleService;
    @Autowired
    private DtOpUserToDistributorService opUserToDistributorMapper;

    public AjaxResult addDistributorToComp(DtDistributorTComp dtDistributorTComp, DtDistributor dtDistributor) {

        int i = distributorToCompMapper.addDistribtorAddComp(dtDistributorTComp);
        int i1 = distributorService.insertDistributor(dtDistributor);

        if (i + i1 > 1) {
            return new AjaxResult(1, "申请成功");
        }

        return new AjaxResult(0, "申请出错");

    }

    @Transactional
    public AjaxResult addDistributorToComp(DtDistributorTComp dtDistributorTComp, DtDistributor dtDistributor, OpUsers opUsers, DtDistributorRole distributorRole) {
        try {
            boolean a = opUsersService.insertUser(opUsers) > 0;
            int i = distributorToCompMapper.addDistribtorAddComp(dtDistributorTComp);
            int i1 = distributorService.insertDistributor(dtDistributor);
            distributorRoleService.insert(distributorRole);

            //厂家与用户建立关系
            DtOpUserToDistributor opUserToDistributor = new DtOpUserToDistributor();
            opUserToDistributor.setUdistributorid(dtDistributor.getUdistributorid());
            opUserToDistributor.setUopuserid(opUsers.getUopuserid());
            opUserToDistributor.setUcreatedate(System.currentTimeMillis()/1000+"");
            opUserToDistributor.setUroleid(distributorRole.getUroleid());
            opUserToDistributor.setUstatus('1');
            opUserToDistributorMapper.insert(opUserToDistributor);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0, "申请出错");

        }
        return new AjaxResult(1, "申请成功");
    }
}
