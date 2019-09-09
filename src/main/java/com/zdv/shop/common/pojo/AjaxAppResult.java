package com.zdv.shop.common.pojo;

/**
 * 封装返回数据 给APP用
 * @author jock
 * @2018年08月28日
 */
public class AjaxAppResult {

	private String retcode = "000";
	private String retmsg = "操作成功";
	private Object data;
	
	public AjaxAppResult(String retcode, String retmsg, Object data){
		this.retcode = retcode;
		this.retmsg = retmsg;
		this.data = data;
	}
	
	public AjaxAppResult(String retcode, String retmsg){
		this.retcode = retcode;
		this.retmsg = retmsg;
	}
	
	public AjaxAppResult(Object data){
		this.retmsg = "操作成功";
		this.data = data;
	}

	
	public AjaxAppResult(){
		
	}

	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getRetmsg() {
		return retmsg;
	}
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "AjaxResult [retcode=" + retcode + ", retmsg=" + retmsg + ", data=" + data + "]";
	}
	
}
