package com.zdv.shop.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.model.OtNotice;
import com.zdv.shop.service.OtAnnexService;
import com.zdv.shop.service.OtNoticeService;

/**
 * 通知公告信息Controller
 * @author LBY
 * @date: 2018年12月7日
 */
@Controller
@RequestMapping("/otNotice/")
public class OtNoticeController extends BaseController {

	@Autowired
	OtNoticeService service;
	
	@Autowired
	OtAnnexService otAnnexService;
	
	@Autowired
	HttpSession session;

	@PostMapping("addOrAlter")
	@ResponseBody
	public AjaxResult add(OtNotice notice, MultipartFile homepic) throws IOException {
		return service.addOrAlter(notice, homepic) ? 
				AppUtil.returnObj("操作成功") : returnFailed(2,"操作失败");
	}
	
	@RequestMapping("del/{unoticeid}")
	public AjaxResult del(String unoticeid) {
		return service.deleteByID(unoticeid);
	}
	
	@RequestMapping("queryAll")
	@ResponseBody
	public AjaxResult queryAll() {
		return new AjaxResult(service.queryAll());
	}
	
	@RequestMapping("queryPage")
	@ResponseBody
	public PageAjax<OtNotice> queryPage(PageAjax<OtNotice> page, OtNotice otNotice) {
		return service.queryPage(page, otNotice);
	}
}
