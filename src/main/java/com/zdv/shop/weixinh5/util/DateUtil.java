package com.zdv.shop.weixinh5.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String formatDate(String format,Long timestamp) {
		Date d = new Date(timestamp);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}
}
