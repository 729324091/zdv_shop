package com.zdv.shop.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 考试收入费用信息表
 */
@Table(name = "FN_Examincome")
public class FnExamincome {
	
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator="UUID")
	private  Integer Uid;				//'唯一标识',
	private  Integer Umonid;		// '费用编号',
	private  Integer  Uunitcode;	//'单位编号',
	private  Integer  Uexid;			//'考试编号',
	private  String  Uexname;		//'考试名称',
	private  String Upayway;		//'支付方式',
	private  String Upaycause;		//'支付原因',
	private  boolean  Uisbill;		//'是否有发票',
	private float  Uincome;			//'收入金额',
	private Date  Uwritetime;		//'记录时间',
	private String  Ureamrk;		//'备注'
		
		
	
}
