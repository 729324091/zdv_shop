package com.zdv.shop.mapper;

import java.util.List;

import com.zdv.shop.model.OaSmsContent;

public interface OaSmsContentMapper {

	public List<OaSmsContent> queryList(OaSmsContent oaSmsContent);	
	public void insertOaSmsContent(OaSmsContent oaSmsContent);
	public void deleteById(OaSmsContent oaSmsContent);
	public void updateByIds(OaSmsContent oaSmsContent);
}
