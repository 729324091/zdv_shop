package com.zdv.shop.service;

import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.mapper.UtBankinfoMapper;
import com.zdv.shop.model.UtBankinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UtBankinfoService extends AbstratService<UtBankinfo> {
	@Autowired
	private UtBankinfoMapper bankMapper;

	public List<UtBankinfo> getBankCardList(String uuserid) {
		List<UtBankinfo> target = new ArrayList<UtBankinfo>();
		target = bankMapper.getBankCardList(uuserid);
		return target;
	}
	public boolean deleteCard(String ubankid) {
		// TODO Auto-generated method stub
		int row = bankMapper.deleteCard(ubankid);
		if(row>0) {
			return true;
		}
		return false;
	}

	public AjaxResult saveBankinfo(UtBankinfo bankInfo) {
		String result = "添加失败";

		try {
			int i = bankMapper.saveBankinfo(bankInfo);
			if (i < 1) {
				result = "添加失败";
				return new AjaxResult(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(result);
		}

		return new AjaxResult();
	}

	public UtBankinfo getBankCardByBankid(String ubankid) {

		return bankMapper.getBankCardByBankid(ubankid);
	}
}
