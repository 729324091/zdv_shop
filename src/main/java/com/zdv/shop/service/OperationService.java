/*
package com.zdv.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.MapperKey;
import com.zdv.shop.common.annotation.Authority;
import com.zdv.shop.common.utils.AnnotationUtil;
import com.zdv.shop.common.utils.JsonConvertUtil;
import com.zdv.shop.common.utils.MD5Util;
import com.zdv.shop.model.AuthOperation;
import com.zdv.shop.model.AuthRole;
import com.zdv.shop.model.Oper_user;

*/
/**
 * 权限管理业务层
 * @author administrator
 *//*

@Service
public class OperationService extends AbstratService<AuthOperation> {
	// 扫描包
	private static final String PERMISS_PACKAGE = "com.zdv.shop.controller";
	private static final String ADMIN = "admin";

	*/
/**
	 * 初始化权限
	 *//*

	@Transactional // 开启事务
	@SuppressWarnings("unchecked")
	public void initAuthority() {
		//获得所有操作功能
		List<AuthOperation> operationList = (List<AuthOperation>) dao.findForList(MapperKey.OPERATION_queryAllOpers, null);
		List<String> opIdList = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(operationList)) {
			for (AuthOperation operation : operationList) {
				opIdList.add(operation.getOpid() + "");
			}
		}

		// 要保存的列表
		List<AuthOperation> saveList = new ArrayList<AuthOperation>();
		// 要修改的列表
		List<AuthOperation> updateList = new ArrayList<AuthOperation>();

		// 同步json文件(左侧菜单栏权限)里面的权限
		List<AuthOperation> operationJsonList = JsonConvertUtil.json2List("operations.json", AuthOperation.class);
		if (operationJsonList != null) {
			for (AuthOperation operation : operationJsonList) {
				AuthOperation $operation = dao.findForObject(MapperKey.OPERATION_queryOperation, operation);
				if ($operation != null) {
					$operation.setOpname(operation.getOpname());
					$operation.setOphref(operation.getOphref());
					$operation.setOpseq(operation.getOpseq());
					$operation.setIfoper(operation.getIfoper());
					updateList.add(operation);
					// 剔除已经更新的数据
					opIdList.remove($operation.getOpid() + "");
				} else {
					$operation = new AuthOperation();
					$operation.setIfoper(operation.getIfoper());
					$operation.setOpcode(operation.getOpcode());
					$operation.setOpname(operation.getOpname());
					$operation.setOphref(operation.getOphref());
					$operation.setOpseq(operation.getOpseq());
		
					saveList.add(operation);
				}
			}
		}
		
		// 同步注解里面的权限
		Map<String, Authority> permissionMap = AnnotationUtil.getMethodAnnotaionByPackage(PERMISS_PACKAGE, Authority.class);
		Set<Map.Entry<String, Authority>> entrySet = permissionMap.entrySet();
		for (Map.Entry<String, Authority> entry : entrySet) {
			Authority permission = entry.getValue();
			String href = entry.getKey();
			String code = permission.opCode();
			String ifOper = permission.ifOper();
			if (StringUtils.isEmpty(code)) {
				continue;
			}
			AuthOperation $operation = new AuthOperation();
			//System.out.println("========================================="+new Date()+"|"+code+"||"+href);
			$operation.setIfoper(ifOper);
			$operation.setOpcode(code);
			$operation.setOphref(href);
			AuthOperation operation = dao.findForObject(MapperKey.OPERATION_queryOperation, $operation);
			if (operation != null) {
				operation.setOpname(permission.opName());
				operation.setIfoper(permission.ifOper());
				operation.setOphref(href);
				operation.setOpseq(permission.opSeq());
				updateList.add(operation);
				// 剔除已经更新的数据
				opIdList.remove(operation.getOpid() + "");
			} else {
				operation = new AuthOperation();
				operation.setOpname(permission.opName());
				operation.setIfoper(permission.ifOper());
				operation.setOphref(href);
				operation.setOpseq(permission.opSeq());
				operation.setOpcode(code);
				saveList.add(operation);
			}
		}

		// 批量保存新增的权限
		if (CollectionUtils.isNotEmpty(saveList)) {
			dao.batchSave(MapperKey.OPERATION_batchSaveOper, saveList);
		}
		// 批量更新修改的权限
		if (CollectionUtils.isNotEmpty(updateList)) {
			dao.batchUpdate(MapperKey.OPERATION_batchUpdateOper, updateList);
		}
		// 删除的权限值
		if (CollectionUtils.isNotEmpty(opIdList)) {
			dao.batchDelete(MapperKey.OPERATION_batchDelOper, opIdList);
		}

		AuthRole role = dao.findForObject(MapperKey.ROLE_queryByRolename, Constant.ROLE_ANONYMOUS);
		// 创建匿名用户组
		
		if (null == role) {
			role = new AuthRole();
			role.setRoleid(UUID.randomUUID().toString().trim().replaceAll("-", ""));
			role.setRolename(Constant.ROLE_ANONYMOUS);
			role.setCname("匿名用户组");
			dao.save(MapperKey.ROLE_addAdmin, role);
		}
		role = dao.findForObject(MapperKey.ROLE_queryByRolename, ADMIN);
		
		// 创建超级管理员
		if (null == role) {
			role = new AuthRole();
			role.setRoleid(UUID.randomUUID().toString().trim().replaceAll("-", ""));
			role.setRolename(ADMIN);
			role.setCname("超级管理员");
			dao.save(MapperKey.ROLE_addAdmin, role);
		}
		String roleid = role.getRoleid();
		Oper_user oper_user = dao.findForObject(MapperKey.USER_queryByUsername, ADMIN);
		// 创建超级用户
		if (null == oper_user) {
			oper_user = new Oper_user();
			oper_user.setUsername(ADMIN);
			try {
				String password = MD5Util.encrypt(ADMIN);
				oper_user.setPassword(password);
			} catch (Exception e) {
			}
			oper_user.setRoleid(roleid);
			dao.save(MapperKey.USER_addAdmin, oper_user);
		}
		//System.out.println("========11=======================:"+roleid);
		// 更新超级管理员权限  jock
		try {
			dao.save(MapperKey.OPERATION_bindOpers, roleid);
		} catch (Exception e) {
			//System.out.println("==begin===="+new Date());
			e.printStackTrace();
			//System.out.println("===end==="+new Date());
		}

	}
	
}
*/
