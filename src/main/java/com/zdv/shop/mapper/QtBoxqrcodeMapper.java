package com.zdv.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.QtBoxqrcode;

public interface QtBoxqrcodeMapper extends MyMapper<QtBoxqrcode>{

	List<QtBoxqrcode> selBoxqrcodeById(@Param("ulumpqrcodeid")String ulumpqrcodeid,@Param("udistributorid")String udistributorid,@Param("uproductid")String uproductid);
	
}