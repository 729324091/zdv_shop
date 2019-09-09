package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.DvDeviceRepair;

import java.util.List;

public interface DvDeviceRepairMapper extends MyMapper<DvDeviceRepair> {


    List<DvDeviceRepair> deviceRepairList(DvDeviceRepair deviceRepair);

    int  addDeviceRepair(DvDeviceRepair deviceRepair);

    int  updateDeviceRepair(DvDeviceRepair deviceRepair);

    int  delDeviceRepairById(String udeviceRepairid);

}
