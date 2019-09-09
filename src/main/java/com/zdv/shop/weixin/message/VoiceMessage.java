package com.zdv.shop.weixin.message;


/**
 * 音频消息
 * 
 */
public class VoiceMessage extends BaseMessage{
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
	
}
