package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtNotice;

/**
 * 通知公告信息
 * @author LBY
 * @date: 2018年12月7日
 */
public interface OtNoticeMapper extends MyMapper<OtNotice> {

	/**
	 * 修改，Uhomepic无论是否为null都会修改
	 */
	@Override
	int updateByPrimaryKey(OtNotice otNotice);
}
