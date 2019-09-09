package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtTakeAddress;

/**
 * 用户收货地址mapper
 * @author LBY
 * @data 2019年2月26日 
 */
public interface UtTakeAddressMapper extends MyMapper<UtTakeAddress> {

	/**
	 * 修改用户默认收货地址
	 * @author LBY
	 * @data 2019年2月27日
	 * @param address
	 * @return
	 */
	int updateDefault(UtTakeAddress address);
	
}
