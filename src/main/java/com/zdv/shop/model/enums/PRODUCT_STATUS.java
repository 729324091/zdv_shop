package com.zdv.shop.model.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 商品状态
 * - 关联 com.zdv.shop.model.OtComToProduct[ot_comp_to_product(Uflag)]
 * 		com.zdv.shop.model.OtDistributorToProduct[ot_distributor_to_product(Uflag)]
 * @author LBY
 * @date: 2018年12月6日
 */
public enum PRODUCT_STATUS {

	SHELVE("01", "上架"),
	UNSHELVE("02", "下架");
	
	private final String key;
	private final String value;
	PRODUCT_STATUS(String key, String value) {
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
