package com.zdv.shop.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.mapper.UtCompMemberUserMapper;
import com.zdv.shop.model.UtCompMemberUser;
import com.zdv.shop.model.vo.TeamUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.mapper.CtCompMapper;
import com.zdv.shop.mapper.UtUserDevelopCompMapper;
import com.zdv.shop.mapper.UtUsersMapper;
import com.zdv.shop.model.CtComp;
import com.zdv.shop.model.UtUsers;
import com.zdv.shop.model.vo.TeamVo;

@Service
public class MyTeamService {
	@Autowired
	private UtUsersMapper userMapper;
	// @Autowired
	// private UtUserDevelopCompMapper developCompMapper;
	@Autowired
	private CtCompMapper cCompMapper;
	@Autowired
	private UtCompMemberUserService compMemberUserService;
	@Autowired
    private UtUserDevelopCompMapper userDevelopCompMapper;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<TeamVo> getALLTeam(UtUsers uuserid) {
		List<TeamVo> target = new ArrayList<TeamVo>();
		// 拿到所有举荐人
		List<UtUsers> userList = userMapper.getBeRecommend(uuserid.getUuserid(),null);
		String myvip = uuserid.getUvip();

		if (userList != null && userList.size() > 0) {
			for (UtUsers utUsers : userList) {
				TeamVo content = new TeamVo();
				content.setDate(utUsers.getUregdate());
				content.setId(utUsers.getUuserid());
				content.setName(utUsers.getUname());
				content.setLogo(utUsers.getUlogo());
				content.setPuser(uuserid.getUname());

				//如果用户是普通会员则不能看到其下的经理及vip
				if ((myvip.equals("0")||myvip.equals("1")) && (utUsers.getUvip().equals("2") || utUsers.getUvip().equals("3"))) {
					continue;
				}


				if (utUsers.getUvip().equals("0")) {
					content.setType("普通会员");

				} else if (utUsers.getUvip().equals("1")) {
					content.setType("VIP");

				}else if (utUsers.getUvip().equals("2")){
					content.setType("经理");

				} else if (utUsers.getUvip().equals("3")) {
					content.setType("分公司");

				}
				target.add(content);
			}
		}
		if (myvip.equals("2") || myvip.equals("3")) {
			// 拿到店铺
			List<CtComp> compList = cCompMapper.getComp(uuserid.getUuserid());
			if (compList != null && compList.size() > 0) {
				for (CtComp ctComp : compList) {
					TeamVo content = new TeamVo();
					content.setDate(ctComp.getUcreatedate());
					content.setId(ctComp.getUcompid());
					content.setName(ctComp.getUcompname());
					content.setLogo(ctComp.getUlogo());
					content.setPuser(uuserid.getUname());
					content.setType("店铺");
					target.add(content);
				}
			}
			/*// 拿到区域经理
			List<UtUsers> areaManagerList = userMapper.getAreaManager(uuserid.getUuserid());
			if (areaManagerList != null && areaManagerList.size() > 0) {
				for (UtUsers utUsers : areaManagerList) {
					TeamVo content = new TeamVo();
					content.setDate(utUsers.getUregdate());
					content.setId(utUsers.getUuserid());
					content.setName(utUsers.getUname());
					content.setLogo(utUsers.getUlogo());
					content.setPuser(uuserid.getUname());
					content.setType("经理");
					target.add(content);
				}
			}*/
		}


		return target;
	}

