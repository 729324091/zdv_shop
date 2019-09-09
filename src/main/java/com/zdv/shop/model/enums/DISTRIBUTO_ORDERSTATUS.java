package com.zdv.shop.model.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 经销商下单状态
 * */
public enum DISTRIBUTO_ORDERSTATUS {

	WAITING_FOR_SHIPMENT("00","等待出货"),
	WAITING_FOR_DELIVERY("01","等待提货"),
	PICK_UP("02","已提货"),
	CONFIRM_RECEIPT("03","确认签收");
	
	private final String key;
	private final String value;
	DISTRIBUTO_ORDERSTATUS(String key, String value) {
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
