package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtBankinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UtBankinfoMapper extends MyMapper<UtBankinfo> {

	List<UtBankinfo> getBankCardList(@Param("uUserId")String uuserid);

	int deleteCard(@Param("uBankId")String ubankid);

	int saveBankinfo(UtBankinfo bankInfo);

	UtBankinfo getBankCardByBankid(String ubankid);
}
