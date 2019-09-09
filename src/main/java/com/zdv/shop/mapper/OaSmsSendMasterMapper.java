package com.zdv.shop.mapper;

import java.util.List;

import com.zdv.shop.model.OaSmsSendMaster;

public interface OaSmsSendMasterMapper {

	public List<OaSmsSendMaster> queryList(OaSmsSendMaster oaSmsSendMaster);	
	public void insertOaSmsSendMaster(OaSmsSendMaster oaSmsSendMaster);
	public void deleteById(OaSmsSendMaster oaSmsSendMaster);
	public void updateByIds(OaSmsSendMaster oaSmsSendMaster);
}
