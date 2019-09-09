package com.zdv.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdv.shop.mapper.UtTakeAddressMapper;
import com.zdv.shop.model.UtTakeAddress;

/**
 * @author LBY
 * @data 2019年2月26日 
 */
@Service
@Transactional
public class UtTakeAddressService extends AbstratService<UtTakeAddress> {

	@Autowired
	private UtTakeAddressMapper mapper;
	
	/**
	 * @author LBY
	 * @data 2019年2月27日
	 * @param uuserid
	 * @return
	 */
	public List<UtTakeAddress> queryByUserid(String uuserid) {
		UtTakeAddress address = new UtTakeAddress();
		address.setUuserid(uuserid);
		return mapper.select(address);
	}
	
	/**
	 * 修改默认收货地址
	 * @author LBY
	 * @data 2019年2月27日
	 * @param uuserid
	 * @param utakeaddressid
	 * @return
	 * @throws Exception
	 */
	public boolean updateDefaultAddress(String uuserid, String utakeaddressid) throws Exception {
		UtTakeAddress notDefault = new UtTakeAddress();
		notDefault.setUuserid(uuserid);
		notDefault.setUdefault(Boolean.FALSE);
		if (mapper.updateDefault(notDefault) > 0) {	// 将所有地址取消默认
			UtTakeAddress defaultAddress = new UtTakeAddress();
			defaultAddress.setUuserid(uuserid);
			defaultAddress.setUtakeaddressid(utakeaddressid);
			defaultAddress.setUdefault(Boolean.TRUE);
			if (mapper.updateDefault(defaultAddress) > 0) {	// 设置默认
				return true;
			} else {
				throw new Exception("修改默认地址失败");
			}
		}
		return false;
	}
}
