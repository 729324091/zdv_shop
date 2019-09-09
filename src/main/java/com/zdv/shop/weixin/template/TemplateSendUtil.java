package com.zdv.shop.weixin.template;

import com.zdv.shop.common.Constant;
import com.zdv.shop.weixin.WeiXinUtil;
import com.zdv.shop.weixin.message.Token;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;

public class TemplateSendUtil {

	 
    // 凭证获取（GET）
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    
    /**
     * 发送 https 请求
     * 
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject（通过 JSONObject.get(key) 的方式获取 JSON 对象的属性值）
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        
        JSONObject jsonObject = null;
        
        try {
            // 创建 SSLContext 对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述 SSLContext 对象中得到 SSLSocketFactory 对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            
            // 当 outputStr 不为 null 时，向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
    
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
        	ce.printStackTrace();

            System.out.println(" 连接超时：{}");
        } catch (Exception e) {
        	e.printStackTrace();

        	System.out.println("https 请求异常：{}");
        }
        
        return jsonObject;
    }

    /**
     * 获取接口访问凭证
     * 
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static Token getToken(String appid, String appsecret) {
    	
        Token token = null;    
        String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);

        // 发起GET请求获取凭证
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        
        if (null != jsonObject) {
            try {
                token = new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (Exception e) {
                token = null;
                // 获取token失败
                System.out.println("获取token失败 errcode:{} errmsg:{}"+jsonObject.getInt("errcode")+jsonObject.getString("errmsg"));
            }
        }
        return token;
    }
    
    /**
     * URL编码（utf-8）
     * 
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 根据内容类型判断文件扩展名
     * 
     * @param contentType 内容类型
     * @return
     */
    public static String getFileExt(String contentType) {
        String fileExt = "";
        if ("image/jpeg".equals(contentType))
            fileExt = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileExt = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileExt = ".amr";
        else if ("video/mp4".equals(contentType))
            fileExt = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileExt = ".mp4";
        return fileExt;
    }
    
    
    /**
     * 发送模板消息
     * appId 公众账号的唯一标识
     * appSecret 公众账号的密钥
     * openId 用户标识  MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ
     */
    public static void send_template_message(String appId, String appSecret, String openId,String template_id,String title,String word1,String word2,String word3,String re) {

        Token token = TemplateSendUtil.getToken(appId, appSecret);
        
        String access_token = token.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;

        PurchaseTemplate temp = new PurchaseTemplate();
        Data data = new Data();
        Data_first first = new Data_first();
        Data_keyword1 keyword1 = new Data_keyword1();
        Data_keyword2 keyword2 = new Data_keyword2();
        Data_keyword3 keyword3 = new Data_keyword3();

        Data_remark remark = new Data_remark();

        first.setValue(title);
        first.setColor("#173177");
        keyword1.setValue(word1);
        keyword1.setColor("#173177");
        keyword2.setValue(word2);
        keyword2.setColor("#173177");
        keyword3.setValue(word3);
        keyword3.setColor("#173177");
        remark.setValue(re);
        remark.setColor("#173177");

        data.setFirst(first);
        data.setKeyword1(keyword1);
        data.setKeyword2(keyword2);
        data.setKeyword3(keyword3);
        data.setRemark(remark);
        temp.setTouser(openId);
        temp.setTemplate_id(template_id);
        temp.setUrl("");
        temp.setTopcolor("#173177");
        temp.setData(data);
       
        
        String jsonString = JSONObject.fromObject(temp).toString();
        JSONObject jsonObject = WeiXinUtil.httpRequest(url, "POST", jsonString);
        int result = 0;
        if (null != jsonObject) {  
             if (0 != jsonObject.getInt("errcode")) {  
                 result = jsonObject.getInt("errcode");  
                 // log.error("错误 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
             }  
         }
       // log.info("模板消息发送结果："+result);
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	SslTest st = new SslTest();

		try {
			String a= st.getRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7d22f0ee17502f9a&secret=40cf7af0426f661c203b3bfe3b50ff5d", 3000);

		} catch (Exception e) {
			// TODO Auto-generated catch block b6bcdc48c76da175776fb4a0a4c02525
			e.printStackTrace();
		}*/
		send_template_message("wx1274d3fe4548ef75","1c25cbcdbd6784298af0e78883e6c33a","osFug50CY4NsiffJtMezJ72DZyV8","MNFURs3OtrK8tpRxPIjLE0MjCJwGEp3OH3XqRp9TGSQ","新的用户进来",Constant.ucustomername,"关注成功","2019-05-19","新数据");
		
		//send_template_message("wx1274d3fe4548ef75","1c25cbcdbd6784298af0e78883e6c33a","osFug59oqxcVExFkKPazMy5f32z0");
	}

}
