package com.zdv.shop.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.zdv.shop.common.Constant;


public class ToJson {
	//把主要内容转化为json 然后封装传出
	public static String getJson(String code,String json) {
		try {
			if(json==null||json.equalsIgnoreCase("")){//URLEncoder.encode(, "UTF-8")
				json ="[]";
			}
			return "{\"retcode\":\""+code+"\",\"retcode\":\""+globalConst(code)+"\",\"result\":"+json.toString()+"}";
		} catch (Exception e) {//UnsupportedEncodingException
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String getJsonnew(String code,String json) {
		try {
			if(json==null||json.equalsIgnoreCase("")){
				json ="[]";
			}
			return "{\"retcode\":\""+code+"\",\"retcode\":\""+globalConst(code)+"\",\"result\":"+json.toString()+"}";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//返回
	public static String getJsonTip(String code,String name,String value) {
		if(value==null||value.equalsIgnoreCase("")){
			try {
				return "{\"retcode\":\""+code+"\",\"retcode\":\""+globalConst(code)+"\",\"result\":[]}";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				value = URLEncoder.encode(value.toString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				return "{\"retcode\":\""+code+"\",\"retcode\":\""+globalConst(code)+"\",\"result\":[{\""+name+"\":\""+value+"\"}]}";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	//直接中文参数
	public static String getJsonCnTip(String code,String name) {
		try {
			return "{\"retcode\":\""+code+"\",\"retcode\":\""+name+"\",\"result\":[]}";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static String getJsonCar(String code,String json) {
		if(json==null||json.equalsIgnoreCase("")){
			json ="[]";
		}
		try {
			return "{\"retcode\":\""+code+"\",\"retcode\":\""+URLEncoder.encode(globalConst(code), "UTF-8")+"\",\"result\":"+json.toString()+"}";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String getJsonSetCar(String json){
		if(json==null||json.equalsIgnoreCase("")){
			json ="[]";
		}
		return "{\"mycar\":"+json.toString()+"}";
	}
	public static String getJson(String code,String m,String v,String json){
		if(json==null||json.equalsIgnoreCase("")){
			json ="[]";
		}
		try {
			return "{\"retcode\":\""+code+"\",\"retcode\":\""+globalConst(code)+"\",\""+m+"\":\""+v+"\",\"result\":"+json.toString()+"}";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String getContentJson(String code,String json) {
	/*	try {
			json = new String(json.getBytes("GB2312"),"UTF-8"); //URLEncoder.encode(json.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if(json==null||json.equalsIgnoreCase("")){
			json ="[]";
		}
		try {
			return "{\"retcode\":\""+code+"\",\"retcode\":\""+URLEncoder.encode(globalConst(code), "UTF-8")+"\",\"content\":"+json.toString()+"}";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unused")
	private static String globalConst(String code){
		if(code.equalsIgnoreCase("000"))
			return Constant.key000;
		else if(code.equalsIgnoreCase("100"))
			return Constant.key100;
		else if(code.equalsIgnoreCase("001"))
			return Constant.key001;
		else if(code.equalsIgnoreCase("005"))
			return Constant.key005;
		else if(code.equalsIgnoreCase("006"))
			return Constant.key006;
		else if(code.equalsIgnoreCase("007"))
			return Constant.key007;
		else if(code.equalsIgnoreCase("008"))
			return Constant.key008;
		else if(code.equalsIgnoreCase("009"))
			return Constant.key009;
		else if(code.equalsIgnoreCase("010"))
			return Constant.key010;
		else if(code.equalsIgnoreCase("011"))
			return Constant.key011;
		else if(code.equalsIgnoreCase("012"))
			return Constant.key012;
		else if(code.equalsIgnoreCase("013"))
			return Constant.key013;
		else if(code.equalsIgnoreCase("014"))
			return Constant.key014;
		else if(code.equalsIgnoreCase("015"))
			return Constant.key015;
		else if(code.equalsIgnoreCase("016"))
			return Constant.key016;
		else if(code.equalsIgnoreCase("017"))
			return Constant.key017;
		else if(code.equalsIgnoreCase("018"))
			return Constant.key018;
		else if(code.equalsIgnoreCase("014"))
			return Constant.key014;
		else if(code.equalsIgnoreCase("101"))
			return Constant.key101;
		else if(code.equalsIgnoreCase("102"))
			return Constant.key102;
		else if(code.equalsIgnoreCase("103"))
			return Constant.key103;
		else if(code.equalsIgnoreCase("104"))
			return Constant.key104;
		else if(code.equalsIgnoreCase("105"))
			return Constant.key105;
		else if(code.equalsIgnoreCase("106"))
			return Constant.key106;
		else if(code.equalsIgnoreCase("107"))
			return Constant.key107;
		else if(code.equalsIgnoreCase("108"))
			return Constant.key108;
		else if(code.equalsIgnoreCase("109"))
			return Constant.key109;
		else if(code.equalsIgnoreCase("110"))
			return Constant.key110;
		else if(code.equalsIgnoreCase("111"))
			return Constant.key111;
		else if(code.equalsIgnoreCase("112"))
			return Constant.key112;
		else if(code.equalsIgnoreCase("113"))
			return Constant.key113;
		else if(code.equalsIgnoreCase("114"))
			return Constant.key114;
		else if(code.equalsIgnoreCase("115"))
			return Constant.key115;
		else if(code.equalsIgnoreCase("116"))
			return Constant.key116;
		else if(code.equalsIgnoreCase("117"))
			return Constant.key117;
		else if(code.equalsIgnoreCase("118"))
			return Constant.key118;
		else if(code.equalsIgnoreCase("119"))
			return Constant.key119;
		else if(code.equalsIgnoreCase("120"))
			return Constant.key120;
		else if(code.equalsIgnoreCase("121"))
			return Constant.key121;
		else if(code.equalsIgnoreCase("122"))
			return Constant.key122;
		else if(code.equalsIgnoreCase("123"))
			return Constant.key123;
		else if(code.equalsIgnoreCase("124"))
			return Constant.key124;
		else if(code.equalsIgnoreCase("125"))
			return Constant.key125;
		else if(code.equalsIgnoreCase("126"))
			return Constant.key126;
		else if(code.equalsIgnoreCase("127"))
			return Constant.key127;
		else if(code.equalsIgnoreCase("128"))
			return Constant.key128;
		else if(code.equalsIgnoreCase("129"))
			return Constant.key129;
		else if(code.equalsIgnoreCase("130"))
			return Constant.key130;
		else if(code.equalsIgnoreCase("131"))
			return Constant.key131;
		else if(code.equalsIgnoreCase("132"))
			return Constant.key132;
		else if(code.equalsIgnoreCase("133"))
			return Constant.key133;
		else if(code.equalsIgnoreCase("134"))
			return Constant.key134;
		else if(code.equalsIgnoreCase("135"))
			return Constant.key135;
		else if(code.equalsIgnoreCase("136"))
			return Constant.key136;
		else if(code.equalsIgnoreCase("137"))
			return Constant.key137;
		else if(code.equalsIgnoreCase("138"))
			return Constant.key138;
		else if(code.equalsIgnoreCase("139"))
			return Constant.key139;
		else if(code.equalsIgnoreCase("140"))
			return Constant.key140;
		else if(code.equalsIgnoreCase("141"))
			return Constant.key141;
		else if(code.equalsIgnoreCase("142"))
			return Constant.key142;
		else if(code.equalsIgnoreCase("143"))
			return Constant.key143;
		else if(code.equalsIgnoreCase("144"))
			return Constant.key144;
		else if(code.equalsIgnoreCase("145"))
			return Constant.key145;
		else if(code.equalsIgnoreCase("146"))
			return Constant.key146;
		else if(code.equalsIgnoreCase("147"))
			return Constant.key147;
		else if(code.equalsIgnoreCase("148"))
			return Constant.key148;
		else if(code.equalsIgnoreCase("149"))
			return Constant.key149;
		else if(code.equalsIgnoreCase("150"))
			return Constant.key150;
		else if(code.equalsIgnoreCase("151"))
			return Constant.key151;
		else if(code.equalsIgnoreCase("152"))
			return Constant.key152;
		else if(code.equalsIgnoreCase("153"))
			return Constant.key153;
		else if(code.equalsIgnoreCase("154"))
			return Constant.key154;
		else if(code.equalsIgnoreCase("155"))
			return Constant.key155;
		else if(code.equalsIgnoreCase("156"))
			return Constant.key156;
		else if(code.equalsIgnoreCase("157"))
			return Constant.key157;
		else if(code.equalsIgnoreCase("158"))
			return Constant.key158;
		else if(code.equalsIgnoreCase("159"))
			return Constant.key159;
		else if(code.equalsIgnoreCase("160"))
			return Constant.key160;
		else if(code.equalsIgnoreCase("161"))
			return Constant.key161;
		else if(code.equalsIgnoreCase("162"))
			return Constant.key162;
		else if(code.equalsIgnoreCase("163"))
			return Constant.key163;
		else if(code.equalsIgnoreCase("164"))
			return Constant.key164;
		else if(code.equalsIgnoreCase("165"))
			return Constant.key165;
		else if(code.equalsIgnoreCase("166"))
			return Constant.key166;
		else if(code.equalsIgnoreCase("167"))
			return Constant.key167;
		else if(code.equalsIgnoreCase("168"))
			return Constant.key168;
		else if(code.equalsIgnoreCase("169"))
			return Constant.key169;
		else if(code.equalsIgnoreCase("170"))
			return Constant.key170;
		else if(code.equalsIgnoreCase("171"))
			return Constant.key171;
		else if(code.equalsIgnoreCase("172"))
			return Constant.key172;
		else if(code.equalsIgnoreCase("173"))
			return Constant.key173;
		else if(code.equalsIgnoreCase("175"))
			return Constant.key175;
		else if(code.equalsIgnoreCase("176"))
			return Constant.key176;
		else if(code.equalsIgnoreCase("177"))
			return Constant.key177;
		else if(code.equalsIgnoreCase("178"))
			return Constant.key178;
		else if(code.equalsIgnoreCase("179"))
			return Constant.key179;
		else if(code.equalsIgnoreCase("180"))
			return Constant.key180;
		else if(code.equalsIgnoreCase("181"))
			return Constant.key181;
		else if(code.equalsIgnoreCase("182"))
			return Constant.key182;
		else if(code.equalsIgnoreCase("183"))
			return Constant.key183;
		else if(code.equalsIgnoreCase("184"))
			return Constant.key184;
		else if(code.equalsIgnoreCase("185"))
			return Constant.key185;
		else if(code.equalsIgnoreCase("186"))
			return Constant.key186;
		else if(code.equalsIgnoreCase("187"))
			return Constant.key187;
		else if(code.equalsIgnoreCase("188"))
			return Constant.key188;
		else if(code.equalsIgnoreCase("189"))
			return Constant.key189;
		else if(code.equalsIgnoreCase("190"))
			return Constant.key190;
		else if(code.equalsIgnoreCase("191"))
			return Constant.key191;
		else if(code.equalsIgnoreCase("192"))
			return Constant.key192;
		else if(code.equalsIgnoreCase("193"))
			return Constant.key193;
		else if(code.equalsIgnoreCase("194"))
			return Constant.key194;
		else if(code.equalsIgnoreCase("195"))
			return Constant.key195;
		else if(code.equalsIgnoreCase("196"))
			return Constant.key196;
		else if(code.equalsIgnoreCase("197"))
			return Constant.key197;
		else if(code.equalsIgnoreCase("198"))
			return Constant.key198;
		else if(code.equalsIgnoreCase("199"))
			return Constant.key199;
		else if(code.equalsIgnoreCase("200"))
			return Constant.key200;
		else if(code.equalsIgnoreCase("201"))
			return Constant.key201;
		else if(code.equalsIgnoreCase("202"))
			return Constant.key202;
		else if(code.equalsIgnoreCase("203"))
			return Constant.key203;
		else if(code.equalsIgnoreCase("204"))
			return Constant.key204;
		else if(code.equalsIgnoreCase("205"))
			return Constant.key205;
		else if(code.equalsIgnoreCase("206"))
			return Constant.key206;
		else if(code.equalsIgnoreCase("207"))
			return Constant.key207;
		else if(code.equalsIgnoreCase("208"))
			return Constant.key208;
		else if(code.equalsIgnoreCase("209"))
			return Constant.key209;
		else if(code.equalsIgnoreCase("210"))
			return Constant.key210;
		else if(code.equalsIgnoreCase("211"))
			return Constant.key211;
		else if(code.equalsIgnoreCase("212"))
			return Constant.key212;
		else if(code.equalsIgnoreCase("213"))
			return Constant.key213;
		else if(code.equalsIgnoreCase("214"))
			return Constant.key214;
		else if(code.equalsIgnoreCase("215"))
			return Constant.key215;
		else if(code.equalsIgnoreCase("216"))
			return Constant.key216;
		else if(code.equalsIgnoreCase("217"))
			return Constant.key217;
		else if(code.equalsIgnoreCase("218"))
			return Constant.key218;
		else if(code.equalsIgnoreCase("219"))
			return Constant.key219;
		else if(code.equalsIgnoreCase("220"))
			return Constant.key220;
		else if(code.equalsIgnoreCase("221"))
			return Constant.key221;
		else if(code.equalsIgnoreCase("222"))
			return Constant.key222;
		else if(code.equalsIgnoreCase("223"))
			return Constant.key223;
		else if(code.equalsIgnoreCase("224"))
			return Constant.key224;
		else if(code.equalsIgnoreCase("225"))
			return Constant.key225;
		else if(code.equalsIgnoreCase("226"))
			return Constant.key226;
		else if(code.equalsIgnoreCase("227"))
			return Constant.key227;
		else if(code.equalsIgnoreCase("228"))
			return Constant.key228;
		else if(code.equalsIgnoreCase("229"))
			return Constant.key229;
		else if(code.equalsIgnoreCase("230"))
			return Constant.key230;
		else if(code.equalsIgnoreCase("231"))
			return Constant.key231;
		else if(code.equalsIgnoreCase("232"))
			return Constant.key232;
		else if(code.equalsIgnoreCase("233"))
			return Constant.key233;
		else if(code.equalsIgnoreCase("234"))
			return Constant.key234;
		else if(code.equalsIgnoreCase("235"))
			return Constant.key235;
		else if(code.equalsIgnoreCase("236"))
			return Constant.key236;
		else if(code.equalsIgnoreCase("900"))
			return Constant.key900;
		else if(code.equalsIgnoreCase("996"))
			return Constant.key996;
		else if(code.equalsIgnoreCase("997"))
			return Constant.key997;
		else if(code.equalsIgnoreCase("998"))
			return Constant.key998;
		else
			return Constant.key999;
	}
}