	public List<TeamVo> getTeamByType(UtUsers uuserid, Integer type) {
		List<TeamVo> target = new ArrayList<TeamVo>();
		String myvip = uuserid.getUvip();
		if (type == 0) {
			List<UtUsers> userList = userMapper.getBeRecommend(uuserid.getUuserid(), type.toString());
			if (userList != null && userList.size() > 0) {
				for (UtUsers utUsers : userList) {
					TeamVo content = new TeamVo();
					content.setDate(utUsers.getUregdate());
					content.setId(utUsers.getUuserid());
					content.setName(utUsers.getUname());
					content.setLogo(utUsers.getUlogo());
					content.setPuser(uuserid.getUname());
					content.setType("普通会员");
					target.add(content);
				}
				return target;
			}
		} else if (type == 1) {
			List<UtUsers> userList = userMapper.getBeRecommend(uuserid.getUuserid(), type.toString());
			if (userList != null && userList.size() > 0) {
				for (UtUsers utUsers : userList) {
					TeamVo content = new TeamVo();
					content.setDate(utUsers.getUregdate());
					content.setId(utUsers.getUuserid());
					content.setName(utUsers.getUname());
					content.setLogo(utUsers.getUlogo());
					content.setPuser(uuserid.getUname());
					content.setType("vip");
					target.add(content);
				}
				return target;
			}
		} else if (type == 4) {
			if (myvip.equals("0") || myvip.equals("1")) {
				return target;
			}
			// 拿到店铺
			List<CtComp> compList = cCompMapper.getComp(uuserid.getUuserid());
			if (compList != null && compList.size() > 0) {
				for (CtComp ctComp : compList) {
					TeamVo content = new TeamVo();
					content.setDate(ctComp.getUcreatedate());
					content.setId(ctComp.getUcompid());
					content.setName(ctComp.getUcompname());
					content.setLogo(ctComp.getUlogo());
					content.setPuser(uuserid.getUname());
					content.setType("店铺");
					target.add(content);
				}
				return target;
			}
		} else if (type == 2) {
			if (myvip.equals("0") || myvip.equals("1") || myvip.equals("2")) {
				return target;
			}


            List<UtUsers> userList = userMapper.getBeRecommend(uuserid.getUuserid(), type.toString());
            if (userList != null && userList.size() > 0) {
                for (UtUsers utUsers : userList) {
                    TeamVo content = new TeamVo();
                    content.setDate(utUsers.getUregdate());
                    content.setId(utUsers.getUuserid());
                    content.setName(utUsers.getUname());
                    content.setLogo(utUsers.getUlogo());
                    content.setPuser(uuserid.getUname());
                    content.setType("经理");
                    target.add(content);
                }
                return target;
            }
			/*// 拿到区域经理
			List<UtUsers> areaManagerList = userMapper.getAreaManager(uuserid.getUuserid());
			if (areaManagerList != null && areaManagerList.size() > 0) {
				for (UtUsers utUsers : areaManagerList) {
					TeamVo content = new TeamVo();
					content.setDate(utUsers.getUregdate());
					content.setId(utUsers.getUuserid());
					content.setName(utUsers.getUname());
					content.setLogo(utUsers.getUlogo());
					content.setPuser(uuserid.getUname());
					content.setType("经理");
					target.add(content);
				}
			}*/
			return target;
		} else if (type == 3) {
			if (myvip.equals("0") || myvip.equals("1") ) {
				return target;
			}
			// 所有分公司
			List<UtUsers> userList = userMapper.getBeRecommend(uuserid.getUuserid(), type.toString());
			if (userList != null && userList.size() > 0) {
				for (UtUsers utUsers : userList) {
					TeamVo content = new TeamVo();
					content.setDate(utUsers.getUregdate());
					content.setId(utUsers.getUuserid());
					content.setName(utUsers.getUname());
					content.setLogo(utUsers.getUlogo());
					content.setPuser(uuserid.getUname());
					content.setType("分公司");
					target.add(content);
				}
			} else {
				return target;
			}
		}
		return target;


	}



