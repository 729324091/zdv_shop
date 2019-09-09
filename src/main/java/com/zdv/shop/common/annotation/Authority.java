package com.zdv.shop.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 * @author administrator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authority {
/*
	String ifOper();
	String opCode();
	String opName();	
	int opSeq() default 1;
*/

	String uopcode();//区分层级 权限值
	String uopname();//权限名称
	String uifoper();//类型 0系统 1经销商 2销售商
	String uismenu();//是否菜单 1是菜单，0非菜单
	String uoptype();//操作类型 0无需排后，1新增，2修改，3删除
	int uopseq() default 1;//显示顺序

}
