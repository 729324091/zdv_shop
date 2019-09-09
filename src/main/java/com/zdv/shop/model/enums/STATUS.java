package com.zdv.shop.model.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 状态枚举
 * @author LBY
 * @date: 2018年12月6日
 */
public enum STATUS {

	/**
	 * 启用
	 */
	ENABLED("01", "启用"),
	/**
	 * 不可用
	 */
	DISABLED("02", "禁用");
	
	private final String key;
	private final String value;
	STATUS(String key, String value) {
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
