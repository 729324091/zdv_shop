package com.zdv.shop.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectApp {
	private String values = "";
	public void put(JSONObject jo,String key,Object value){
		try {
				key = key.replaceAll(" ", "");
				if(value==null||value.equals(""))
					jo.put(key, "");
				else{
					/*values = URLEncoder.encode(value.toString(),"UTF-8");
					values=values.replaceAll("\\+", "%20");	
					jo.put(key, values);*/
					values = value.toString();
					jo.put(key, values);
				
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	
	public void uput(JSONObject jo,String key,Object value){
		try {
				key = key.replaceAll(" ", "");
				if(value==null)
					jo.put(key, "");
				else{
					
						values = URLEncoder.encode(value.toString(),"UTF-8");
						
						//values=values.replaceAll("\\+", "%20");
						jo.put(key, values);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void put(Map<String,Object> jo,String key,Object value){
		try {
				key = key.replaceAll(" ", "");
				if(value==null)
					jo.put(key, "");
				else{
					
						values = URLEncoder.encode(value.toString(),"UTF-8");
						values=values.replaceAll("\\+", "%20");
						jo.put(key, values);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	public void putde(JSONObject jo,String key,Object value){
		try {
				key = key.replaceAll(" ", "");
				if(value==null)
					jo.put(key, "");
				else{			
						values = URLEncoder.encode(value.toString(),"UTF-8");
						values=values.replaceAll("\\+", "%20");
						jo.put(key, values);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
