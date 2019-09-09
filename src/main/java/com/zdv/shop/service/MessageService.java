package com.zdv.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdv.shop.mapper.MessageMapper;
import com.zdv.shop.model.Message;
import com.zdv.shop.model.po.MessageAndSendNameVo;

@Service
public class MessageService extends AbstratService<Message> {
	@Autowired
	private MessageMapper messageMapper;
	
	public List<MessageAndSendNameVo> getMessageByuserId(String userid) {
		return messageMapper.getMessageByuserId(userid);
	}

	public boolean readmessagemessageid(String messageid) {
		// TODO Auto-generated method stub
		int readmessagemessageid = messageMapper.readmessagemessageid(messageid);
		if(readmessagemessageid>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public Integer getUnreadMessageByuserId(String userid) {
		return messageMapper.getUnreadMessageByuserId(userid);
		
	}
}
