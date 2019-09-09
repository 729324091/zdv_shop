package com.zdv.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zdv.shop.common.Constant;
import com.zdv.shop.model.UtUsers;
import com.zdv.shop.model.po.MessageAndSendNameVo;
import com.zdv.shop.service.MessageService;

@Controller
@RequestMapping("/h5/message/")
public class MessageController extends BaseController {
	@Autowired
	private MessageService message;
	@RequestMapping("/")
	//获取用户id
	public String getMessage(Model model,HttpServletRequest request) {
		//获取登陆用户的id
		String isnull = "";
		UtUsers users = (UtUsers)getRequest().getSession().getAttribute(Constant.SESSION_H5USER);
		if(users!=null) {
			String uuserid = users.getUuserid();
			List<MessageAndSendNameVo> messageByuserId = message.getMessageByuserId(uuserid);
			if(messageByuserId.size()>0) {
				isnull="ture";
			}
			model.addAttribute("isnull", isnull);
			model.addAttribute("messages", messageByuserId);
			//去消息页面
			return "templates/h5/msg_list";
		}else {
			//去登陆页面
			return "redirect:/h5/user/login";
		}
	}
	/**
	 * 设置消息为已读
	 * @param messageid
	 * @return
	 */
	@RequestMapping("readmessage")
	@ResponseBody
	public String readmessage(String messageid) {
		//设置消息为已读
		boolean flag = message.readmessagemessageid(messageid);
		if(flag) {
			return "已读";
		}else {
			return "未读";
		}
	}
	/**
	 * 获取未读消息
	 * @param userid
	 * @return
	 */
	@RequestMapping("unreadmessage")
	@ResponseBody
	public String getUnreadMessageByuserId(String userid) {
		return message.getUnreadMessageByuserId(userid)+"";
	}
}
