package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.DvDevice;

import java.util.List;

public interface DvDeviceMapper extends MyMapper<DvDevice> {

    List<DvDevice> deviceList(DvDevice dvDevice);

    int  addDevice(DvDevice dvDevice);

    int  updateDevice(DvDevice dvDevice);

    int  delDeviceById(String udeviceid);
}
