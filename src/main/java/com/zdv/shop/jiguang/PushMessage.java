package com.zdv.shop.jiguang;

import java.util.HashMap;
import java.util.Map;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

@SuppressWarnings(PushMessage.SERIAL)
public class PushMessage {

	static final String SERIAL = "serial";
	public static  String appkey ="6596d666fec8d02e4bfac181";
	public static  String masterSecret = "7473717bff9a30946803d20d";
	public static  String appkey1 ="92325b05f506e2026e858dfc";
	public static  String masterSecret1 = "568247b7ab15b02c14cf3961";
	private static JPushClient jPushClient = new JPushClient(masterSecret,appkey);
	private static JPushClient jPushClient1 = new JPushClient(masterSecret1,appkey1);
	//极光 易店生活-经销商
	//AppKey 6596d666fec8d02e4bfac181
	//7473717bff9a30946803d20d
	// 极光 易店生活-零售店
	//92325b05f506e2026e858dfc
	//568247b7ab15b02c14cf3961
	public PushMessage(){
		
	}
	public static void main(String[] args) {
		(new PushMessage()).pushmessagejxs("12402751925661696 ","12402751925661696","你有新的订单信息","老舒下单了");
	}
	/**
	 * 极光 易店生活-经销商
	 * @param userid 发送人ID
	 * @param touserid 发送对象
	 * @param title 标题  
	 * @param content ；内容
	 */
	public static void pushmessagejxs(String userid,String touserid,String title,String content){
		   jiguangSendPushAndroid(userid,touserid,title,content,"0");
		
	}
	/**
	 * 极光 易店生活-零售店
	 * @param userid 发送人ID
	 * @param touserid 发送对象
	 * @param title 标题  
	 * @param content ；内容
	 */
	public static void pushmessagelsd(String userid,String touserid,String title,String content){
		   jiguangSendPushAndroid1(userid,touserid,title,content,"0");
		
	}
	public static void pushmessagelogin(String userid,String touserid,String title,String content,String type){
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("msg_title", title);
		extras.put("content", content);
		extras.put("type", type);
		sendToRegistrationId(touserid,title, extras);
		//sendToRegistrationId(touserid,title, extras);
		
	}
	//系统信息 推送所有的人
	public static void pushallmessage(String notification_title, String msg_title, String msg_content) {
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("msg_title", msg_title);
		extras.put("content", msg_content);
		extras.put("type", "1");
		Map<String, String> androidextras = new HashMap<String, String>();
		androidextras.put("type", "1");
		 try { 
				PushPayload pushPayload = buildPushObject_ios_all_alertWithTitle(notification_title,extras);
				PushResult result1 =  jPushClient.sendPush(pushPayload);
				
	    		//System.out.println("=============Got result 2-"+result1);
				
		 } catch (APIConnectionException e) {
	            // Connection error, should retry later

	        } catch (APIRequestException e) {
	            // Should review the error, and fix the request

	        }
		 int n = sendToAllAndroid(notification_title,msg_title,msg_content,androidextras);
			//System.out.println("=============Got result 1-"+n);
	}
	//b   false表示开发，true表示生产
	public static void jiguangSendPush(String userid,String touserid,String title,String content,boolean b,String type) {
        try { 
    		Map<String, String> extras = new HashMap<String, String>();
    		extras.put("content", content);
    		extras.put("userid", userid);//推送用户ID
    		extras.put("type", type);
    		PushPayload pushPayload = buildPushObject_ios_one_alertWithTitle(touserid,title,extras,b);
    		PushResult result1 =  jPushClient.sendPush(pushPayload);
    	
        } catch (APIConnectionException e) {
            // Connection error, should retry later

        } catch (APIRequestException e) {
            // Should review the error, and fix the request

        }
	}
	
