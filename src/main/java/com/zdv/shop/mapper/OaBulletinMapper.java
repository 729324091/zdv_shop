package com.zdv.shop.mapper;

import java.util.List;

import com.zdv.shop.model.OaBulletin;

public interface OaBulletinMapper {

	public List<OaBulletin> queryList(OaBulletin oaBulletin);	
	public void insertOaBulletin(OaBulletin oaBulletin);
	public void deleteById(OaBulletin oaBulletin);
	public void updateByIds(OaBulletin oaBulletin);
}