    public List<TeamUserVo> getALLTeam(UtUsers user, String type) {
	    //分公司或经理
        List<TeamUserVo> alllist = new ArrayList<>();
        List<TeamUserVo> showList = new ArrayList<>();
        if (user.getUvip().equals("3") ||user.getUvip().equals("2")||user.getUvip().equalsIgnoreCase("4")) {
            //获得全部子集
			listAllChildrenByUserid(user.getUuserid(), alllist, new ArrayList<>());

        }else{
            //普通用户和VIP  只能看到一层
            alllist = userMapper.listChildren(user.getUuserid());
            for (TeamUserVo listChild : alllist) {
                if (listChild.getUvip().equals("0")) {
                    listChild.setUvipname("普通会员");
                } else if (listChild.getUvip().equals("1")) {
                    listChild.setUvipname("VIP");
                }
            }
           /* //店铺
            List<CtComp> compList = cCompMapper.getComp(user.getUuserid());
            if (compList != null && compList.size() > 0) {
                for (CtComp ctComp : compList) {
                    TeamUserVo content = new TeamUserVo();
                    content.setUregdate(ctComp.getUcreatedate());
                    content.setUuserid(ctComp.getUcompid());
                    content.setUname(ctComp.getUcompname());
                    content.setUlogo(ctComp.getUlogo());
                    content.setUpname(user.getUname());
                    content.setUvipname("店铺");
                    alllist.add(content);
                }
            }*/


        }

        if (StringUtils.StringisNotEmpty(type)) {
            for (TeamUserVo teamUserVo : alllist) {
                //如果为对应等级
                if (type.equals(teamUserVo.getUvip())) {
                    showList.add(teamUserVo);
                }
            }
        }else {
            showList = alllist;
        }
       /* if (type.equals("0")) {

        } else if (type.equals("1")) {

        } else if (type.equals("2")) {

        } else if (type.equals("3")) {

        }else{

        }
*/
        return showList;

    }

    private void listAllChildrenByUserid(String uuserid, List<TeamUserVo> alllist,List<String> useridlist) {
        List<TeamUserVo> listChildren = userMapper.listChildren(uuserid);
//        List<TeamUserVo> list = userDevelopCompMapper.listChildren(uuserid);
        if (listChildren != null) {
            for (TeamUserVo listChild : listChildren) {
            	if(useridlist.contains(listChild.getUuserid())){

            		continue;
				}
                if (listChild.getUvip().equals("0")) {
                    listChild.setUvipname("普通会员");
                } else if (listChild.getUvip().equals("1")) {
                    listChild.setUvipname("VIP");
                } else if (listChild.getUvip().equals("2")) {
                    listChild.setUvipname("经理");
                } else if (listChild.getUvip().equals("3")) {
                    listChild.setUvipname("分公司");
                }else if (listChild.getUvip().equals("4")) {
                    listChild.setUvipname("店主");
                }
				useridlist.add(listChild.getUuserid());
                listAllChildrenByUserid(listChild.getUuserid(), alllist,useridlist);
                alllist.add(listChild);
            }
//            alllist.addAll(listChildren);

        }

       /* if (list != null) {
			for (TeamUserVo teamUserVo : list) {
				//实际为零售店的id,根据零售店id获取其下的用户
				String ucompid = teamUserVo.getUuserid();
				UtCompMemberUser compMemberUser = new UtCompMemberUser();
				compMemberUser.setUcompid(ucompid);
				List<TeamUserVo> compChildren = compMemberUserService.queryTeamList(compMemberUser);
				if (compChildren != null) {
					for (TeamUserVo compChild : compChildren) {
						if (compChild.getUvip().equals("0")) {
							compChild.setUvipname("普通会员");
						} else if (compChild.getUvip().equals("1")) {
							compChild.setUvipname("VIP");
						} else if (compChild.getUvip().equals("2")) {
							compChild.setUvipname("经理");
						} else if (compChild.getUvip().equals("3")) {
							compChild.setUvipname("分公司");
						}
						listAllChildrenByUserid(compChild.getUuserid(), alllist);
						alllist.add(compChild);
					}
				}
			}

            alllist.addAll(list);
        }*/



    }
}
