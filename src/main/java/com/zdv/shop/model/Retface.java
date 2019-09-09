package com.zdv.shop.model;
//新颜授权接口用户授权成功或者失败获得的内容
public class Retface {
	public Retface() {
		
	}
    private String retfaceid;//
    private String userid;//用户主键id
    private String compid;//企业id编码
    private String customerid;//商户号
    private String task_type;//任务类型 通知的任务类型为大写字母
    private String phase_type;//通知阶段 LOGIN:登录阶段 TASK：采集阶段 REPORT:报告阶段
    private String result;//任务结果 1 任务完 0 任务失败
    private String trade_no;//新颜订单号
    private String member_trans_id;//商户订单号
    private String desc;//通知结果描述
    private String finished;//是否最终状态 1 最终状态 0 非最终状态
    private String task_content;//订单内容 包含不同业务的部分条件
    private String user_id;//用户id
    private String mobile;//手机号码 部分业务有该属性如运营商
    private String account;//登录帐号 部分业务有该属性如社保公积金等
    private String createdate;//创建时间	
	public String getRetfaceid() {
		return retfaceid;
	}
	public String getUserid() {
		return userid;
	}
	public String getCompid() {
		return compid;
	}
	public String getCustomerid() {
		return customerid;
	}
	public String getTask_type() {
		return task_type;
	}
	public String getPhase_type() {
		return phase_type;
	}
	public String getResult() {
		return result;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public String getMember_trans_id() {
		return member_trans_id;
	}
	public String getDesc() {
		return desc;
	}
	public String getFinished() {
		return finished;
	}
	public String getTask_content() {
		return task_content;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getMobile() {
		return mobile;
	}
	public String getAccount() {
		return account;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setRetfaceid(String retfaceid) {
		this.retfaceid = retfaceid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setCompid(String compid) {
		this.compid = compid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public void setPhase_type(String phase_type) {
		this.phase_type = phase_type;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public void setMember_trans_id(String member_trans_id) {
		this.member_trans_id = member_trans_id;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setFinished(String finished) {
		this.finished = finished;
	}
	public void setTask_content(String task_content) {
		this.task_content = task_content;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

}