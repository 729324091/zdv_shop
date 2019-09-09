package com.zdv.shop.controller;

import com.zdv.shop.model.CtProfit;
import com.zdv.shop.service.CtProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/profit/")
@ResponseBody
public class CtProfitController extends BaseController{
	@Autowired
	private CtProfitService cProfitService;
	@RequestMapping("add")
	public String add(CtProfit cp) {
		cp.setUprofitid(nextId());
		cp.setUcreatdate(new Date().getTime()+"");
		return cProfitService.inserts(cp)?"添加成功":"添加失败";
	}
	
	@RequestMapping("update")
	public String update(CtProfit cp) {
		cp.setUcreatdate(new Date().getTime()+"");
		return cProfitService.updateByCustomer(cp)?"更新成功":"更新失败";
	}
	
	@RequestMapping("select")
	public List<CtProfit> select() {
		return cProfitService.queryAll();
	}
	
	@RequestMapping("selectone")
	public CtProfit selectone(CtProfit cp) {
		return cProfitService.queryByCtProfit(cp);
	}
}
