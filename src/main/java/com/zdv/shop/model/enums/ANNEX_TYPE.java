package com.zdv.shop.model.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 附件类型枚举
 * - 关联com.kx.exam.model.OtAnnex[ot_annex]
 * @author LBY
 * @date: 2018年12月6日
 */
public enum ANNEX_TYPE {
	
	PICTURE('0', "图片"),
	VIDEO('1', "视频"),
	WORD('2', "word"),
	XLS('3', "xls"),
	TEXT('4', "text");
	
	private final Character key;
	private final String value;
	ANNEX_TYPE(Character key, String value) {
		this.key = key;
		this.value = value;
	}
	public Character getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public static Map<Character, String> getMap() {
		Map<Character, String> map = new LinkedHashMap<Character, String>();
		for(ANNEX_TYPE item : ANNEX_TYPE.values())
			map.put(item.getKey(), item.getValue());
		return map;
	}
}
