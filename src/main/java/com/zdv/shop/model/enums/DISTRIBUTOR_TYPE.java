package com.zdv.shop.model.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 经销商类型
 * */
public enum DISTRIBUTOR_TYPE {

	PROVINCE("01","省级"),
	CITY("02","市级"),
	COUNTY("03","县级");
	
	private final String key;
	private final String value;
	DISTRIBUTOR_TYPE(String key, String value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public static Map<String, String> getMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(STATUS item : STATUS.values())
			map.put(item.getKey(), item.getValue());
		return map;
	}
}
