package com.zdv.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zdv.shop.model.OtProductGrade;
import com.zdv.shop.model.UtUsers;
import com.zdv.shop.model.po.GradePO;
import com.zdv.shop.service.OtProductGradeService;
import com.zdv.shop.service.UtOrderItemService;
import com.zdv.shop.service.UtOrderService;
import com.zdv.shop.service.UtUserService;
import com.zdv.shop.weixinh5.util.DateUtil;

@RequestMapping("/h5/commen/")
@Controller
public class H5ProductCommenController {
	 @Autowired
	   private OtProductGradeService produService;
	 @Autowired
	 	private UtUserService userService;
	 @RequestMapping("/{ucomproductid}")
	 public String getCommenByUorderitemid(@PathVariable("ucomproductid") String ucomproductid,Model model,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="5") int pageSize){
		 PageInfo<OtProductGrade> commenByUorderitemid = produService.getCommenByUorderitemid(ucomproductid,page,pageSize);
		 this.CommenConditionUtil(commenByUorderitemid.getList(),model,ucomproductid);
		 //分页信息
		 model.addAttribute("pageInfo", commenByUorderitemid);
		return "/templates/h5/comment_list";
	 }
	 @RequestMapping("/type")
	 public String getCommenCondition(String ucomproductid,Model model,String type,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="5") int pageSize) {
		 PageInfo<OtProductGrade> commenByUorderitemid = produService.getCommenByUorderitemid(ucomproductid,type,page,pageSize);
		 
		 this.CommenConditionUtil(commenByUorderitemid.getList(),model,ucomproductid);
		 model.addAttribute("type", type);
		 //分页信息
		 model.addAttribute("pageInfo", commenByUorderitemid);
		return "/templates/h5/comment_list";
	 }
	 public void CommenConditionUtil(List<OtProductGrade> commenByUorderitemid,Model model,String ucomproductid) {
		 String isNull="";
		 //获取评论总数，
		 Integer gradeSum = produService.getGradeToTalByType(ucomproductid,null);
		 Integer goodGradeNum =  produService.getGradeToTalByType(ucomproductid,"1");
		 Integer middonGradeNum =  produService.getGradeToTalByType(ucomproductid,"0");
		 Integer badGradeNum =  produService.getGradeToTalByType(ucomproductid,"-1");
		 if(commenByUorderitemid!=null&&commenByUorderitemid.size()>0) {
		 List<GradePO> gradeList = new ArrayList<GradePO>();
		 //拿出订单项id,找到评价者
		 for(int i = 0;i<commenByUorderitemid.size();i++) {
			 GradePO grade = new GradePO();
			 grade.setContent(commenByUorderitemid.get(i).getContent());
			 grade.setIsShowName(commenByUorderitemid.get(i).getIsshowname());
			 grade.setServiceGrade(commenByUorderitemid.get(i).getServicegrade());
			 String oldcreatedate = commenByUorderitemid.get(i).getCreatedate();
			 String newcreatedate = DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", Long.parseLong(oldcreatedate));
			 grade.setDate(newcreatedate);
			 //订单项id
			 String uorderitemid = commenByUorderitemid.get(i).getUorderitemid();
			 String queryUnameByUorderitemid = userService.queryUnameByUorderitemid(uorderitemid);
			 grade.setUname(queryUnameByUorderitemid);
			 gradeList.add(grade);
			 
		 }
		 model.addAttribute("gradeContent",gradeList);
		 isNull="true";
		 }else {
			 isNull="false";
		 }
		 model.addAttribute("ucomproductid", ucomproductid);
		 model.addAttribute("isNull", isNull);
		 model.addAttribute("gradeSum", gradeSum);
		 model.addAttribute("goodGradeNum", goodGradeNum);
		 model.addAttribute("middonGradeNum", middonGradeNum);
		 model.addAttribute("badGradeNum", badGradeNum);
	 }
}
