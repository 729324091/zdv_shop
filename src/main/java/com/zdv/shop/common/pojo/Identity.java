package com.zdv.shop.common.pojo;

import java.util.List;

import com.zdv.shop.model.OpUsers;
import com.zdv.shop.model.PtOperation;

/**
 * 封装Session
 * @author administrator
 */
public class Identity {
	private String sessionId;
	private String loginIp;
	private OpUsers loginUser;
	private List<PtOperation> operationList;
	private String uuserid;

	//代理商id
	private String udistributorid;
	//销售商id
	private String ucompid;


	public String getUdistributorid() {
		return udistributorid;
	}

	public void setUdistributorid(String udistributorid) {
		this.udistributorid = udistributorid;
	}

	public String getUcompid() {
		return ucompid;
	}

	public void setUcompid(String ucompid) {
		this.ucompid = ucompid;
	}

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public OpUsers getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(OpUsers loginUser) {
		this.loginUser = loginUser;
	}
	public List<PtOperation> getOperationList() {
		return operationList;
	}
	public void setOperationList(List<PtOperation> operationList) {
		this.operationList = operationList;
	}
	public String getUuserid() {
		return uuserid;
	}

	public void setUuserid(String uuserid) {
		this.uuserid = uuserid;
	}


}