	public static void jiguangSendPushAndroid(String userid,String touserid,String title,String content,String type) {
        try {
 
    		Map<String, String> extras = new HashMap<String, String>();
    		extras.put("userid", userid);//ID
    		extras.put("type", type);
    		//System.out.println("=============Got result -"+new Date());
    		PushResult result = jPushClient.sendAndroidNotificationWithAlias(title, content, extras, touserid);
    		//System.out.println("Android=============Got result -"+result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later

        } catch (APIRequestException e) {
            // Should review the error, and fix the request

        }
	}
	public static void jiguangSendPushAndroid1(String userid,String touserid,String title,String content,String type) {
        try {
 
    		Map<String, String> extras = new HashMap<String, String>();
    		extras.put("userid", userid);//ID
    		extras.put("type", type);
    		//System.out.println("=============Got result -"+new Date());
    		PushResult result = jPushClient1.sendAndroidNotificationWithAlias(title, content, extras, touserid);
    		//System.out.println("Android=============Got result -"+result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later

        } catch (APIRequestException e) {
            // Should review the error, and fix the request

        }
	}
	 private static PushPayload buildPushObject_ios_one_alertWithTitle(String alias,String notification_title,Map<String, String> extras,boolean apnsProduct) {

	        return PushPayload.newBuilder()

	                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台

	                .setPlatform(Platform.ios())

	                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id

	                .setAudience(Audience.alias(alias))

	                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发

	                .setNotification(Notification.newBuilder()

	                        //指定当前推送的android通知

	                        .addPlatformNotification(IosNotification.newBuilder()

	                                //传一个IosAlert对象，指定apns title、title、subtitle等

	                                .setAlert(notification_title)

	                                //直接传alert

	                                //此项是指定此推送的badge自动加1

	                                .incrBadge(1)

	                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，

	                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音

	                                .setSound("sound.caf")
	                                .addExtras(extras)

	                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value

	                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification

	                               // .setContentAvailable(true)

	 

	                                .build())

	                        .build()

	                )
	                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，

	                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的

	                .setOptions(Options.newBuilder()

	                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义

	                        .setApnsProduction(apnsProduct)

	                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录

	                        .setSendno(1)

	                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒

	                        .setTimeToLive(86400)

	                        .build())

	                .build();

	    }
	 	//推送所有的IOS用户
	   private static PushPayload buildPushObject_ios_all_alertWithTitle(String notification_title,Map<String, String> extras) {

	        return PushPayload.newBuilder()

	                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台

	                .setPlatform(Platform.ios())

	                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id

	                .setAudience(Audience.all())

	                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发

	                .setNotification(Notification.newBuilder()

	                        //指定当前推送的android通知

	                        .addPlatformNotification(IosNotification.newBuilder()

	                                //传一个IosAlert对象，指定apns title、title、subtitle等

	                                .setAlert(notification_title)

	                                //直接传alert

	                                //此项是指定此推送的badge自动加1

	                                .incrBadge(1)

	                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，

	                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音

	                                .setSound("sound.caf")
	                                .addExtras(extras)

	                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value

	                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
	                                .addExtra("iosNotification extras key","extrasparam")
	                               .setContentAvailable(true)

	 

	                                .build())

	                        .build()

	                )
	                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，

	                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的

	                .setOptions(Options.newBuilder()

	                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义

	                        .setApnsProduction(false)

	                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录

	                        .setSendno(1)

	                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒

	                        .setTimeToLive(86400)

	                        .build())

	                .build();

	    }



	//极光推送


    /**

     * 推送给设备标识参数的用户

     * @param registrationId 设备标识

     * @param notification_title 通知内容标题

     * @param msg_title 消息内容标题

     * @param msg_content 消息内容

     * @param extrasparam 扩展字段

     * @return 0推送失败，1推送成功

     */

    public static int sendToRegistrationId( String registrationId,String notification_title, Map<String, String> extras) {

        int result = 0;

        try {

            PushPayload pushPayload= buildPushObject_all_registrationId_alertWithTitle(registrationId,notification_title,extras);

            System.out.println(pushPayload);

            PushResult pushResult=jPushClient.sendPush(pushPayload);

            System.out.println(pushResult);

            if(pushResult.getResponseCode()==200){

                result=1;

            }

        } catch (APIConnectionException e) {

            e.printStackTrace();

 

        } catch (APIRequestException e) {

            e.printStackTrace();

        }

 

         return result;

    }

 

