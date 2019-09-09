package com.zdv.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdv.shop.model.PtOperation;

/**
 * 权限管理业务层
 * @author administrator
 */
@Service
public class OpOperationService extends AbstratService<PtOperation> {
	// 扫描包
	private static final String PERMISS_PACKAGE = "com.zdv.shop.controller";
	//private static final String ADMIN = "admin";

	/**
	 * 初始化权限
	 */
	@Transactional // 开启事务
	@SuppressWarnings("unchecked")
	public void initAuthority() {
		


	}
	
}
