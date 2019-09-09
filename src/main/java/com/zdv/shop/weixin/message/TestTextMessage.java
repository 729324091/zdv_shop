package com.zdv.shop.weixin.message;

public class TestTextMessage  extends TestBaseMessage{
	//文本
    private TestText text;
    //否     表示是否是保密消息，0表示否，1表示是，默认0
    private int safe;
	public TestText getText() {
		return text;
	}
	public int getSafe() {
		return safe;
	}
	public void setText(TestText text) {
		this.text = text;
	}
	public void setSafe(int safe) {
		this.safe = safe;
	}
}
