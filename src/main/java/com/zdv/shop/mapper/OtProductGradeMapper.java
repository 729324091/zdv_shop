package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtProductGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OtProductGradeMapper extends MyMapper<OtProductGrade> {

    int addProductGradeList(List<OtProductGrade> gradelist);

	List<OtProductGrade> getCommenByUorderitemid(@Param("ucomproductid")String ucomproductid);

	List<OtProductGrade> getCommenByUorderitemidAndType(@Param("ucomproductid")String ucomproductid,@Param("flag") String flag);
	
	Integer getGradeToTalByType(@Param("ucomproductid")String ucomproductid,@Param("flag") String flag);

    List<OtProductGrade> queryProductGrade(OtProductGrade otProductGrade);

	int addProductGrade(OtProductGrade otProductGrade);
}
