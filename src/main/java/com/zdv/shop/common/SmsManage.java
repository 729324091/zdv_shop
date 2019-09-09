package com.zdv.shop.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class SmsManage {
	private static final SmsManage instance = new SmsManage();
	 /**
     * 发送短信
     */
    public static String SubmitApiURL="http://202.104.149.61/sdk/SMS?cmd=send&uid=5234TSGF11&psw=6C6978ED735E2A499E648AD93659F90E";
	private SmsManage() {
		
	}	
	public static SmsManage getInstance()
	{
		return instance;
	}
    /**
     * @param 参数
     * @param apiKey  用户唯一标识
     * @param da      发送号码（支持多个，用逗号分割）
     * @param msg     发送内容（url编码）
     * @return
     */

    public static boolean sendMessage(String phone,String msg){
        //生成调用的URL.如果参数中有中文一定要用 java.net.URLEncoder.encode(<参数>,"UTF-8")
		
	  	    String qurl="";	  	    
				try {
					String parmstr="&mobiles="+phone+"&msgid=455544&msg="+URLEncoder.encode(msg,"GBK");
					qurl = SubmitApiURL+parmstr;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				URL url=null;
	            try {
	                url = new URL(qurl);
	                URLConnection URLconnection = url.openConnection();  
	                HttpURLConnection httpConnection = (HttpURLConnection)URLconnection;  
	                int responseCode = httpConnection.getResponseCode();  
	                System.out.println(responseCode);
	                if (responseCode == HttpURLConnection.HTTP_OK) {  
	                    InputStream urlStream = httpConnection.getInputStream();  
	                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream,"GBK"));  

	                    String sCurrentLine = "";  
	                    String sTotalString = "";  
	                    while ((sCurrentLine = bufferedReader.readLine()) != null) {  
	                        sTotalString += sCurrentLine;  
	                    }	                 
	             System.out.println(sTotalString);
	                       //假设该url页面输出为"OK"  
	               
	                	   return true;
	                
	                }
	            } catch (Exception e) {
	                // TODO Auto-generated catch blockeb
	                e.printStackTrace();
	            }  
	            return false;
	}


    public static void main(String[] args) {   
    	System.out.println(sendMessage("13760351001","sussess sh77777777777【阳光达人】"));
    }
    

}
