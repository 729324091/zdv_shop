package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.OtCompToProduct;
import com.zdv.shop.model.po.OtCompToProductPO;
import com.zdv.shop.model.vo.H5GoodsListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 销售商关联产品表
 * @author LBY
 * @date: 2018年12月7日
 */
public interface OtCompToProductMapper extends MyMapper<OtCompToProduct> {

	List<OtCompToProduct> selectList(OtCompToProduct otCompToProduct);
	
	/**
	 * 查询推荐商品列表
	 * @author LBY
	 * @data 2019年2月22日
	 * @return
	 */
	List<Map<String, Object>> selectRecommendGoodsList(H5GoodsListVO vo);
	
	/**
	 * 查询热销产品列表
	 * @author LBY
	 * @data 2019年2月23日
	 * @return
	 */
	List<Map<String, Object>> selectHotClickList(H5GoodsListVO vo);
	
	/**
	 * 查询促销产品列表
	 * @author LBY
	 * @data 2019年2月23日
	 * @return
	 */
	List<Map<String, Object>> selectPromoteSalesGoodsList(H5GoodsListVO vo);
	
	/**
	 * 根据销售商ID查询产品列表
	 * @author LBY
	 * @data 2019年2月28日
	 * @param ucompid
	 * @return
	 */
	List<Map<String, Object>> selectGoodsListByCompid(@Param("ucompid")String ucompid);
	
	/**
	 * 查询商品详情
	 * @author LBY
	 * @data 2019年2月26日
	 * @param ucomproductid 
	 * @return
	 */
	Map<String, Object> selectGoodsByComproductid(@Param("ucomproductid")String ucomproductid);

//    List<Map<String,Object>> queryProductList(@Param("uproducttypeid") String uproducttypeid, @Param("ucompid") String ucompid, @Param("publicurl") String publicurl);

	List<OtCompToProductPO> queryProductListAndSort(@Param("uproducttypeid") String uproducttypeid, @Param("ucustomerid") String ucustomerid,@Param("publicurl") String publicurl,
			@Param("type")String type);

	List<OtCompToProductPO> queryProductListByUaliasAndSort(@Param("Ualias")String keywords,@Param("ucustomerid") String ucustomerid,@Param("publicurl") String publicurl, @Param("type")String type);

	OtCompToProduct queryByUcomproductid(String ucomproductid);

	List<Map<String,Object>> queryProductList(@Param("uproducttypeid") String uproducttypeid, @Param("ucustomerid")String ucustomerid);

    List<OtCompToProduct> queryNewestProduct(@Param("ucustomerid") String ucustomerid, @Param("num") Integer num, @Param("ucompid") String ucompid);

    List<OtCompToProduct> queryFreeProduct(@Param("ucustomerid") String customerid,@Param("type") String type);

    int updateStockById(OtCompToProduct newCompToProduct);

    List<OtCompToProduct> queryIntegralProduct(@Param("ucustomerid") String customerid,@Param("ucompid") String ucompid);
}
