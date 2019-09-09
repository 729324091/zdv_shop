package com.zdv.shop.mapper;

import com.zdv.shop.model.CtOpUserToComp;
import com.zdv.shop.model.DtOpUserToDistributor;
import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.PtOperation;

import java.util.List;

public interface PtOperationMapper extends MyMapper<PtOperation> {
    List<PtOperation> queryAllOpers();

    //通过opcode获得权限对象
    List<PtOperation> queryOperation(@Param("uopcode")String uopcode, @Param("uophref") String uophref);

    List<PtOperation> queryOperByUroleid(@Param("uroleid")String uroleid, @Param("uophref") String uophref);
    List<PtOperation> queryOperationByCodeAndType(@Param("uopcode")String uopcode, @Param("uoptype") String uoptype);

    PtOperation selectOperationById(String uopid);

    List<PtOperation> queryOpers(String uoptype);


    //OpUserToComp查询用户权限
    List<PtOperation> queryByUTC(CtOpUserToComp opUserToComp);
    //OpUserToDistributor查询用户权限
    List<PtOperation> queryByUTD(DtOpUserToDistributor opUserToDistributor);

    List<PtOperation> queryByDTUroleid(String roleid);
    List<PtOperation> queryByCTUroleid(String uroleid);
   /* int deleteByPrimaryKey(Integer uopid);

    int insert(PtOperation record);

    int insertSelective(PtOperation record);

    PtOperation selectByPrimaryKey(Integer uopid);

    int updateByPrimaryKeySelective(PtOperation record);

    int updateByPrimaryKey(PtOperation record);*/





}