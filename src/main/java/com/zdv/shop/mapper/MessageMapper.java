package com.zdv.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.DvDevice;
import com.zdv.shop.model.Message;
import com.zdv.shop.model.po.MessageAndSendNameVo;


public interface MessageMapper  extends MyMapper<Message> {
	List<MessageAndSendNameVo> getMessageByuserId(@Param("userid")String userid);
	
	Integer getUnreadMessageByuserId(@Param("userid")String userid);
	
	int readmessagemessageid(@Param("messageid")String messageid);
}
