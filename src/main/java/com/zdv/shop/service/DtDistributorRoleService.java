package com.zdv.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.mapper.DtDistributorRoleMapper;
import com.zdv.shop.mapper.DtRoleOperationMapper;
import com.zdv.shop.mapper.PtOperationMapper;
import com.zdv.shop.model.DtDistributorRole;
import com.zdv.shop.model.DtRoleOperation;
import com.zdv.shop.model.PtOperation;

/**
 * 经销商（供应商）角色信息表Service
 * @author LBY
 * @date: 2018年12月14日
 */
@Service
public class DtDistributorRoleService extends AbstratService<DtDistributorRole> {

	@Autowired
	DtDistributorRoleMapper mapper;

    @Autowired
    private DtRoleOperationMapper roleOperMapper;

    @Autowired
    private PtOperationMapper operationMapper;

	/**
	 * 查询该用户管辖的所有角色列表
	 * @author LBY
	 * @date 2018年12月18日
	 * @param udistributorid
	 * @param roleid
	 * @return
	 */
	public List<DtDistributorRole> queryPossessRoles(String udistributorid, String uopuserid, String uroleid) {
		return mapper.queryPossessRoles(udistributorid, uopuserid, uroleid);
	}
    //所有子级
    public List<DtDistributorRole> childRolelist(String uroleid,String udistributorid){
        return mapper.childRolelist(uroleid, udistributorid);
    }
    public List<DtDistributorRole> rootRolelist(String udistributorid){
        return mapper.rootRolelist(udistributorid);
    }
    public List<DtDistributorRole> dotree(List<DtDistributorRole> list, String uroleid, String udistributorid, int i){
        List<DtDistributorRole> listc = childRolelist(uroleid, udistributorid);

        if(listc!=null) {
            if(listc.size()>0) {
                for(DtDistributorRole voc:listc) {
                    String s="";
                    for(int n = 0;n<i;n++)
                        s = s+"&emsp;";
                    voc.setUrolename(s+"∟"+voc.getUrolename());
                    list.add(voc);
                    dotree(list,voc.getUroleid(),udistributorid,i+1);
                }
            }
        }
        return null;
    }
    /**
     *保存角色信息
     * @param role
     * @return
     */
    @ServiceLog("保存角色")
	public AjaxResult saveRole(DtDistributorRole role) {
		String result = null;
        DtDistributorRole distributorRole = mapper.queryByRolename(role.getUdistributorid(),role.getUrolename());
		if(distributorRole!=null){
            result = "角色名已存在";
        }else{
            if(role.getUparentid().equals("null")) role.setUparentid("0");
            save(role);
        }

		return AppUtil.returnObj(result);
	}


    @ServiceLog("更新角色")
    public AjaxResult updateRole(DtDistributorRole role) {
        String result = null;
        DtDistributorRole $role = mapper.queryByRolename(role.getUdistributorid(),role.getUrolename());

        if (null != $role && !$role.getUrolename().equalsIgnoreCase(role.getUrolename())) {
            result = "角色名已存在";
        } else {
            updateByID(role);
        }
        return AppUtil.returnObj(result);
    }

    @ServiceLog("绑定权限")
    public AjaxResult bindOpersByOpcode(String roleid, String[] opcodes, String udistributorid) {
        try {


            List<DtRoleOperation> list = new ArrayList<DtRoleOperation>();
            List<DtRoleOperation> delList = new ArrayList<DtRoleOperation>();
            DtRoleOperation roleOperation = null;

            //先解除改角色所有权限
            DtRoleOperation dtRoleOperation = new DtRoleOperation();
            dtRoleOperation.setUroleid(roleid);
            dtRoleOperation.setUdistributorid(udistributorid);
            roleOperMapper.delete(dtRoleOperation);
            List<String> codelist = new ArrayList<String>();
            for (int i=0; i<opcodes.length; i++) {
                if(!codelist.contains(opcodes[i])) {
                    codelist.add(opcodes[i]);
                }
            }

            //获取每个opcode
            for(String  opcode: codelist){
                //List<PtOperation> authOperations = authOperationMapper.queryOperation(opcode, "");
                //通过opcode 获得authOperations对象集合
                List<PtOperation> authOperations = operationMapper.queryOperationByCodeAndType(opcode,null);

                //遍历获得每个authOperation

                for (PtOperation authOperation : authOperations) {
                    dtRoleOperation.setUopid(authOperation.getUopid());
                    roleOperMapper.insert(dtRoleOperation);
                }

            }

            return new AjaxResult(1, "操作成功");

        } catch (Exception e) {
            e.printStackTrace();
            return AppUtil.returnObj(null);

        }

    }

    public AjaxResult delRoleByID(String uroleid) {
         int i=mapper.delRoleByID(uroleid);
        return AppUtil.returnObj(null);
    }
}
