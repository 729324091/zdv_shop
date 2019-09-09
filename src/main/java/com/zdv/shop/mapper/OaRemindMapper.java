package com.zdv.shop.mapper;

import java.util.List;

import com.zdv.shop.model.OaRemind;

public interface OaRemindMapper {

	
	public List<OaRemind> queryList(OaRemind oaRemind);	
	public void insertOaRemind(OaRemind oaRemind);
	public void deleteById(OaRemind oaRemind);
	public void updateByIds(OaRemind oaRemind);
}
