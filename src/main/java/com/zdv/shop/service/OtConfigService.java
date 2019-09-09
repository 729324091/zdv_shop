package com.zdv.shop.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zdv.shop.common.Constant;
import com.zdv.shop.mapper.OtConfigMapper;
import com.zdv.shop.model.OtCompToProduct;
import com.zdv.shop.model.OtConfig;

@Service
public class OtConfigService extends AbstratService<OtConfig> {
	@Autowired
	private OtConfigMapper configMapper;


	public List<OtConfig> getConfigByType(Integer type,String ucustomerid){
		List<OtConfig> configByType = configMapper.getConfigByType(type + "", ucustomerid);

	/*	for (OtConfig otConfig : configByType) {
			otConfig.setUhomepic(otConfig.getUhomepic());

		}
*/
		return configByType;
	}

	public List<OtConfig> getConfigByType(Integer type, String ucustomerid, String ucompid) {
		List<OtConfig> configByType = configMapper.getConfigByType(type + "", ucustomerid,ucompid);

		return configByType;
	}
}
