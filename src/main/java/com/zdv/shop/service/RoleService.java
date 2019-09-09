package com.zdv.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.mapper.AuthOperationMapper;
import com.zdv.shop.mapper.AuthRoleMapper;
import com.zdv.shop.mapper.AuthRoleOperationMapper;
import com.zdv.shop.model.AuthOperation;
import com.zdv.shop.model.AuthRole;
import com.zdv.shop.model.AuthRoleOperation;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class RoleService extends AbstratService<AuthRole> {

	@Autowired
	private AuthRoleMapper roleMapper;
	@Autowired
	private AuthRoleOperationMapper roleOperMapper;
	@Autowired
	private AuthOperationMapper authOperationMapper;
	public AuthRole queryRoleById(String roleid){
		return roleMapper.queryRoleById(roleid);
	}

	@ServiceLog("新增角色")
	public AjaxResult saveRole(AuthRole role) {
		String result = null;
		AuthRole $role = new AuthRole();
		if(role.getCustomerid()==null||role.getCustomerid().equals(""))
			$role = roleMapper.queryByRolename(role.getRolename());
		else
			$role = roleMapper.queryByRolename1(role.getRolename(),role.getCustomerid());
		if (null == $role) {
			save(role);
			roleMapper.insertAuth_role_operation(role.getRoleid(), "0");
		} else {
			result = "角色名已存在";
		}
		return AppUtil.returnObj(result);
	}

	@ServiceLog("更新角色")
	public AjaxResult updateRole(AuthRole role) {
		String result = null;
		AuthRole $role = new AuthRole();
		if(role.getCustomerid()==null||role.getCustomerid().equals(""))
			$role = roleMapper.queryByRolename(role.getRolename());
		else
			$role = roleMapper.queryByRolename1(role.getRolename(),role.getCustomerid());
		if (null != $role && $role.getRoleid() != role.getRoleid()) {
			result = "角色名已存在";
		} else {
			updateByID(role);
		}
		return AppUtil.returnObj(result);
	}

	public List<AuthRole> queryNotAdmin(String customerid) {
		Example example = new Example(AuthRole.class);
		Criteria criteria = example.createCriteria();
		criteria.andNotEqualTo("rolename", "admin");
		criteria.andEqualTo("customerid",customerid);
		return roleMapper.selectByExample(example);
	}

	@ServiceLog("绑定角色权限")
	public AjaxResult bindOpers(String roleid, int[] opids) {
		List<AuthRoleOperation> list = new ArrayList<AuthRoleOperation>();
		AuthRoleOperation roleOperation = null;
		for(int opid: opids){
			roleOperation = new AuthRoleOperation();
			roleOperation.setRoleid(roleid);
			roleOperation.setOpid(opid);
			list.add(roleOperation);
		}
		//通用mapper的批量插入竟然不行
//		roleOperMapper.insertList(list);
		roleOperMapper.batchInsert(list);
		return AppUtil.returnObj(null);
	}

	@ServiceLog("解除角色权限")
	public AjaxResult unbindOpers(String roleid, int[] opids){
		List<AuthRoleOperation> list = new ArrayList<AuthRoleOperation>();
		AuthRoleOperation roleOperation = null;
		for(int opid: opids){
			roleOperation = new AuthRoleOperation();
			roleOperation.setRoleid(roleid);
			roleOperation.setOpid(opid);
			list.add(roleOperation);
		}
		roleOperMapper.delRoleOpers(list);
		return AppUtil.returnObj(null);
	}
	//所有父级
	public List<AuthRole> rootRolelist(String customerid){
			return roleMapper.rootRolelist(customerid);
		}
		//所有子级
	public List<AuthRole> childRolelist(String roleid,String customerid){
			return roleMapper.childRolelist(roleid, customerid);
	}
	public List<AuthRole> dotree(List<AuthRole> list,String roleid,String customerid,int i){
		List<AuthRole> listc = childRolelist(roleid, customerid);
		
		if(listc!=null) {
			if(listc.size()>0) {
				for(AuthRole voc:listc) {
					String s="";
					for(int n = 0;n<i;n++)
						s = s+"&emsp;";
					voc.setCname(s+"∟"+voc.getCname());
					list.add(voc);
					dotree(list,voc.getRoleid(),customerid,i+1);
				}
			}
		}
		return null;
	}
	
	 @ServiceLog("opcode绑定角色权限")
	    public AjaxResult bindOpersByOpcode(String roleid, String[] opcodes) {
		 List<AuthRoleOperation> list = new ArrayList<AuthRoleOperation>();
			List<AuthRoleOperation> delList = new ArrayList<AuthRoleOperation>();
	        AuthRoleOperation roleOperation = null;
	        //获取每个opcode
	        for(String  opcode: opcodes){
	            //通过opcode 获得authOperations对象集合
	            List<AuthOperation> authOperations = authOperationMapper.queryOperation(opcode, "");
	            //遍历获得每个authOperation
	            for (AuthOperation authOperation : authOperations) {
	                roleOperation = new AuthRoleOperation();
	                //建立权限id与角色id的关联
	                Integer opid = authOperation.getOpid();
	                roleOperation.setRoleid(roleid);
	                roleOperation.setOpid(opid);
	                list.add(roleOperation);
	            }
	        }
			//获取所有权限然后解除绑定
			List<AuthOperation> delauthOperations = authOperationMapper.queryAllOpers();
			for (AuthOperation delauthOperation : delauthOperations) {
				roleOperation = new AuthRoleOperation();
				//建立权限id与角色id的关联
				Integer opid = delauthOperation.getOpid();
				roleOperation.setRoleid(roleid);
				roleOperation.setOpid(opid);
				delList.add(roleOperation);
			}
			//通用mapper的批量插入竟然不行
//			roleOperMapper.insertList(list);
			//解除绑定
			roleOperMapper.delRoleOpers(delList);
			//绑定选中的权限
	        roleOperMapper.batchInsert(list);
	        return AppUtil.returnObj(null);
	    }


}
