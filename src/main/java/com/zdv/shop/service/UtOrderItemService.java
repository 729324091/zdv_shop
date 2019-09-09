package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.mapper.UtOrderItemMapper;
import com.zdv.shop.model.UtOrderItem;
import com.zdv.shop.model.UtUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UtOrderItemService extends AbstratService<UtOrderItem> {

    @Autowired
    private UtOrderItemMapper utOrderItemMapper;

    public PageAjax<UtOrderItem> queryOrderItemPage(PageAjax<UtOrderItem> page, UtOrderItem utOrderItem) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());

        List<UtOrderItem> list = utOrderItemMapper.queryOrderItemPage(utOrderItem);

        return new PageAjax<UtOrderItem>(list);

    }
    
    /**
     * 查询购物车
     * @author LBY
     * @data 2019年3月1日
     * @param uuserid
     * @return
     */
    public List<Map<String, Object>> queryCartOrderItem(String uuserid) {
        List<Map<String, Object>> list = utOrderItemMapper.selectCartOrderItem(uuserid);
        for (Map<String, Object> stringObjectMap : list) {
                String uhomepic = (String) stringObjectMap.get("uhomepic");
            if (uhomepic != null) {
                if (uhomepic.contains("#")) {
                    String[] split = uhomepic.split("#");
                    stringObjectMap.put("uhomepic", split[0]);
                }
            }
        }
        return list;
    }

    public List<Map<String, Object>> queryCartOrderItem2(String uuserid) {
        List<UtOrderItem> list = utOrderItemMapper.selectCartOrderItemList(uuserid);

        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        for (UtOrderItem orderItem : list) {
            String uhomepic = orderItem.getUhomepic();
            if (StringUtils.StringisNotEmpty(uhomepic)) {
                if (uhomepic.contains("#")) {
                    String[] split = uhomepic.split("#");
                    orderItem.setUhomepic(split[0]);
                }
            }
            String ucompid = orderItem.getUcompid();
            //如果map中已经存在该商店商品
            if (map.containsKey(ucompid)) {
                List<UtOrderItem> o = (List<UtOrderItem>) map.get(ucompid);
                o.add(orderItem);
                map.put(ucompid, o);
            }else{
                List<UtOrderItem> items = new ArrayList<>();
                map1.put(ucompid, orderItem.getUcompname());
                items.add(orderItem);
                map.put(ucompid, items);
            }
        }
        mapList.add(map);
        mapList.add(map1);
        return mapList;
    }

    /**
     * 通过id查询订单项  uorderitemids // 0,1,2,3,4,5
     * @param uorderitemids
     * @return
     */
    public List<UtOrderItem> queryOrderItem(String uorderitemids) {
        return utOrderItemMapper.selectOrderItem(uorderitemids);

    }

    public int updateOrderItemList(List<UtOrderItem> itemList) {
        return  utOrderItemMapper.updateOrderItemList(itemList);

    }

    /**
     * 查询购物车中的list
     * @param utOrderItem
     * @return
     */
    public List<UtOrderItem> queryCartItemList(UtOrderItem utOrderItem) {
        return utOrderItemMapper.queryCartItemList(utOrderItem);


    }

    public List<UtOrderItem> queryOrderItemList(UtOrderItem orderItem) {
        return utOrderItemMapper.queryOrderItemList(orderItem);

    }

    public AjaxResult delByUorderitemids(String[] uorderitemid) {
        int i = 0;
        for (String s : uorderitemid) {
            i += utOrderItemMapper.deleteByPrimaryKey(s);
        }
        if (i == uorderitemid.length) {
            return new AjaxResult(1, "删除成功 ");
        }else{
            return new AjaxResult(0, "删除失败");
        }


    }

    public List<Map<String, Object>> queryOrderItemListByUorderitemids(String[] uorderitemids) {
        String uorderitemid = "";

        for (int i = 0; i < uorderitemids.length; i++) {
            if (i == uorderitemids.length - 1) {
                uorderitemid += uorderitemids[i];
            }else{
                uorderitemid += uorderitemids[i] + ",";
            }

        }

        return utOrderItemMapper.queryOrderItemListByUorderitemids(uorderitemid);


    }

    public int delByUorderid(String uorderid) {

       return utOrderItemMapper.delByUorderid(uorderid);

    }

    public List<UtOrderItem> listOrderitemByUorderid(String uorderid) {
        return utOrderItemMapper.listOrderitemByUorderid(uorderid);

    }



}
