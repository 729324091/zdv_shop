package com.zdv.shop.controller;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.model.UtTakeAddress;
import com.zdv.shop.model.UtUsers;
import com.zdv.shop.service.UtTakeAddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

/**
 * 收货地址Controller
 * @author LBY
 * @data 2019年2月27日 
 */
@Controller
@RequestMapping("/h5/takeAddress/")
public class H5UtTakeAddressController extends BaseController {

	@Autowired
	private UtTakeAddressService service;
	
	@RequestMapping("list")
	public String address(@SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers,
			Model model,String addresstype) {
		model.addAttribute("addressList", service.queryByUserid(utUsers.getUuserid()));
		if (StringUtils.isNotEmpty(addresstype)) {
			model.addAttribute("addresstype", addresstype);
		}
		return "templates/h5/address_list";
	}
	@RequestMapping("welist")
	public String weaddress(@SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers, Model model,String addresstype) {
		model.addAttribute("addressList", service.queryByUserid(utUsers.getUuserid()));
		HttpSession session = getRequest().getSession();
		if (StringUtils.isNotEmpty(addresstype)) {
			model.addAttribute("addresstype", addresstype);

		}
		return "templates/weui/address_list";
	}

	/**
	 * 购物车结算按钮后添加地址时type=1
	 * @param utakeaddressid
	 * @param model
	 * @param type
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(String utakeaddressid, Model model,String type) {
		if (!StringUtils.isEmpty(utakeaddressid)) {

			model.addAttribute("address", service.queryByID(utakeaddressid));
		}

		model.addAttribute("type", type);
		return "templates/h5/add_address";
	}
	@RequestMapping("weedit")
	public String weedit(String utakeaddressid, Model model,String type) {
		if (!StringUtils.isEmpty(utakeaddressid)) {

			model.addAttribute("address", service.queryByID(utakeaddressid));
		}

		model.addAttribute("type", type);
		return "templates/weui/address_edit";
	}

	/**
	 * 保存收货地址
	 * @author LBY
	 * @data 2019年2月27日
	 * @param takeAddress
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public AjaxResult save(String type,UtTakeAddress takeAddress, @SessionAttribute(Constant.SESSION_H5USER)UtUsers user) {
		int rows = 0;
		if (!StringUtils.isEmpty(takeAddress.getUtakeaddressid())) {	// 修改
			rows = service.updateByID(takeAddress);
		} else {

			takeAddress.setUtakeaddressid(nextId());

//			takeAddress.setUdefault(Boolean.FALSE);
			takeAddress.setUuserid(user.getUuserid());
			rows = service.insert(takeAddress);
		}
		//如果是从购物车流程过去

		if (type.equals("1")||takeAddress.getUdefault()) {
			try {
				service.updateDefaultAddress(user.getUuserid(), takeAddress.getUtakeaddressid());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (type.equals("1")) {
				return rows > 0 ? new AjaxResult(3,"跳转下订单页面",takeAddress.getUtakeaddressid()):returnFailed("保存收货地址失败");
			}
		}
		return rows > 0 ?
				returnSuccess("保存收货地址成功") : returnFailed("保存收货地址失败");
	}
	
	/**
	 * 删除收货地址
	 * @author LBY
	 * @data 2019年2月27日
	 * @param utakeaddressid
	 * @return
	 */
	@RequestMapping("del/{utakeaddressid}")
	public String del(@PathVariable("utakeaddressid")String utakeaddressid) {
		service.delById(utakeaddressid);
		return "forward:/h5/takeAddress/list";
	}
	@RequestMapping("wedel/{utakeaddressid}")
	public String wedel(@PathVariable("utakeaddressid")String utakeaddressid) {
		service.delById(utakeaddressid);
		return "forward:/h5/takeAddress/welist";
	}
	
	/**
	 * 设置收货地址
	 * @author LBY
	 * @data 2019年2月27日
	 * @param utakeaddressid
	 * @param user
	 * @return
	 */
	@RequestMapping("setDefault/{utakeaddressid}")
	@ResponseBody
	public AjaxResult setDefault(@PathVariable("utakeaddressid")String utakeaddressid, 
			@SessionAttribute(Constant.SESSION_H5USER)UtUsers user,String type) {
		boolean flag = false;
		try {
			flag = service.updateDefaultAddress(user.getUuserid(), utakeaddressid);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return flag ? returnSuccess() : returnFailed("设置默认收货地址失败");
	}
	/**
	 * 去收货地址页面
	 * @return
	 */
	@RequestMapping("address_list")
	public String address_list(@SessionAttribute(Constant.SESSION_H5USER)UtUsers utUsers, 
			Model model,String addresstype) {
		model.addAttribute("addressList", service.queryByUserid(utUsers.getUuserid()));
		if (StringUtils.isNotEmpty(addresstype)) {
			model.addAttribute("addresstype", addresstype);
		}
		return "templates/weui/address_list";
	}
}
