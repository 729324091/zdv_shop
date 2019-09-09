package com.zdv.shop.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.mapper.OtDistributorToProductMapper;
import com.zdv.shop.model.OtDistributorProductStockObjname;
import com.zdv.shop.model.OtDistributorToProduct;
import com.zdv.shop.model.OtProducttype;
import com.zdv.shop.model.QtProductunit;
import com.zdv.shop.model.vo.AppQueryStockArgVo;
import com.zdv.shop.model.vo.AppQueryStockVo;
import com.zdv.shop.model.vo.AppStockUnitVo;

/**
 * 经销商关联产品
 * @author LBY
 * @date: 2018年12月7日
 */
@Service
public class OtDistributorToProductService extends AbstratService<OtDistributorToProduct> {

	@Autowired
	private OtDistributorToProductMapper mapper;
	
	@Autowired
	private QtProductunitService qtProductunitService;
	@Autowired
	private OtDistributorProductStockObjnameService otDistributorProductStockObjnameService;
	
	public PageAjax<OtDistributorToProduct> queryPage(PageAjax<OtDistributorToProduct> page, OtDistributorToProduct distributorToProduct) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		return new PageAjax<OtDistributorToProduct>(mapper.selectList(distributorToProduct));
	}
	
	/**
	 * APP查询库存信息
	 * @author LBY
	 * @date 2019年1月3日
	 * @param page
	 * @param distributorid 经销商ID
	 * @param producttypeid 产品类型ID
	 * @param keywords 关键字，根据产品名称模糊查询
	 * @param stock 库存数量，查询小于等于该指数的记录
	 * @return
	 */
	public List<AppQueryStockVo> appQueryStock(PageAjax<AppQueryStockVo> page, AppQueryStockArgVo vo) {
		if (page != null)
			PageMethod.startPage(page.getPageNo(), page.getPageSize(), false);
		List<AppQueryStockVo> stockList = mapper.appQueryStock(vo);
		return this.setUnit(stockList);
	}
	
	/**
	 * 设置单位信息
	 * @author LBY
	 * @data: 2019年1月7日
	 * @param stockList
	 * @return
	 */
	private List<AppQueryStockVo> setUnit(List<AppQueryStockVo> stockList) {
		if (stockList != null && stockList.size() > 0) {
			for (AppQueryStockVo appQueryStockVo : stockList) {
				List<AppStockUnitVo> unitVoList = new ArrayList<AppStockUnitVo>();
				List<QtProductunit> unitList = new ArrayList<QtProductunit>();
				String proStock = appQueryStockVo.getUstock();	// 产品库存
				String price = appQueryStockVo.getUprice();		// 产品价格
				QtProductunit unit = qtProductunitService.queryFirstGrade(appQueryStockVo.getUunitid());	// 查询一级单位信息
				if (unit != null) {
					unitList.add(unit);
					QtProductunit tempUnit = unit;
					// 循环查询出所有的单位
					while (true) {
						QtProductunit childUnit = qtProductunitService.queryChild(tempUnit.getUunitid());
						if (childUnit == null) {
							break;
						} else {
							unitList.add(childUnit);
							tempUnit = childUnit;
						}
					}
					
					// 计算各单位库存
					Integer stockTemp = Integer.parseInt(proStock);
					String priceTemp = price;
					
					for (int i = unitList.size() - 1; i >= 0; i--) {
						unitList.get(i).setPrice(priceTemp);
						if (unitList.get(i).getUcalernum() != null && unitList.get(i).getUcalernum() != 0) {
							unitList.get(i).setStock(stockTemp % unitList.get(i).getUcalernum());
							stockTemp = stockTemp / unitList.get(i).getUcalernum();
							priceTemp = new BigDecimal(priceTemp).multiply(BigDecimal.valueOf(unitList.get(i).getUcalernum())).toString();
						} else {
							unitList.get(i).setStock(0);
							stockTemp = 0;
							priceTemp = "0";
						}
					}
					
					// 添加到vo list中
					String childNameTemp = null;
					for (int i = 0; i < unitList.size(); i++) {
						AppStockUnitVo vo = new AppStockUnitVo();
						if (i + 1 < unitList.size()) {
							childNameTemp = unitList.get(i + 1).getUunitname();
							vo.setCalerNum(String.valueOf(unitList.get(i + 1).getUcalernum()));
						} else {
							childNameTemp = null;
						}
						
						vo.setUnitId(unitList.get(i).getUunitid());
						vo.setUnitName(unitList.get(i).getUunitname());
						vo.setChildName(childNameTemp);
						vo.setPrice(unitList.get(i).getPrice());
						vo.setStockNum(unitList.get(i).getStock());
						vo.setUcolor(unitList.get(i).getUcolor());
						
						unitVoList.add(vo);
					}
				}
				appQueryStockVo.setUnitList(unitVoList);
				if (unitVoList != null && unitVoList.size() > 0) {
					// appQueryStockVo.setUunit(unitVoList.get(unitVoList.size() - 1).getUnitName());
					//unitVoList.get(unitVoList.size() - 1).setCalerNum(appQueryStockVo.getUobjvalue());
					//unitVoList.get(unitVoList.size() - 1).setChildName(appQueryStockVo.getUobjname());
				}
				
				OtDistributorProductStockObjname arg = new OtDistributorProductStockObjname();
				arg.setUproductstockid(appQueryStockVo.getUproductstockid());
				appQueryStockVo.setUproductstockid(appQueryStockVo.getUproductstockid());
				appQueryStockVo.setUobjList(otDistributorProductStockObjnameService.queryList(arg));
			}
		}
		return stockList;	
	}
	
	/**
	 * APP查询库存页面中的产品类型
	 * @author LBY
	 * @date 2019年1月3日
	 * @param distributorid 经销商ID
	 * @return
	 */
	public List<OtProducttype> appQueryProductTypeList(String distributorid) {
		return mapper.appQueryProductTypeList(distributorid);
	}
	
	/**
	 * APP查询补货列表
	 * @author LBY
	 * @data: 2019年1月7日
	 * @param udistributorid
	 * @return
	 */
//	public List<AppQueryStockVo> appQueryReplenishList(PageAjax page, String udistributorid) {
//		if (page != null)
//			PageMethod.startPage(page.getPageNo(), page.getPageSize(), false);
//		return this.setUnit(mapper.appQueryReplenishList(udistributorid));
//	}
	//获得指定地区代理某个产品的终端代理商 通过产品ID和地区 终端代理商要ulevel标识
	public List<OtDistributorToProduct> queryDistributorByProductId(List<String> item,String uareaid,String ulevel){
		Map params = new HashMap();
		if(item.size()==0)
			params.put("ids", null);
		else
			params.put("ids", item);
        params.put("uareaid", uareaid);
        params.put("ulevel", ulevel);
		return mapper.queryDistributorByProductId(params);
	}
}
