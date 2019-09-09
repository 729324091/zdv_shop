package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtCompMemberUser;
import com.zdv.shop.model.vo.TeamUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UtCompMemberUserMapper extends MyMapper<UtCompMemberUser> {
	List<UtCompMemberUser> selectnoqrcodelist();
	int updateCompMemberUser(@Param("ucompid") String ucompid, @Param("uuserid") String uuserid, @Param("qrcodepath") String qrcodepath);
	int delCompMemberUser(@Param("ucompid") String ucompid, @Param("uuserid") String uuserid);

	int addCompMemberUser(UtCompMemberUser utCompMemberUser);


    UtCompMemberUser getByUuserid(String uuserid);

    List<TeamUserVo> queryTeamList(UtCompMemberUser compMemberUser);

}
