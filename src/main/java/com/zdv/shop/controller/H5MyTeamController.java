package com.zdv.shop.controller;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.model.UtUsers;
import com.zdv.shop.model.vo.TeamUserVo;
import com.zdv.shop.model.vo.TeamVo;
import com.zdv.shop.service.MyTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/h5/team/")
public class H5MyTeamController {
	@Autowired
	private MyTeamService teamService;
	@RequestMapping("info")
	public String info(@SessionAttribute(Constant.SESSION_H5USER) UtUsers user, Model model,String type) {
//		List<TeamUserVo> allTeam = new ArrayList<>();
		//如果是经理或者经销商
		List<TeamUserVo> allTeam = teamService.getALLTeam(user,type);

		int count = 0;
		if(allTeam!=null&&allTeam.size()>0) {
			count = allTeam.size();
		}
		model.addAttribute("count", count);
		model.addAttribute("teamList", allTeam);
		String typename = "全部";
		if (StringUtils.StringisNotEmpty(type)) {

			model.addAttribute("type", type);
			if(type.equals("1")) {
				typename = "vip";
			}
			if(type.equals("2")) {
				typename = "经理";
			}
			if(type.equals("3")) {
				typename = "分公司";
			}
			if (type.equals("4")) {
				typename = "店主";
			}
		}
		model.addAttribute("typename", typename);

		return "templates/weui/myteam";
	}
	@RequestMapping("type")
	public String queryByType(@SessionAttribute(Constant.SESSION_H5USER) UtUsers user, Model model,Integer type) {

		List<TeamVo> vip = teamService.getTeamByType(user,type);

		int count = 0;
		if(vip!=null&&vip.size()>0) {
			for (TeamVo teamVo : vip) {
				count++;
			}
		}
		String typename = "";
		if(type==1) {
			typename = "vip";
		}
		if(type==2) {
			typename = "店铺";
		}
		if(type==3) {
			typename = "区域经理";
		}
		model.addAttribute("count", count);
		model.addAttribute("teamList", vip);
		model.addAttribute("type", typename);
		return "templates/weui/myteam";
	}
}
