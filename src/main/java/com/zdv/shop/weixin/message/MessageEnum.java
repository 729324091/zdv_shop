package com.zdv.shop.weixin.message;

/**
 * 
 * @Title:MessageEnum
 * @Description:消息类型枚举类
 * @author Administrator
 */
public enum MessageEnum {

	MSG_TEXT("text",1),
	MSG_IMAGE("image",2),
	MSG_VIDEO("video",3),
	MSG_VOICE("voice",4),
	MSG_MUSIC("music",5),
	MSG_NEWS("news",6),
	MSG_SHORTVIDEO("shortvideo",7),
	MSG_LOCATION("location",8),
	MSG_LINK("link",9),
	MSG_EVENT("event",10),
	
	EVENT_SUBSCRIBE("subscribe",101),
	EVENT_UNSUBSCRIBE("unsubscribe",102),
	EVENT_SCAN("scan",103),
	EVENT_LOCATION("location",104),
	EVENT_CLICK("click",105),
	EVENT_VIEW("view",106);
	
	private String name;
	private int value;
	
	
	private MessageEnum(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public static int getValue (String name){
		int t= 0;
		 for (MessageEnum enuma : MessageEnum.values()){
			 if (enuma.getName().equals(name)){
				 t = enuma.getValue();
			 }
		 }
		 return t;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
