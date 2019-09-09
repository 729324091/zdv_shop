package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.mapper.UtUserDistributionMapper;
import com.zdv.shop.model.UtUserDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtUserDistributionService extends AbstratService<UtUserDistribution> {

    @Autowired
    private UtUserDistributionMapper userDistributionMapper;

    @Autowired
    private LogService logService;

    public PageAjax<UtUserDistribution> queryDistributionPage(PageAjax<UtUserDistribution> page, UtUserDistribution userDistribution) {

        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<UtUserDistribution> list = userDistributionMapper.queryDustributionList(userDistribution);


        return new PageAjax<>(list);



    }

    public UtUserDistribution selectUserDistributionLastLevel(String udistributionid, String ucustomerid) {

        return userDistributionMapper.selectUserDistributionLastLevel(udistributionid,ucustomerid);

    }

    public Double queryProfitSumByUcustomerid(String ucustomerid, String udistributionid) {

        return userDistributionMapper.queryProfitSumByUcustomerid(ucustomerid,udistributionid);

    }

    public AjaxResult addUserDistribution(UtUserDistribution userDistribution) {
        if (StringUtils.StringisNotEmpty(userDistribution.getUparentid())) {
            UtUserDistribution distribution = userDistributionMapper.selectByPrimaryKey(userDistribution.getUparentid());
            if (StringUtils.objectIsNull(distribution)||StringUtils.objectIsNull(distribution.getUlevel())) {
                return new AjaxResult(0, "上级设置错误");
            }
            //如果存在父级
            userDistribution.setUlevel(logService.sp_level(1, distribution.getUlevel(), "", "ut_user_distribution", ""));

        }else{
            //不存在父级
            userDistribution.setUlevel(logService.sp_level(0, "", "", "ut_user_distribution", ""));
        }
        boolean b = userDistributionMapper.addUserDistribution(userDistribution) > 0;
        return b?  new AjaxResult(1): new AjaxResult(0);
    }

    public AjaxResult updateUserDistribution(UtUserDistribution userDistribution) {
        boolean b = userDistributionMapper.updateUserDistributionByIds(userDistribution) > 0;
        return b?  new AjaxResult(1): new AjaxResult(0);

    }

    /**
     * 删除该等级及子等级
     * @param udistributionid
     * @param ucustomerid
     * @return
     */
    public AjaxResult delUserDistributions(String udistributionid, String ucustomerid) {
        UtUserDistribution utUserDistribution = userDistributionMapper.selectByPrimaryKey(udistributionid);


        boolean b = userDistributionMapper.delUserDistributions(utUserDistribution.getUlevel(), ucustomerid) > 0;
        return b? new AjaxResult(1): new AjaxResult(0);


    }

    public UtUserDistribution queryUserDistributionByUuserid(String uuserid) {
        return userDistributionMapper.queryUserDistributionByUuserid(uuserid);
    }
}
