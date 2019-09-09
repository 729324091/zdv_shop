package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.mapper.DvDeviceRepairMapper;
import com.zdv.shop.model.DvDevice;
import com.zdv.shop.model.DvDeviceRepair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DvDeviceRepairService extends AbstratService<DvDeviceRepair> {

    @Autowired
    private DvDeviceRepairMapper deviceRepairMapper;

    public PageAjax<DvDeviceRepair> queryDeviceRepairPage(PageAjax<DvDeviceRepair> page, DvDeviceRepair dvDevice) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<DvDeviceRepair> list = deviceRepairMapper.deviceRepairList(dvDevice);

        return new PageAjax<DvDeviceRepair>(list);

    }



}
