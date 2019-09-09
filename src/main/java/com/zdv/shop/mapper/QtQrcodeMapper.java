package com.zdv.shop.mapper;



import org.apache.ibatis.annotations.Param;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.QtQrcode;

public interface QtQrcodeMapper extends MyMapper<QtQrcode>{

	QtQrcode selQrcodeById(@Param("udistributorid")String udistributorid,@Param("uproductid")String uproductid);
}
