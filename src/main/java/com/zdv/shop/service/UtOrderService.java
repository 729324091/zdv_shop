package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.MD5Util;
import com.zdv.shop.common.utils.QRCodeUtil;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.mapper.UtOrderMapper;
import com.zdv.shop.model.*;
import com.zdv.shop.model.vo.SalesOrderVO;
import com.zdv.shop.model.vo.SalesProductDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UtOrderService extends AbstratService<UtOrder> {

    @Autowired
    private UtOrderMapper utOrderMapper;
    @Autowired
	private QtProductunitService qtProductunitService;
    @Autowired
    private UtOrderItemService orderItemService;
    @Autowired
    private OtCompToProductService compToProductService;
    @Autowired
    private UtFinancelogService financelogService;
    @Autowired
    private UtUserService userService;
    @Autowired
    private UtUserfreeproductService userfreeproductService;
    @Autowired
    private CtProfitService profitService;
    @Autowired
    private CtCompService compService;
    @Value("${publicURL}")
    private String publicurl;
    //存放核销的二维码
    @Value("${global.upload.docBase}")
    private String doBase;

    public PageAjax<UtOrder> queryOrderPage(PageAjax<UtOrder> page, UtOrder utOrder, String starttime, String endtime) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<UtOrder> list = utOrderMapper.orderList(utOrder,starttime,endtime);

        return new PageAjax<UtOrder>(list);
    }
    
    /**
     * APP查询销售订单
     * @author LBY
     * @data 2019年2月16日
     * @param page
     * @param ucompid
     * @return
     */
    public List<SalesOrderVO> querySalesOrderByCompidList(PageAjax<SalesOrderVO> page, String ucompid) {
    	if (page != null)
    		PageMethod.startPage(page.getPageNo(), page.getPageSize());
    	return utOrderMapper.selectSalesOrderByCompid(ucompid);
    }
    
    /**
     * APP查询销售订单详情
     * @author LBY
     * @data 2019年2月18日
     * @param uorderid
     * @return
     */
    public SalesOrderVO querySalesOrderDetailsByOrderid(PageAjax<SalesOrderVO> page, String uorderid) {
    	if (page != null)
    		PageMethod.startPage(page.getPageNo(), page.getPageSize());
    	SalesOrderVO order = utOrderMapper.selectSalesOrderDetailsByOrderid(uorderid);
    	if (order != null) {
    		if (order.getOrderItemList() != null && order.getOrderItemList().size() > 0) {
    			for (int i = 0; i < order.getOrderItemList().size(); i++) {
    				String unitId = ((SalesProductDetailsVO) order.getOrderItemList().get(i)).getUunitid();	// 单位ID
    				QtProductunit child = qtProductunitService.queryChild(unitId);
    				if (child != null) {
    					((SalesProductDetailsVO) order.getOrderItemList().get(i)).setUchildunitname(child.getUunitname());
    					((SalesProductDetailsVO) order.getOrderItemList().get(i)).setUcalerNum(child.getUcalernum());
    				}
    			}
    		}
    	}
    	return order;
    }
    //销售商产品销售排行榜
    public List<UtOrderItem> querycompproductSales(UtOrder order){
    	return utOrderMapper.querycompproductSales(order);
    }
    //销售商产品销售排行榜计算总数
    public UtOrderItem querycompproductSalesCount(UtOrder order){
    	return utOrderMapper.querycompproductSalesCount(order);
    }
    //销售商根据日期获得日或者月、年销售和进货产品统计数据
    public List<UtOrderItem> querycompproductSalesByDate(UtOrder order){
    	return utOrderMapper.querycompproductSalesByDate(order);
    }


    public AjaxResult addOrderList(List<UtOrder> orderList, List<UtOrderItem> itemList,List<UtUserfreeproduct> freeList) {
        try {
//            int ordernum = utOrderMapper.addOrderList(orderList);
            for (UtOrder utOrder : orderList) {
                utOrderMapper.addOrder(utOrder);

            }
            if (itemList != null && itemList.size() > 0) {
                for (UtOrderItem utOrderItem : itemList) {
                    orderItemService.updateByID(utOrderItem);
                    compToProductService.updateStock(utOrderItem);
                }
            }

            if (freeList != null && freeList.size() > 0) {
                for (UtUserfreeproduct userfreeproduct : freeList) {
                    userfreeproductService.saveUserfreeproduct(userfreeproduct);

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("操作失败");
        }
        return new AjaxResult(1, "操作成功");
    }

    public List<Map<String, Object>> queryOrderList(UtOrder order) {

        List<Map<String, Object>> list = utOrderMapper.queryOrderList(order);
        for (Map<String, Object> map : list) {
            String uorderid = map.get("uorderid").toString();
            UtOrderItem orderItem = new UtOrderItem();
            orderItem.setUorderid(uorderid);
            //获得该订单的订单项
            List<UtOrderItem> itemList = orderItemService.queryOrderItemList(orderItem);
            for (UtOrderItem utOrderItem : itemList) {
                String uhomepic = utOrderItem.getUhomepic();
                if (StringUtils.StringisNotEmpty(uhomepic)) {
                    //设置第一张图
                    utOrderItem.setUhomepic(uhomepic.split("#")[0]);
                }
                if (!StringUtils.StringisNotEmpty(utOrderItem.getUpromotesales())) {
                    utOrderItem.setUpromotesales("0");
                }
            }
            map.put("itemList", itemList);

        }


        return list;
    }



    public void queryCenterOrderCount(UtOrder utOrder, Model model) {
        utOrder.setUeflag("00");
        int nopay = utOrderMapper.selectCount(utOrder);
        model.addAttribute("nopay",nopay);
        //待收货订单数
        utOrder.setUeflag("01");
        int nosend = utOrderMapper.selectCount(utOrder);
        model.addAttribute("nosend", nosend);
        utOrder.setUeflag("02");
        int wait = utOrderMapper.selectCount(utOrder);
//        int wait = i+i1;
        model.addAttribute("wait",wait);

        //待评价
        utOrder.setUeflag("03");
        int i2 = utOrderMapper.selectCount(utOrder);
       /* utOrder.setUeflag("04");
        int i3 = utOrderMapper.selectCount(utOrder);
        int evaluate = i2+i3;*/
        model.addAttribute("evaluate",i2);

    }

    public AjaxResult updateOrder(UtOrder utOrder) {
        if (utOrder.getUeflag().equals("99")) {
            List<UtOrderItem> list = orderItemService.listOrderitemByUorderid(utOrder.getUorderid());
            if (list != null  && list.size() > 0) {
                for (UtOrderItem orderItem : list) {
                    if (!orderItem.getUpromotesales().equals("0")) {
                        userfreeproductService.delUserfreeproductByUorderitemid(orderItem.getUorderitemid());
                    }
                }
            }

        }

        int ret =utOrderMapper.updateOrder(utOrder);
        String result = null;
        if(ret <= 0){
            result = "更新失败";
            return new AjaxResult(0, result);
        }
        return new AjaxResult(1, "成功");
    }

    public int delOrder(String uorderid, String uuserid) {
        int i = utOrderMapper.delOrder(uorderid, uuserid);
        orderItemService.delByUorderid(uorderid);

        return i;

    }

    //钱包付款
    public AjaxResult walletPay(UtOrder utOrder, UtFinancelog utFinancelog, UtUsers updateUser) {
        try {

            int uintegral = 0;
            //保存财务日志
            financelogService.saveFinaceLog(utFinancelog);
            //更新用户金钱
            userService.updateByUuserid(updateUser);
            List<UtOrderItem> list = orderItemService.listOrderitemByUorderid(utOrder.getUorderid());
            UtUsers users = userService.queryByID(updateUser.getUuserid());
            UtOrder order = utOrderMapper.selectByPrimaryKey(utOrder.getUorderid());
            CtProfit ctProfit = profitService.queryProfitByCompId(utOrder.getUcompid(), utOrder.getUcustomerid());

            if (ctProfit != null && ctProfit.getClass() != null) {
                Double totalpayprice = Double.parseDouble(order.getUpayprice());
                if (totalpayprice != null && totalpayprice > 0 && ctProfit.getUintegral()!=null && ctProfit.getUintegral()>0) {
                    if (ctProfit.getUintegral() != null && ctProfit.getUintegral() > 0) {
                        uintegral  = (int) (totalpayprice / ctProfit.getUintegral());
                        order.setUintegral(uintegral);
                        if (users.getUintegral() != null ) {
                            uintegral += users.getUintegral();
                            //用户更新对象
                            UtUsers upuser = new UtUsers();
                            upuser.setUintegral(uintegral);
                            upuser.setUuserid(users.getUuserid());
                            userService.updateByUuserid(upuser);
                        }
                    }
                }
            }
            //生成核销二维码
            String verification = createVerification(utOrder.getUorderid(), order);

            utOrder.setUverificationcode(verification);

            for (UtOrderItem orderItem : list) {
                orderItem.setUpaystatus("1");
                //港货灵一元活动
                if (orderItem.getUcomproductid().equalsIgnoreCase("95730749885657088")) {
                    //直接现场取货
                    utOrder.setUeflag("02");
                }
            }
            //更新订单状态
            utOrderMapper.updateOrder(utOrder);

            for (UtOrderItem orderItem : list) {

//                financelogService.shareProfit(users, orderItem.getUorderitemid());
                financelogService.share(users,orderItem.getUorderitemid(),"01");
                orderItem.setUpaystatus("1");

            }
            orderItemService.updateOrderItemList(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0, "支付出错");
        }
        return new AjaxResult(1, "付款成功");

    }

    public AjaxResult refund(UtOrder updateOrder, UtFinancelog utFinancelog, UtUsers utUsers) {
        try {
            utOrderMapper.updateOrder(updateOrder);
            financelogService.saveFinaceLog(utFinancelog);
            userService.updateUbalance(utUsers);
            List<UtOrderItem> list = orderItemService.listOrderitemByUorderid(updateOrder.getUorderid());
            UtUsers users = userService.queryByID(utUsers.getUuserid());
            for (UtOrderItem orderItem : list) {
//                financelogService.shareProfit(users, orderItem.getUorderitemid());
                financelogService.share(users, orderItem.getUorderitemid(), "00");
                if (!orderItem.getUpromotesales().equals("0")) {
                    userfreeproductService.delUserfreeproductByUorderitemid(orderItem.getUorderitemid());
                }
            }

            //返回分润
//            financelogService.share(users,uorderitemid,);
            return new AjaxResult(1, "退款成功,请稍等");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new AjaxResult(0, "订单状态更新失败");
    }
    //结束付款
    public AjaxResult overPay(UtOrder utOrder, UtFinancelog utFinancelog) {
        try {
//            utOrderMapper.updateOrder(utOrder);
            List<UtOrderItem> itemList = orderItemService.listOrderitemByUorderid(utOrder.getUorderid());
            UtOrder order = utOrderMapper.selectByPrimaryKey(utOrder.getUorderid());
            CtProfit ctProfit = profitService.queryProfitByCompId(order.getUcompid(), order.getUcustomerid());
            UtUsers users = userService.queryByID(utOrder.getUuserid());

            if (ctProfit != null && ctProfit.getClass() != null) {
                Double totalpayprice = Double.parseDouble(utOrder.getUpayprice());
                if (totalpayprice != null && totalpayprice > 0 && ctProfit.getUintegral()!=null && ctProfit.getUintegral()>0) {
                    int uintegral = (int) (totalpayprice / ctProfit.getUintegral());
                    utOrder.setUintegral(uintegral);
                    if (users.getUintegral() != null ) {
                        uintegral += users.getUintegral();
                        //用户更新对象
                        UtUsers upuser = new UtUsers();
                        upuser.setUintegral(uintegral);
                        upuser.setUuserid(users.getUuserid());
                        userService.updateByUuserid(upuser);
                    }
                }
            }


            //生成核销二维码
            String verification = createVerification(utOrder.getUorderid(), order);

            utOrder.setUverificationcode(verification);

            for (UtOrderItem orderItem : itemList) {
                orderItem.setUpaystatus("1");
                //港货灵一元活动
               /* if (orderItem.getUcomproductid().equalsIgnoreCase("95730749885657088")) {
                    //直接现场取货
                    utOrder.setUeflag("02");
                }*/
            }
            //更新订单状态等
            utOrderMapper.updateOrder(utOrder);

            orderItemService.updateOrderItemList(itemList);
            financelogService.saveFinaceLog(utFinancelog);
            List<UtOrderItem> list = orderItemService.listOrderitemByUorderid(utOrder.getUorderid());
            for (UtOrderItem orderItem : list) {
//                financelogService.shareProfit(users, orderItem.getUorderitemid());
                financelogService.share(users,orderItem.getUorderitemid(),"01");

            }

            return new AjaxResult(1, "付款,请稍等");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(0, "订单状态更新失败");

    }

    public Double queryConsumption(UtOrder utOrder) {

        return utOrderMapper.queryConsumption(utOrder);
    }


    public String createVerification(String uorderid,UtOrder order) {
        String md5 = "";
        md5 = MD5Util.encrypt(order.getUcustomerid() + order.getUorderid()+ order.getUorderno() );

        //拼接跳转链接
        String url = publicurl + "/app/wd/order/verification";
        //生成二维码
        String qrcode = "";


        String json = "{'name':'verification','url':'"+ url +"','uorderid':'"+uorderid+"','md5':'"+md5+"'}";
        qrcode = QRCodeUtil.encode(json, "", doBase, "/verification/", true);
        return "/upload" + qrcode;
    }
    public String getVerificationcode(String uorderid, String uuserid) {
        return utOrderMapper.selectVerificationcode(uorderid, uuserid);
    }


}
