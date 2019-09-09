package com.zdv.shop.model.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum SUPER_DISTRIBUTO_ORDER_STATUS {

	PLACING_ORDER("00","已下单中"),
	READY_FOR_SHIPMENT("01","已备齐货"),
	SHIPPED("02","已发货"),
	TRANSACTION_COMPLETED("03","交易完成");
	
	private final String key;
	private final String value;
	SUPER_DISTRIBUTO_ORDER_STATUS(String key, String value) {
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
		for(PRODUCT_STATUS item : PRODUCT_STATUS.values())
			map.put(item.getKey(), item.getValue());
		return map;
	}
}