    /**

     * 发送给所有安卓用户

     * @param notification_title 通知内容标题

     * @param msg_title 消息内容标题

     * @param msg_content 消息内容

     * @param extrasparam 扩展字段

     * @return 0推送失败，1推送成功

     */

    public static int sendToAllAndroid( String notification_title, String msg_title, String msg_content, Map<String, String> extras) {

        int result = 0;

        try {
        	PushPayload pushPayload= buildPushObject_android_all_alertWithTitle(notification_title,msg_title,msg_content,"extrasparam");
            System.out.println(pushPayload);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if(pushResult.getResponseCode()==200){
                result=1;
            }
            /*PushPayload pushPayload= buildPushObject_android_all_alertWithTitle(notification_title,msg_title,msg_content,extras);

            System.out.println(pushPayload);

            PushResult pushResult=jPushClient.sendPush(pushPayload);

            System.out.println(pushResult);

            if(pushResult.getResponseCode()==200){

                result=1;

            }*/

        } catch (Exception e) {

 

            e.printStackTrace();

        }

 

         return result;

    }
    //自定义
    private static PushPayload buildPushObject_android_all_alertWithTitle(String notification_title, String msg_title, String msg_content, String extrasparam) {
        System.out.println("----------buildPushObject_android_registrationId_alertWithTitle");
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.android())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.all())
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(notification_title)
                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("androidNotification extras key",extrasparam)
                                .build())
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtra("message extras key",extrasparam)
                        .build())
 
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build())
                .build();
    }

    /**

     * 发送给所有IOS用户

     * @param notification_title 通知内容标题

     * @param msg_title 消息内容标题

     * @param msg_content 消息内容

     * @param extrasparam 扩展字段

     * @return 0推送失败，1推送成功

     */

    public static int sendToAllIos(String alias,String notification_title,  Map<String, String> extras,boolean b) {

        int result = 0;
        try {
            PushPayload pushPayload= buildPushObject_ios_one_alertWithTitle(alias,notification_title,extras,b);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
                result=1;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
         return result;

    }

 

    /**

     * 发送给所有用户

     * @param notification_title 通知内容标题

     * @param msg_title 消息内容标题

     * @param msg_content 消息内容

     * @param extrasparam 扩展字段

     * @return 0推送失败，1推送成功

     */

    public static int sendToAll( String notification_title, Map<String, String> extras) {

        int result = 0;
        try {
            PushPayload pushPayload= buildPushObject_android_and_ios(notification_title,extras);

           PushResult pushResult=jPushClient.sendPush(pushPayload);

            if(pushResult.getResponseCode()==200){

                result=1;

            }

        } catch (Exception e) {

 

            e.printStackTrace();

        }

 

        return result;

    }

    public static PushPayload buildPushObject_android_and_ios(String notification_title, Map<String, String> extras) {

        return PushPayload.newBuilder()

                .setPlatform(Platform.android_ios())

                .setAudience(Audience.all())

                .setNotification(Notification.newBuilder()

                        .setAlert(notification_title)

                        .addPlatformNotification(AndroidNotification.newBuilder()

                                .setAlert(notification_title)

                                .setTitle(notification_title)

                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）

                                 .addExtras(extras)

                                .build()

                        )

                        .addPlatformNotification(IosNotification.newBuilder()

                                //传一个IosAlert对象，指定apns title、title、subtitle等

                                .setAlert(notification_title)

                                //直接传alert

                                //此项是指定此推送的badge自动加1

                                .incrBadge(1)

                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，

                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音

                                .setSound("sound.caf")

                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）

                                .addExtras(extras)

                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification

                                // .setContentAvailable(true)

 

                                .build()

                        )

                        .build()

                )

 

                .setOptions(Options.newBuilder()

                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义

                        .setApnsProduction(false)

                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录

                        .setSendno(1)

                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒

                        .setTimeToLive(86400)

                        .build()

                )

                .build();

    }

 

    private static PushPayload buildPushObject_all_registrationId_alertWithTitle(String registrationId,String notification_title,Map<String, String> extras) {

 

        System.out.println("----------buildPushObject_all_all_alert");

        //创建一个IosAlert对象，可指定APNs的alert、title等字段

        //IosAlert iosAlert =  IosAlert.newBuilder().setTitleAndBody("title", "alert body").build();

 

        return PushPayload.newBuilder()

                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台

                .setPlatform(Platform.all())

                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id

                .setAudience(Audience.registrationId(registrationId))

                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发

                /*.setNotification(Notification.newBuilder()

                        //指定当前推送的android通知

                        .addPlatformNotification(AndroidNotification.newBuilder()

 

                                .setAlert(notification_title)

                                .setTitle(notification_title)

                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）

                                .addExtras(extras)

                                

                                .build())*/
                        

                        //指定当前推送的iOS通知

                        /*.addPlatformNotification(IosNotification.newBuilder()

                                //传一个IosAlert对象，指定apns title、title、subtitle等

                                .setAlert(notification_title)

                                //直接传alert

                                //此项是指定此推送的badge自动加1

                                .incrBadge(1)

                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，

                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音

                                .setSound("sound.caf")

                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）

                                .addExtras(extras)

                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification

                                //取消此注释，消息推送时ios将无法在锁屏情况接收

                                .setContentAvailable(true)

 

                                .build())*/

 

 

                        //.build())

                //自定义处理
                .setMessage(Message.newBuilder()
                        .setMsgContent(notification_title)
                        .setTitle(notification_title)
                        .addExtra("type","9")
                        .build())

                .setOptions(Options.newBuilder()

                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义

                        .setApnsProduction(false)

                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录

                        .setSendno(1)

                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天；

                        .setTimeToLive(86400)

 

                        .build())

 

                .build();

  

    }

 

    private static PushPayload buildPushObject_android_all_alertWithTitle(String notification_title, String msg_title, String msg_content, Map<String, String> extras) {

    

        return PushPayload.newBuilder()

                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台

                .setPlatform(Platform.android())

                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id

                .setAudience(Audience.all())
                

                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发

                .setNotification(Notification.newBuilder()

                        //指定当前推送的android通知
                		
                        .addPlatformNotification(AndroidNotification.newBuilder()

                                .setAlert(notification_title)

                                .setTitle(notification_title)
                                

                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）

                               .addExtras(extras)
                               

                                .build())

                        .build()

                )
                .setOptions(Options.newBuilder()

                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义

                        .setApnsProduction(false)


                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录

                        .setSendno(1)

                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒

                        .setTimeToLive(86400)

                        .build())

                .build();

    }
  //方法一:利用ApacheHttpClient代替JMessageClient进行用户的注册
  	/*public static void regpp(String username,String password) {
  		JMessageClient client = new JMessageClient(appkey, masterSecret);
  		String authCode = ServiceHelper.getBasicAuthorization(appkey, masterSecret);
  		ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null, ClientConfig.getInstance());
  		client.setHttpClient(httpClient);
  		try {
              List<RegisterInfo> users = new ArrayList<RegisterInfo>();
              RegisterInfo user = RegisterInfo.newBuilder()
                      .setUsername(username)
                      .setPassword(password)
                      .build();
              users.add(user);          
              RegisterInfo[] regUsers = new RegisterInfo[users.size()];
   
              String res = client.registerUsers(users.toArray(regUsers));
             
              //System.out.println(res);
          } catch (APIConnectionException e) {
        	  System.out.println("连接错误。请稍后重试。 ");
          } catch (APIRequestException e) {
        	  System.out.println("JPush服务器的错误响应。请检查并改正。");
        	  System.out.println("网络状态: " + e.getStatus());
        	  System.out.println("错误信息: " + e.getMessage());
          }
  	}*/

}
