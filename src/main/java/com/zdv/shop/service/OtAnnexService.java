package com.zdv.shop.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.common.Constant;
import com.zdv.shop.mapper.OtAnnexMapper;
import com.zdv.shop.model.OtAnnex;

/**
 * 附件图片视频信息
 * @author LBY
 * @date: 2018年12月7日
 */
@Service
public class OtAnnexService extends AbstratService<OtAnnex> {

	@Autowired
	OtAnnexMapper mapper;
	
	@Autowired
	HttpSession session;
	
	/**
	 * 获取附件上传路径
	 * @author LBY
	 * @date 2018年12月7日
	 * @return
	 */
	public String getUploadPath() {
		return session.getServletContext().getRealPath(Constant.ANNEX_UPLOAD_PATH);
	}
}