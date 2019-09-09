package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.mapper.OtCompToProductMapper;
import com.zdv.shop.model.OtCompProductStock;
import com.zdv.shop.model.OtCompToProduct;
import com.zdv.shop.model.UtOrderItem;
import com.zdv.shop.model.po.OtCompToProductPO;
import com.zdv.shop.model.vo.H5GoodsListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 销售商关联产品
 * @author LBY
 * @date: 2018年12月7日
 */
@Service
public class OtCompToProductService extends AbstratService<OtCompToProduct> {
	@Value("${publicURL}") //在配置文件中
	private String publicurl;
	@Autowired
	private OtCompToProductMapper mapper;
	@Autowired
	private OtCompProductStockObjnameService compProductStockObjnameService;
	@Autowired
	private OtCompProductStockService compProductStockService;


	public PageAjax<OtCompToProduct> queryPage(PageAjax<OtCompToProduct> page, OtCompToProduct otCompToProduct) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		return new PageAjax<OtCompToProduct>(mapper.selectList(otCompToProduct));
	}
	
	/**
	 * 首页查询推荐商品
	 * @author LBY
	 * @data 2019年2月22日
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> queryRecommendGoodsPage(H5GoodsListVO vo) {
		try {
			PageMethod.startPage(vo.getPageNo(), vo.getPageSize(), false);
			List<Map<String, Object>> maps = mapper.selectRecommendGoodsList(vo);
			for (Map<String, Object> map : maps) {
				String ucomproductid = map.get("ucomproductid").toString();
				List<Map<String, Object>> list = compProductStockService.queryListByUcomproductid(ucomproductid);
				if (list != null && list.size() > 0) {
					Double minuprice = 0.00;
					Double minumarketprice = 0.00;
					Double minuvipprice = 0.00;
					for (Map<String, Object> objectMap : list) {

						Double uprice = (Double) objectMap.get("uprice");
						Double umarketprice = (Double) objectMap.get("umarketprice");
						Double uvipprice = (Double) objectMap.get("uvipprice");
						//最小销售价
						if (minuprice == 0) {
							minuprice = uprice;
						}else{
							if (minuprice > uprice) {
								minuprice = uprice;
							}
						}
						//最小市场价
						if (minumarketprice == 0) {
							minumarketprice = umarketprice;

						}else{
							if (minumarketprice > umarketprice) {
								minumarketprice = umarketprice;
							}
						}
						//最小vip价
						if (minuvipprice == 0) {
							minuvipprice = uvipprice;

						}else{
							if (minuvipprice > uvipprice) {
								minuvipprice = uvipprice;
							}
						}

					}
					map.put("uprice", minuprice);
					map.put("umarketprice", minumarketprice);
					map.put("uvipprice", minuvipprice);
				}
			}



			return maps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询热销产品列表
	 * @author LBY
	 * @data 2019年2月23日
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryHotClickGoodsPage(H5GoodsListVO vo) {
		try {
			PageMethod.startPage(vo.getPageNo(), vo.getPageSize(), false);
			return mapper.selectHotClickList(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询促销产品列表
	 * @author LBY
	 * @data 2019年2月23日
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryPromoteSalesGoodsPage(H5GoodsListVO vo) {
		try {
			PageMethod.startPage(vo.getPageNo(), vo.getPageSize(), false);
			List<Map<String, Object>> maps = mapper.selectPromoteSalesGoodsList(vo);
			for (Map<String, Object> map : maps) {
				String uhomepic = (String) map.get("uhomepic");
				//分割展示第一张
				String[] split = uhomepic.split("#");
				String s = split[0];
				map.put("uhomepic", s);
			}
			return maps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据销售商ID查询产品列表
	 * @author LBY
	 * @data 2019年2月28日
	 * @param ucompid
	 * @return
	 */
	public List<Map<String, Object>> queryGoodsListByCompid(String ucompid) {
		List<Map<String, Object>> maps = mapper.selectGoodsListByCompid(ucompid);
		for (Map<String, Object> map : maps) {
			String uhomepic = (String) map.get("uhomepic");
			//分割展示第一张
			String[] split = uhomepic.split("#");
			String s = split[0];
			map.put("uhomepic", s);
		}
		return maps;
	}
	
	/**
	 * 查询商品详情
	 * @author LBY
	 * @data 2019年2月26日
	 * @param ucomproductid
	 * @return
	 */
	public Map<String, Object> queryGoodsByComproductid(String ucomproductid) {

		return mapper.selectGoodsByComproductid(ucomproductid);
	}

	/*public List<Map<String, Object>> queryProductList(String uproducttypeid) {
		String ucompid = null;//伪

		return mapper.queryProductList(uproducttypeid,ucompid,publicurl);
	}*/

	public List<OtCompToProductPO> queryProductListAndSort(String uproducttypeid, String type, String ucustomerid) {
		if(com.zdv.shop.common.Constant.Ubrowsepoint.equals(type)){
			type = "Ubrowsepoint";
		}else if(com.zdv.shop.common.Constant.Usalesnum.equals(type)){
			type = "Usalesnum";
		}else{
			type=null;
		}
		return mapper.queryProductListAndSort(uproducttypeid,ucustomerid,publicurl,type);
	}

	public List<OtCompToProductPO> queryProductListByUaliasAndSort(String keywords, String type, String ucustomerid) {
				if(com.zdv.shop.common.Constant.Ubrowsepoint.equals(type)){
					type = "Ubrowsepoint";
				}else if(com.zdv.shop.common.Constant.Usalesnum.equals(type)){
					type = "Usalesnum";
				}else{
					type="Uprice";
				}
		return mapper.queryProductListByUaliasAndSort(keywords, ucustomerid, publicurl, type);
	}

	public void queryStockAndPrice( String id,String[] attr, String number, Map<String, Object> result) {
			//查询出库存和详情
		Map<String, Object> product =mapper.selectGoodsByComproductid(id);
		result.put("upromotesales", product.get("upromotesales"));
		List<Map<String, Object>> list = new ArrayList<>();
		if (attr.length == 0) {
			// TODO Auto-generated method stub
			double uprice = (double) product.get("uprice");
			double uvipprice = (double) product.get("uvipprice");
			//价格
			String upricenum = "销售价:￥ "+(uprice * Integer.parseInt(number))+"元";
			String uvipprices = "会员价:￥ "+(uvipprice * Integer.parseInt(number))+"元";
			//库存
			result.put("product_number","库存:"+ product.get("ustock"));
			result.put("uprice", upricenum);
			result.put("uvipprice", uvipprices);
		} else if (attr.length == 1) {
			String[] split = attr[0].split(":");
			String uobjname = split[0];
			String uobjvalue = split[1];
			list = compProductStockService.queryByObjnamevalue(id,uobjname,uobjvalue);
		} else if (attr.length == 2) {
			String[] split = attr[0].split(":");
			String uobjname = split[0];
			String uobjvalue = split[1];
			String[] split1 = attr[1].split(":");
			String uobjname1 = split1[0];
			String uobjvalue1 = split1[1];
			list = compProductStockService.queryByObjnamevalues(id,uobjname,uobjvalue,uobjname1,uobjvalue1);

		}
		if (list.size() > 0) {

			result.put("product_number", "库存:" + list.get(0).get("ustock"));
			Double uprice = (Double) list.get(0).get("uprice");
			Double uvipprice = (Double) list.get(0).get("uvipprice");
			String upricenum = "销售价:￥ "+(uprice * Integer.parseInt(number))+"元";
			String uvipprices = "会员价:￥ "+(uvipprice * Integer.parseInt(number))+"元";
			result.put("uprice",upricenum);
			result.put("uvipprice", uvipprices);
		}
	}


	public OtCompToProduct queryByUcomproductid(String ucomproductid) {
		return mapper.queryByUcomproductid(ucomproductid);
	}

	/**
	 * 查询上架商品
	 * @param uproducttypeid
	 * @param ucustomerid
	 * @return
	 */
    public List<Map<String,Object>> queryProductList(String uproducttypeid, String ucustomerid) {

        return mapper.queryProductList(uproducttypeid,ucustomerid);

    }

	public List<OtCompToProduct> queryNewestProduct(String ucustomerid,Integer num,String ucompid) {
		List<OtCompToProduct> list = mapper.queryNewestProduct(ucustomerid, num,ucompid);
		for (OtCompToProduct otCompToProduct : list) {
			getPrice(otCompToProduct);
			String uhomepic = otCompToProduct.getUhomepic();
			if (StringUtils.StringisNotEmpty(uhomepic)) {
				String[] split = uhomepic.split("#");
				otCompToProduct.setUhomepic(split[0]);
			}

		}
		return list;
	}

	//获取最小价格
	public void getPrice(OtCompToProduct compToProduct) {
		List<Map<String, Object>> list = compProductStockService.queryListByUcomproductid(compToProduct.getUcomproductid());
		if (list != null && list.size() > 0) {
			Double minuprice = 0.00;
			Double minumarketprice = 0.00;
			Double minuvipprice = 0.00;
			for (Map<String, Object> objectMap : list) {

				Double uprice = (Double) objectMap.get("uprice");
				Double umarketprice = (Double) objectMap.get("umarketprice");
				Double uvipprice = (Double) objectMap.get("uvipprice");
				//最小销售价
				if (minuprice == 0) {
					minuprice = uprice;
				}else{
					if (minuprice > uprice) {
						minuprice = uprice;
					}
				}
				//最小市场价
				if (minumarketprice == 0) {
					minumarketprice = umarketprice;

				}else{
					if (minumarketprice > umarketprice) {
						minumarketprice = umarketprice;
					}
				}
				//最小vip价
				if (minuvipprice == 0) {
					minuvipprice = uvipprice;

				}else{
					if (minuvipprice > uvipprice) {
						minuvipprice = uvipprice;
					}
				}

			}
			compToProduct.setUprice(new BigDecimal(minuprice));
			compToProduct.setUmarketprice(new BigDecimal(minumarketprice));
			compToProduct.setUvipprice(new BigDecimal(minuvipprice));
			/*map.put("uprice", minuprice);
			map.put("umarketprice", minumarketprice);
			map.put("uvipprice", minuvipprice);*/
		}
	}
	public List<Map<String, Object>> searchCompToProduct(String keyword, String customerid) {

		return new ArrayList<>();
	}

	public List<OtCompToProduct> queryFreeProduct(String customerid, String type) {
		List<OtCompToProduct> plist = mapper.queryFreeProduct(customerid, type);
		for (OtCompToProduct otCompToProduct : plist) {

			String ucomproductid = otCompToProduct.getUcomproductid();
			List<Map<String, Object>> list = compProductStockService.queryListByUcomproductid(ucomproductid);
			if (list != null && list.size() > 0) {
				Double minuprice = 0.00;
				Double minumarketprice = 0.00;
				Double minuvipprice = 0.00;
				for (Map<String, Object> objectMap : list) {

					Double uprice = (Double) objectMap.get("uprice");
					Double umarketprice = (Double) objectMap.get("umarketprice");
					Double uvipprice = (Double) objectMap.get("uvipprice");
					//最小销售价
					if (minuprice == 0) {
						minuprice = uprice;
					}else{
						if (minuprice > uprice) {
							minuprice = uprice;
						}
					}
					//最小市场价
					if (minumarketprice == 0) {
						minumarketprice = umarketprice;

					}else{
						if (minumarketprice > umarketprice) {
							minumarketprice = umarketprice;
						}
					}
					//最小vip价
					if (minuvipprice == 0) {
						minuvipprice = uvipprice;

					}else{
						if (minuvipprice > uvipprice) {
							minuvipprice = uvipprice;
						}
					}

				}
				otCompToProduct.setUprice(new BigDecimal(minuprice));
				otCompToProduct.setUmarketprice(new BigDecimal(minumarketprice));
				otCompToProduct.setUvipprice(new BigDecimal(minuvipprice));

			}
		}


		return plist;

	}

	public int updateStock(UtOrderItem orderItem) {
	//获得销售商品信息用于更新
		OtCompToProduct otCompToProduct = mapper.selectByPrimaryKey(orderItem.getUcomproductid());
		OtCompToProduct newCompToProduct = new OtCompToProduct();
		newCompToProduct.setUcomproductid(otCompToProduct.getUcomproductid());
		//如果存在规格
		OtCompProductStock compProductStock = null;
		//下单修改商品的销量和库存
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(orderItem.getUproductstockid())) {
			//如果存在规格
			compProductStock = compProductStockService.queryByID(orderItem.getUproductstockid());
			//更新库存
			compProductStock.setUstock(Integer.parseInt(compProductStock.getUstock()) - Integer.parseInt(orderItem.getUproductnum())+"");

		}else{
			//不存在规格
			Integer stock = otCompToProduct.getUstock() - Integer.parseInt(orderItem.getUproductnum());
//                otCompToProduct.setUstock(stock);
			newCompToProduct.setUstock(stock);
		}
		int salenum =otCompToProduct.getUsalesnum()+ Integer.parseInt(orderItem.getUproductnum());
		//设置销量
		newCompToProduct.setUsalesnum(salenum);

		int i  = mapper.updateStockById(newCompToProduct);
		if (compProductStock != null) {
			compProductStockService.update(compProductStock);
		}

		return i;


	}

    public List<OtCompToProduct> queryIntegralProduct(String customerid, String ucompid) {
		List<OtCompToProduct> plist = mapper.queryIntegralProduct(customerid, ucompid);

		getlistprice(plist);


		return plist;

    }


	/**
	 * 获取商品最大价格最小价格等
	 * @param plist
	 */
    public void getlistprice(List<OtCompToProduct> plist) {
		for (OtCompToProduct otCompToProduct : plist) {

			String ucomproductid = otCompToProduct.getUcomproductid();
			List<Map<String, Object>> list = compProductStockService.queryListByUcomproductid(ucomproductid);
			if (list != null && list.size() > 0) {
				Double minuprice = 0.00;
				Double minumarketprice = 0.00;
				Double minuvipprice = 0.00;
				for (Map<String, Object> objectMap : list) {

					Double uprice = (Double) objectMap.get("uprice");
					Double umarketprice = (Double) objectMap.get("umarketprice");
					Double uvipprice = (Double) objectMap.get("uvipprice");
					//最小销售价
					if (minuprice == 0) {
						minuprice = uprice;
					}else{
						if (minuprice > uprice) {
							minuprice = uprice;
						}
					}
					//最小市场价
					if (minumarketprice == 0) {
						minumarketprice = umarketprice;

					}else{
						if (minumarketprice > umarketprice) {
							minumarketprice = umarketprice;
						}
					}
					//最小vip价
					if (minuvipprice == 0) {
						minuvipprice = uvipprice;

					}else{
						if (minuvipprice > uvipprice) {
							minuvipprice = uvipprice;
						}
					}

				}
				otCompToProduct.setUprice(new BigDecimal(minuprice));
				otCompToProduct.setUmarketprice(new BigDecimal(minumarketprice));
				otCompToProduct.setUvipprice(new BigDecimal(minuvipprice));

			}
		}


	}
}
