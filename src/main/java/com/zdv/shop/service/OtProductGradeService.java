package com.zdv.shop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.common.utils.UploadUtil;
import com.zdv.shop.mapper.OtProductGradeMapper;
import com.zdv.shop.model.OtProductGrade;
import com.zdv.shop.model.UtOrder;
import com.zdv.shop.model.UtOrderItem;
import com.zdv.shop.model.UtUsers;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class OtProductGradeService extends AbstratService<OtProductGrade> {

    @Autowired
    private OtProductGradeMapper productGradeMapper;
    @Autowired
    private UploadUtil uploadUtil;
    @Autowired
    private UtOrderService orderService;
    @Autowired
    private UtFinancelogService financelogService;


    public AjaxResult uploadImg(MultipartFile[] files, String docBase, String path, String[] uorderitemid,String[] productgradeids) {
        try {

            for (int i = 0; i < files.length; i++) {

                String url = uploadImg(files[i], docBase, path);
                OtProductGrade otProductGrade = new OtProductGrade();
                otProductGrade.setUorderitemid(uorderitemid[i]);
                //可能不止一条啊
                List<OtProductGrade> list = productGradeMapper.queryProductGrade(otProductGrade);

                otProductGrade.setProductgradeid(list.get(0).getProductgradeid());
                otProductGrade.setImgpic(url);
                productGradeMapper.updateByPrimaryKeySelective(otProductGrade);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"失败");
        }
        return new AjaxResult(1, "评论成功");


    }

    public String uploadImg(MultipartFile file, String docBase, String path) {
        String filepath = "";
        String nowName = uploadUtil.getNewName(".jpg");
        final ByteArrayOutputStream srcImageData = new ByteArrayOutputStream();
        try {
            IOUtils.copy(file.getInputStream(), srcImageData);
            // 上传图片
            FileUtils.copyInputStreamToFile(new ByteArrayInputStream(srcImageData.toByteArray()), new File(uploadUtil.getFilePath(docBase, nowName)));
        } catch (IOException e) {
            return filepath;
        }
        filepath = uploadUtil.getFilePath(path, nowName);
        filepath = filepath.replaceAll("\\\\", "/");

        return filepath;
    }

    public AjaxResult addProductGradeList(List<OtProductGrade> gradelist, String uorderid, UtUsers users) {
        String result = null;
        try {
            for (OtProductGrade otProductGrade : gradelist) {
                int i = productGradeMapper.addProductGrade(otProductGrade);
                OtProductGrade productGrade = new OtProductGrade();
                productGrade.setUorderitemid(otProductGrade.getUorderitemid());
                //订单的第一次评论
                int count = productGradeMapper.selectCount(productGrade);
                if (count == 1) {
//                    financelogService.shareProfit(users, otProductGrade.getUorderitemid());
                }

            }
            UtOrder utOrder = new UtOrder();
            utOrder.setUorderid(uorderid);
            utOrder.setUeflag("04");
            //更新订单状态
             orderService.updateOrder(utOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0, "失败");
        }

        return new AjaxResult(1,"成功");

    }
    
    public PageInfo<OtProductGrade> getCommenByUorderitemid(String ucomproductid,Integer currentPage,Integer pageSize){
    	PageHelper.startPage(currentPage, pageSize);
    	List<OtProductGrade> gradeList =  productGradeMapper.getCommenByUorderitemid(ucomproductid);
    	PageInfo<OtProductGrade> pageInfo = new PageInfo<>(gradeList);
    	return pageInfo;
    }

	public PageInfo<OtProductGrade> getCommenByUorderitemid(String ucomproductid, String type,Integer currentPage,Integer pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<OtProductGrade> gradeList = productGradeMapper.getCommenByUorderitemidAndType(ucomproductid,type);
		PageInfo<OtProductGrade> pageInfo = new PageInfo<>(gradeList);
		 return pageInfo;
	}
	
	public Integer getGradeToTalByType(String ucomproductid, String type) {
		return productGradeMapper.getGradeToTalByType(ucomproductid, type);
	}
}
