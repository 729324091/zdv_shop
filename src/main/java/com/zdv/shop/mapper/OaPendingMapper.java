package com.zdv.shop.mapper;

import java.util.List;

import com.zdv.shop.model.OaPending;

public interface OaPendingMapper {

	public List<OaPending> queryList(OaPending oaPending);	
	public void insertOaPending(OaPending oaPending);
	public void deleteById(OaPending oaPending);
	public void updateByIds(OaPending oaPending);
}
