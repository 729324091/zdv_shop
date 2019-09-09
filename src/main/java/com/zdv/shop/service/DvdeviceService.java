package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.mapper.DvDeviceMapper;
import com.zdv.shop.model.DvDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DvdeviceService extends AbstratService<DvDevice> {

    @Autowired
    private DvDeviceMapper deviceMapper;

    public PageAjax<DvDevice> queryDevicePage(PageAjax<DvDevice> page, DvDevice dvDevice) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<DvDevice> list = deviceMapper.deviceList(dvDevice);

        return new PageAjax<DvDevice>(list);

    }
}
