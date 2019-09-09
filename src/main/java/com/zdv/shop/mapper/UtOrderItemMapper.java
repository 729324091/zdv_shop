package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UtOrderItemMapper extends MyMapper<UtOrderItem> {


    List<UtOrderItem> queryOrderItemPage(UtOrderItem utOrderItem);
    
    /**
     * 查询购物车
     * @author LBY
     * @data 2019年3月1日
     * @param uuserid
     * @return
     */
    List<Map<String, Object>> selectCartOrderItem(@Param("uuserid")String uuserid);
    List<UtOrderItem> selectCartOrderItemList(@Param("uuserid")String uuserid);

    /**
     * 通过id查询订单项  id // 0,1,2,3,4,5
     * @param uorderitemids
     * @return  List<UtOrderItem>
     */
    List<UtOrderItem> selectOrderItem(String uorderitemids);

    int updateOrderItemList(@Param("list") List<UtOrderItem> itemList);

    List<UtOrderItem> queryCartItemList(UtOrderItem utOrderItem);

    List<UtOrderItem> queryOrderItemList(UtOrderItem orderItem);

    /**
     * 通过id查询订单项  id // 0,1,2,3,4,5
     * @param uorderitemids
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryOrderItemListByUorderitemids(String uorderitemid);

    int delByUorderid(@Param("uorderid") String uorderid);

    List<UtOrderItem> listOrderitemByUorderid(@Param("uorderid") String uorderid);
    
}
