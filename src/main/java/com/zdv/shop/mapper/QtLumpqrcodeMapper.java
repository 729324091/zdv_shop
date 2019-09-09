package com.zdv.shop.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.QtLumpqrcode;

public interface QtLumpqrcodeMapper extends MyMapper<QtLumpqrcode>{

	QtLumpqrcode selLumpqrcodeById(@Param("udistributorid")String udistributorid,@Param("uproductid")String uproductid);

	List<QtLumpqrcode> selLumpqrcodeVague(@Param("udistributorid")String udistributorid,@Param("selCondition")String selCondition);
}
