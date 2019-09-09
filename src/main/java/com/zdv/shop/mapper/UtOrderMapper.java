package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtOrder;
import com.zdv.shop.model.UtOrderItem;
import com.zdv.shop.model.vo.SalesOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UtOrderMapper extends MyMapper<UtOrder> {


    /**
     * 根据条件查询order集合
     * @param utOrder
     * @param starttime
     * @param endtime
     * @return
     */
    List<UtOrder> orderList(@Param("utOrder")UtOrder utOrder,@Param("starttime") String starttime,@Param("endtime")String endtime);

    /**
     * APP查询销售订单
     * @author LBY
     * @data 2019年2月16日
     * @param ucompid
     * @return
     */
    List<SalesOrderVO> selectSalesOrderByCompid(@Param("ucompid")String ucompid);
    
    /**
     * APP查询销售订单详情信息
     * @author LBY
     * @data 2019年2月18日
     * @param uorderid
     * @return
     */
    SalesOrderVO selectSalesOrderDetailsByOrderid(@Param("uorderid")String uorderid);
    //销售商产品销售排行榜
    List<UtOrderItem> querycompproductSales(UtOrder order);
    //销售商产品销售排行榜计算总数
    UtOrderItem querycompproductSalesCount(UtOrder order);
    //销售商根据日期获得日或者月、年销售和进货产品统计数据
    List<UtOrderItem> querycompproductSalesByDate(UtOrder order);

    int addOrderList(List<UtOrder> orderList);

    /**
     * 查询订单列表
     * @return
     * @param order
     */
    List<Map<String,Object>> queryOrderList(UtOrder order);

    int updateOrder(UtOrder utOrder);

    int delOrder(@Param("uorderid") String uorderid,@Param("uuserid") String uuserid);

    void addOrder(UtOrder utOrder);

    Double queryConsumption(UtOrder utOrder);

    String selectVerificationcode(@Param("uorderid") String uorderid,@Param("uuserid") String uuserid);
}
