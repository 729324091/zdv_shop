package com.zdv.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.mapper.CtOpUserToCompMapper;
import com.zdv.shop.mapper.OpUsersMapper;
import com.zdv.shop.model.CtOpUserToComp;
import com.zdv.shop.model.DtOpUserToDistributor;
import com.zdv.shop.model.OpUsers;
import com.zdv.shop.model.vo.AppQueryOpuserVO;

@Service
public class CtOpUserToCompService extends AbstratService<CtOpUserToComp> {
	@Autowired
	private CtOpUserToCompMapper mapper;
	@Autowired
	private OpUsersMapper usersMapper;
	@ServiceLog("管理员")
	public int updateOpusercomp(CtOpUserToComp opUserToComp) {
		return mapper.updateOpusercomp(opUserToComp);
	}
    
    /**
     * APP查询经销商管理工作人员
     * @author LBY
     * @data 2019年1月15日
     * @param ucompid
     * @param uroleid
     * @return
     */
	@ServiceLog("APP查询经销商管理工作人员")
	public List<Map<String, Object>> appQueryOpuser(AppQueryOpuserVO argVo){
		return mapper.appQueryOpuser(argVo);
	}
	
	
	//删除销售商与工作人员关系
	public boolean del(CtOpUserToComp opUserToComp) {
		return mapper.delete(opUserToComp) > 0;
	}
	

	@ServiceLog("绑定管理员")
	public AjaxResult bindUser(OpUsers users, CtOpUserToComp opUserToComp) {
		String result = null;
		try {
			OpUsers opUsers = usersMapper.queryByUsername(users.getUloginname());
			if (opUsers != null || opUsers.getUpassword().equalsIgnoreCase(users.getUpassword())) {
				opUserToComp.setUopuserid(opUsers.getUopuserid());
				opUserToComp.setUstatus('0');
				boolean b = mapper.insert(opUserToComp) > 0;
				if (b) {
					result = "绑定成功";
					return new AjaxResult(1, result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult();

	}
	@ServiceLog("获得零售店下所有管理员ID")
	public List<Map<String,Object>> opusercomplistByCompid(String ucompid){
		return mapper.opusercomplistByCompid(ucompid);
	}
}
