package com.zdv.shop.common.utils;

import org.htmlparser.beans.StringBean;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static void main(String[] args) {
		StringUtils.getContentUsingStringBean("http://www.ip138.com:8080/search.asp?action=mobile&mobile=13760351001"); 
	}
	

	//判断字符串是否为空
	public static boolean StringisNotEmpty(String str) {
		if(str == null||"".equals(str)) {			
			return false;
		}else {
			if(str.length() <= 0||str.isEmpty())
				return false;
			else
				return true;
		}
	}
	
	 public static String[] toArray(String parseString, String splitString) {
	        return toArray(parseString, splitString, false);
	    }

	    /**
	     * 分隔一个字符串
	     *
	     * @param parseString  String 原始字符串
	     * @param splitString  String 分隔串
	     * @param returnDelims boolean 返回值是否包含分隔串
	     * @return String[] 分隔后的字符串
	     */
	    public static String[] toArray(String parseString, String splitString,
	                                   boolean returnDelims) {
	        StringTokenizer tokens = new StringTokenizer(parseString, splitString
	                , returnDelims);
	        String[] values = new String[tokens.countTokens()];
	        int i = 0;
	        while (tokens.hasMoreTokens()) {
	            values[i++] = tokens.nextToken();
	        }
	        return values;
	    }

	//签到金额数控制
	public static Double signmoney(){
		java.util.Random random=new java.util.Random();// 定义随机类
		int result=random.nextInt(10);// 返回[0,5)集合中的整数，注意不包括10
		return (result+1)*0.01;              // +1后，[0,5)集合变为[1,11)集合，满足要求
	}
    
	    public static Integer StringToInteger(String str) {
	    	if (str==null||"".equalsIgnoreCase(str)) {
				return 0;
			}
	    	try {
				double d = Double.valueOf(str);
				int num = (int)d;
				return num;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
	    	return 0;
	    }
	  

	    //Map 排序处理
	    private static Map<String, String> sortMapByKey(Map<String, String> oriMap) {  
	        if (oriMap == null || oriMap.isEmpty()) {  
	            return null;  
	        }  
	        Map<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {  
	            public int compare(String key1, String key2) {  
	                int intKey1 = 0, intKey2 = 0;  
	                try {  
	                    intKey1 = getInt(key1);  
	                    intKey2 = getInt(key2);  
	                } catch (Exception e) {  
	                    intKey1 = 0;   
	                    intKey2 = 0;  
	                }  
	                return intKey1 - intKey2;  
	            }});  
	        sortedMap.putAll(oriMap);  
	        return sortedMap;  
	    }  

	    private static int getInt(String str) {  
	        int i = 0;  
	        try {  
	            Pattern p = Pattern.compile("^\\d+");  
	            Matcher m = p.matcher(str);  
	            if (m.find()) {  
	                i = Integer.valueOf(m.group());  
	            }  
	        } catch (NumberFormatException e) {  
	            e.printStackTrace();  
	        }  
	        return i;  
	    } 
		  // 视频缩略图截取 
		 // inFile  输入文件(包括完整路径) 
		 // outFile 输出文件(可包括完整路径)
		 public static String  transfer(String inFile, String outFile) { 
			 String command = "ffmpeg -i " + inFile + " -y -f image2 -ss 00:00:02 -t 00:00:01 -s 176x144 " + outFile; 
			 String line = null; 
			 try { 
				 Runtime rt = Runtime.getRuntime(); 
				 Process proc = rt.exec(command); 
				 InputStream stderr = proc.getErrorStream(); 
				 InputStreamReader isr = new InputStreamReader(stderr); 
				 BufferedReader br = new BufferedReader(isr); 
				 
				 while ((line = br.readLine()) != null) 
				 System.out.println(line);
			  } catch (Throwable t) { 
				 t.printStackTrace(); 
				 //ajaxHtml(ToJson.getJsonTip("999", "line", line));
				 //return null; 
			  } 
			 //ajaxHtml(ToJson.getJsonTip("000", "line", line));
			 return null; 
		  }

		public static boolean objectIsNull(Object o) {
			if(o==null||o.equals("")||o.equals("null"))
				return true;
			else
				return false;
		}
		//对为String json
		public static String getObjectValue(Object o) {
			if(o==null||o.equals("")||o.equals("null"))
				return "0";
			else
				return o.toString();
		}
		public static boolean isMobileNO(String mobiles){  		  
			Pattern p = Pattern.compile("^((13[0-9])|(15[0-9,\\D])|(17[0-9,\\D])||(19[0-9,\\\\D])|(18[0-9]))\\d{8}$");  
			Matcher m = p.matcher(mobiles);  
			return m.matches();  
		} 
	    /**
	     * 首字母转大写
	     * @param str
	     * @return
	     */
	    public static String toFirstLetterUpperCase(String str) {  
	        if(str == null || str.length() < 2){  
	            return str;  
	        }  
	        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());  
	     }  
	   //隐藏一个姓名
	    public static String douname(String str) {
	
	    	if(str==null||"".equals(str)||str.equals("null"))
	    		return "***";
	    	else if(str.length()<2)
	    		return str;
	    	else if(str.length()>=2&&str.length()<11)
	    		return str.substring(0, 1)+"*"+str.substring(2, str.length());
	    	else
	    		return str.substring(0, 4)+"****"+str.substring(7, str.length());
	    }
		public static String getContentUsingStringBean(String url) {
			try {
		  	  	String s ="";
		        StringBean sb = new StringBean();
		        sb.setLinks(true);// 是否显示web页面的连接(Links)
		        //为了取得页面的整洁美观一般设置上面两项为true , 如果要保持页面的原有格式, 如代码页面的空格缩进 可以设置为false
		        sb.setCollapse(true);// 如果是true的话把一系列空白字符用一个字符替代.
		        sb.setReplaceNonBreakingSpaces(true);//If true regular space
		        sb.setURL(url);
		        s = sb.getStrings();	        
		        s = s.replaceAll("\\s+", " ");
		        return s.split("卡号归属地")[1].split("卡 类 型")[0];
			}catch (Exception e) {
				return "";
			}
		 }
		//处理图片路径
		public static String doimges(String publicurl,String imppath) {
			return publicurl+"upload/default/default.jpg";
			/*if(!(imppath==null||imppath.equals("")||imppath.equals("null"))) {
				imppath = imppath.trim();
				if(imppath.contains("http")||imppath.contains("HTTP")) {
					return imppath;
				}else { 			
					String[] aimppath = imppath.split("\\;");
					if(aimppath.length==1){
						return publicurl+imppath;
					}else{
						return publicurl+imppath.replaceAll(";", ";"+publicurl);
					}
				}
					
			}else
				return publicurl+"upload/default/default.jpg";*/
		}
		//语言处理
		@SuppressWarnings("unused")
		public static Locale dolanguge(String lang) {
			if(objectIsNull(lang)) {
				lang = "zh_CN";
			}else {
				lang = lang.replaceAll(" ", "");
				lang = lang.replaceAll("-", "_");
				if(lang.equalsIgnoreCase("zh_Hans")||lang.equalsIgnoreCase("zh_Hans_CN")) {
					lang="zh_CN";
		    	 }
			}
			Locale locale = LocaleContextHolder.getLocale();
			String[] language = lang.split("_");         
	        locale = new Locale(language[0], language[1]);
	        
			return locale;
		}


	/**
	 * 提供精确的小数位四舍五入处理。
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
