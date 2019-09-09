package com.zdv.shop.service;

import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.mapper.UtUsersMapper;
import com.zdv.shop.model.*;
import com.zdv.shop.weixin.template.TemplateSendUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Service
@Transactional
public class UtUserService extends AbstratService<UtUsers> {


    @Autowired
    private UtUsersMapper usersMapper;
    @Autowired
	private UtThirdloginService thirdloginService;
	@Autowired
	private UtCompMemberUserService compMemberUserService;
	@Autowired
	private UtFinancelogService financelogService;
	@Autowired
	private CtWxpayConfigService wxpayConfigService;
	@Autowired
	private CtCompService compService;

	@Value("${ucompid}")
	private String ucompid;

	@Value("${ucustomerid}")
	private String ucustomerid;

    /**
     * h5用户登录
     * @author LBY
     * @data 2019年2月27日
     * @param users
     * @return
     */
    public UtUsers login(UtUsers users) {
    	if (users != null && !StringUtils.isEmpty(users.getUloginname())
    			&& !StringUtils.isEmpty(users.getUpassword())) {
    		try {
    			return usersMapper.queryUserOnCompany(users);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return null;
    }
    
    /**
     * h5用户注册
     * @author LBY
     * @data 2019年2月27日
     * @param users
     * @return
     */
    public UtUsers register(UtUsers users) {
    	if (usersMapper.addUser(users) > 0)
    		return usersMapper.selectByPrimaryKey(users.getUuserid());
    	return null;
    }
    
    public int selectCountByUserName(String uloginname) {
    	UtUsers arg = new UtUsers();
    	arg.setUloginname(uloginname);
    	return usersMapper.selectCount(arg);
    }
    
    public int selectCountByUmobile(String umobile) {
    	UtUsers arg = new UtUsers();
    	arg.setUmobile(umobile);
    	return usersMapper.selectCount(arg);
    }
    
    public String  queryUnameByUorderitemid(@Param("uorderitemid")String uorderitemid) {
		return usersMapper.queryUnameByUorderitemid(uorderitemid);
    	
    }

	public int updateByUuserid(UtUsers utUsers) {
		return usersMapper.updateUser(utUsers);

	}

	public int addUser(UtUsers users, UtThirdlogin utThirdlogin, UtCompMemberUser utCompMemberUser) {
		int i = usersMapper.addUser(users);
		int insert = thirdloginService.insert(utThirdlogin);
		int c = compMemberUserService.addCompMemberUser(utCompMemberUser);

		return i + insert;
	}

	public UtUsers selectByUloginame(String uloginname) {
		return usersMapper.selectByUloginame(uloginname);
	}

	public AjaxResult zhuanzeng(UtUsers utUsers, UtUsers toUser, UtFinancelog utFinancelog, UtFinancelog toFinancelog, Double cash) {

		boolean a = usersMapper.updateUbalance(utUsers) > 0;

		boolean b = usersMapper.updateUbalance(toUser)>0;
		if (a & b) {
			boolean c = financelogService.saveFinaceLog(utFinancelog) > 0;
			if (c) {
				boolean d = financelogService.saveFinaceLog(toFinancelog) > 0;

				if (d) {
					UtThirdlogin thirdlogin = thirdloginService.selectInfoByUserid(toUser.getUuserid(), "0");
					if (thirdlogin != null) {
						CtWxpayConfig ctWxpayConfig = new CtWxpayConfig();
						ctWxpayConfig.setUcustomerid(ucustomerid);
						//0微信 1支付宝
						ctWxpayConfig.setUtypes("0");
						List<CtWxpayConfig> configs = wxpayConfigService.queryList(ctWxpayConfig);
						String appid = "";
						String appsecret = "";
						CtComp ctComp = compService.queryByID(ucompid);

						if (configs.size() > 0) {
							appid = configs.get(0).getAppid();
							appsecret = configs.get(0).getAppsecret();
							//标题
							String title = "零钱转账";
							//门店
							String shop = ctComp.getUcompname();
							//服务
							String service = "转赠";
							//时间
							String time = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
							//数据 (详细内容)
							String data = "您收到了来自用户ID:" + utUsers.getUusercode() + "名称:" + utUsers.getUname() + "转账的零钱:" + cash + "元";
							String templateid = "MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ";

							TemplateSendUtil.send_template_message(appid, appsecret, thirdlogin.getOpenid(), templateid, title, shop, service, time, data);
						}
					}


				}

			}

		}

		return new AjaxResult(1, "操作成功,请耐心等待");

	}


	public void tuisong() {



	}












	public int updateUbalance(UtUsers users) {
		return usersMapper.updateUbalance(users);
	}

    public UtUsers getAreaManager(UtUsers users) {
		String upuserid = users.getUpuserid();
		//如果存在父
		if (StringUtils.isNotEmpty(upuserid)) {
			UtUsers parent = usersMapper.selectUserById(upuserid);
			if (parent != null) {
				//如果等级为经理
				if (parent.getUvip().equals("2")) {
					return parent;
				}else{
					return getAreaManager(parent);
				}
			}
		}
		return null;
	}
	public UtUsers getShoper(UtUsers users) {
		String upuserid = users.getUpuserid();
		//如果存在父
		if (StringUtils.isNotEmpty(upuserid)) {
			UtUsers parent = usersMapper.selectUserById(upuserid);
			if (parent != null) {
				//如果等级为经理
				if (parent.getUvip().equals("4")) {
					return parent;
				}else{
					return getShoper(parent);
				}
			}
		}
		return null;
	}

    public UtUsers getBranch(UtUsers users) {
		String upuserid = users.getUpuserid();
		//如果存在父
		if (StringUtils.isNotEmpty(upuserid)) {
			UtUsers parent = usersMapper.selectUserById(upuserid);
			if (parent != null) {
				//如果等级为分公司
				if (parent.getUvip().equals("3")) {
					return parent;
				}else{
					return getBranch(parent);
				}
			}
		}
		return null;
    }


	/**
	 * 查询手机号是否与该商户存在绑定用户
	 * @param map
	 * @return
	 */
	public int queryCountByUmobileOnCompany(String umobile, String ucustomerid) {
		return usersMapper.queryCountByUmobileOnCompany(umobile,ucustomerid);
	}

	public String getSuperiorUuseridByUuserid(String uuserid) {
		return usersMapper.getSuperiorUuseridByUuserid(uuserid);
	}
}
