package com.zdv.shop.service;

import com.zdv.shop.mapper.UtCompMemberUserMapper;
import com.zdv.shop.model.UtCompMemberUser;
import com.zdv.shop.model.vo.TeamUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtCompMemberUserService extends AbstratService<UtCompMemberUser> {
	@Autowired
	private UtCompMemberUserMapper compMemberUserMapper;

	public List<UtCompMemberUser> selectnoqrcodelist() {
		return compMemberUserMapper.selectnoqrcodelist();
	}

	public int updateCompMemberUser(String ucompid, String uuserid, String qrcodepath) {
		return compMemberUserMapper.updateCompMemberUser(ucompid, uuserid, qrcodepath);
	}

	public int delCompMemberUser(String ucompid, String uuserid) {
		return compMemberUserMapper.delCompMemberUser(ucompid, uuserid);
	}

	public int addCompMemberUser(UtCompMemberUser utCompMemberUser) {
		return compMemberUserMapper.addCompMemberUser(utCompMemberUser);

	}

	public UtCompMemberUser getByUuserid(String uuserid) {
		return compMemberUserMapper.getByUuserid(uuserid);
	}

    public List<TeamUserVo> queryTeamList(UtCompMemberUser compMemberUser) {
		return compMemberUserMapper.queryTeamList(compMemberUser);
    }
}
