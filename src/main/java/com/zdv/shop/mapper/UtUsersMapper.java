package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OpUsers;
import com.zdv.shop.model.UtUsers;
import com.zdv.shop.model.vo.TeamUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UtUsersMapper extends MyMapper<UtUsers> {

    /**
     * 用户列表
     * @param utUsers
     * @return
     */
    List<UtUsers> userList(UtUsers utUsers);

    /**
     * 添加用户
     * @param utUsers
     * @return
     */
    int addUser(UtUsers utUsers);

    int updateUser(UtUsers utUsers);

    int delUserById(String userid);
    
    UtUsers selectUserById(String userid);
   
    String queryUnameByUorderitemid(@Param("uorderitemid")String uorderitemid);

	List<UtUsers> getBeRecommend(@Param("uUserId")String uuserid,@Param("uvip")String uvip);

	List<UtUsers> getAreaManager(@Param("uUserId")String uuserid);

    UtUsers selectByUloginame(String uloginname);

    int updateUbalance(UtUsers utUsers);

    List<TeamUserVo> listChildren(@Param("upuserid") String upuserid);

    int insertUser(OpUsers opUsers);



    int queryCountByUmobileOnCompany(@Param("umobile") String umobile,@Param("ucustomerid") String ucustomerid);

    UtUsers queryUserOnCompany(UtUsers users);

    String getSuperiorUuseridByUuserid(String uuserid);

}
