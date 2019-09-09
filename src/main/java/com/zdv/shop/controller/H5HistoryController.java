package com.zdv.shop.controller;

import com.zdv.shop.common.Constant;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.model.OtCompProductStock;
import com.zdv.shop.model.OtCompToProduct;
import com.zdv.shop.model.UtMyproductstore;
import com.zdv.shop.model.UtUsers;
import com.zdv.shop.service.OtCompProductStockService;
import com.zdv.shop.service.OtCompToProductService;
import com.zdv.shop.service.UtMyproductstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/h5/history")
public class H5HistoryController extends BaseController {
    @Autowired
    private OtCompToProductService compToProductService;

    @Autowired
    private OtCompProductStockService compProductStockService;
    @Autowired
    private UtMyproductstoreService myproductstoreService;
    @Autowired
    private HttpSession session;
    /**存放地址*/
    @Value("${global.upload.docBase}")
    private String docBase;
    @Value("${publicURL}") //在配置文件中
    private String publicurl;
    @Value("${ucustomerid}")
    private String ucustomerid;
    @Value("${ucompid}")
    private String ucompid;
    @RequestMapping("history")
    public String history(Model model, String active) {

        UtUsers user = (UtUsers) session.getAttribute(Constant.SESSION_H5USER);
        model.addAttribute("active", active);

        List<String> history = (List<String>) session.getAttribute("history");
        if (!StringUtils.objectIsNull(history)) {
            //倒序
            Collections.reverse(history);
            List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
            for (String s : history) {
                Map<String, Object> stringObjectMap = compToProductService.queryGoodsByComproductid(s);
                maps.add(stringObjectMap);
            }
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (Map<String, Object> map : maps) {
                Map<String, String> hashMap = new HashMap<>();
                String uhomepic = (String) map.get("uhomepic");
                if (com.zdv.shop.common.utils.StringUtils.StringisNotEmpty(uhomepic)) {
                    String[] split = uhomepic.split("#");
                    String s = split[0];
                    map.put("uhomepic", s);
                }
                String ucomproductid = map.get("ucomproductid").toString();
                OtCompProductStock productStock = new OtCompProductStock();
                productStock.setUcomproductid(ucomproductid);
                List<OtCompProductStock> stockList = compProductStockService.queryList(productStock);

                //若商品存在规格库存
                if (stockList.size() > 0) {
                    //最高价
                    Double max = 0.00;
                    //最低价
                    Double min = 0.00;
                    //总库存
                    Integer total = 0;
                    Double vipprice = 0.00;
                    Double marketprice = 0.00;
                    for (OtCompProductStock compProductStock : stockList) {
                        //总库存
                        total += Integer.parseInt(compProductStock.getUstock());
                        //获得最大价格
                        if (Double.parseDouble(compProductStock.getUprice()) > max) {
                            max = Double.parseDouble(compProductStock.getUprice());
                        }
                        //获得最小
                        if (Double.parseDouble(compProductStock.getUprice()) < min|| min ==0.00) {
                            min = Double.parseDouble(compProductStock.getUprice());
                            marketprice = Double.parseDouble(compProductStock.getUmarketprice());
                            vipprice = Double.parseDouble(compProductStock.getUvipprice());
                        }
                    }
                    map.put("uprice", min);
                    map.put("umarketprice", marketprice);
                    map.put("uvipprice", vipprice);
                    map.put("ustock", total);
                }


                list.add(hashMap);
                map.get("ualias");
                map.get("uhomepic");
                map.put("ucomproductid", map.get("ucomproductid") + "");
            }

        model.addAttribute("historyproducts", maps);
        }

        //获取收藏的商品信息
        UtMyproductstore utMyproductstore = new UtMyproductstore();
        utMyproductstore.setUuserid(user.getUuserid());
        utMyproductstore.setUcustomerid(ucustomerid);
        List<UtMyproductstore> queryList = myproductstoreService.queryList(utMyproductstore);

        List<Map<String, Object>> starlist = new ArrayList<Map<String, Object>>();
        for (UtMyproductstore myproductstore : queryList) {
            Map<String, Object> stringObjectMap = compToProductService.queryGoodsByComproductid(myproductstore.getUcomproductid());
            starlist.add(stringObjectMap);
        }
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            //遍历商品集合
        for (Map<String, Object> map : starlist) {
            Map<String, String> hashMap = new HashMap<>();
            String uhomepic = (String) map.get("uhomepic");
            if (com.zdv.shop.common.utils.StringUtils.StringisNotEmpty(uhomepic)) {
                String[] split = uhomepic.split("#");
                String s = split[0];
                map.put("uhomepic", s);
            }
            String ucomproductid = map.get("ucomproductid").toString();
            OtCompProductStock productStock = new OtCompProductStock();
            productStock.setUcomproductid(ucomproductid);
            List<OtCompProductStock> stockList = compProductStockService.queryList(productStock);

            //若商品存在规格库存
            if (stockList.size() > 0) {
                //最高价
                Double max = 0.00;
                //最低价
                Double min = 0.00;
                //总库存
                Integer total = 0;
                Double vipprice = 0.00;
                Double marketprice = 0.00;
                for (OtCompProductStock compProductStock : stockList) {
                    //总库存
                    total += Integer.parseInt(compProductStock.getUstock());
                    //获得最大价格
                    if (Double.parseDouble(compProductStock.getUprice()) > max) {
                        max = Double.parseDouble(compProductStock.getUprice());
                    }
                    //获得最小
                    if (Double.parseDouble(compProductStock.getUprice()) < min|| min ==0.00) {
                        min = Double.parseDouble(compProductStock.getUprice());
                        marketprice = Double.parseDouble(compProductStock.getUmarketprice());
                        vipprice = Double.parseDouble(compProductStock.getUvipprice());
                    }
                }
                map.put("uprice", min);
                map.put("umarketprice", marketprice);
                map.put("uvipprice", vipprice);
                map.put("ustock", total);
            }


            list.add(hashMap);
            map.get("ualias");
            map.get("uhomepic");
            map.put("ucomproductid", map.get("ucomproductid") + "");
        }


        model.addAttribute("starlist", starlist);


        return "templates/weui/star";
    }


    @RequestMapping("addMyproductstore/{ucomproductid}")
    @ResponseBody

    public AjaxResult addMyproductstore(@SessionAttribute(Constant.SESSION_H5USER)UtUsers users, @PathVariable("ucomproductid")String ucomproductid) {

        OtCompToProduct otCompToProduct = compToProductService.queryByID(ucomproductid);
        if (otCompToProduct != null) {
            UtMyproductstore utMyproductstore = new UtMyproductstore();
            utMyproductstore.setUproductstoreid(nextId());
            utMyproductstore.setUcompid(otCompToProduct.getUcompid());
            utMyproductstore.setUproductid(otCompToProduct.getUproductid());
            utMyproductstore.setUcomproductid(ucomproductid);
            utMyproductstore.setUuserid(users.getUuserid());
            return myproductstoreService.addMyproductstore(utMyproductstore);
        }



        return new AjaxResult(0,"收藏发生错误");
    }


    @RequestMapping("delMyproductstore/{ucomproductid}")
    @ResponseBody
    public AjaxResult delMyproductstore(@SessionAttribute(Constant.SESSION_H5USER)UtUsers users,@PathVariable("ucomproductid")String ucomproductid) {

        OtCompToProduct otCompToProduct = compToProductService.queryByID(ucomproductid);
        if (otCompToProduct != null) {
            UtMyproductstore utMyproductstore = new UtMyproductstore();
            utMyproductstore.setUuserid(users.getUuserid());
            utMyproductstore.setUcompid(otCompToProduct.getUcompid());
            utMyproductstore.setUcomproductid(ucomproductid);
            utMyproductstore.setUproductid(otCompToProduct.getUproductid());
            return	myproductstoreService.delMyproductstore(utMyproductstore);
        }
        return new AjaxResult(1);
    }

    @RequestMapping("changeMyproductstore")
    @ResponseBody
    public AjaxResult changeMyproductstore(@SessionAttribute(Constant.SESSION_H5USER) UtUsers users,String ucomproductid) {
        OtCompToProduct otCompToProduct = compToProductService.queryByID(ucomproductid);

        UtMyproductstore utMyproductstore = new UtMyproductstore();
        utMyproductstore.setUcomproductid(ucomproductid);
        utMyproductstore.setUcompid(otCompToProduct.getUcompid());
        utMyproductstore.setUuserid(users.getUuserid());
        utMyproductstore.setUproductid(otCompToProduct.getUproductid());
        utMyproductstore.setUcustomerid(ucustomerid);

        List<UtMyproductstore> utMyproductstores = myproductstoreService.queryList(utMyproductstore);
        //如果已存在则操作为删除
        if (utMyproductstores.size() > 0) {
            myproductstoreService.delMyproductstore(utMyproductstore);
        }else{
            utMyproductstore.setUproductstoreid(nextId());
            myproductstoreService.addMyproductstore(utMyproductstore);
        }

        List<UtMyproductstore> size = myproductstoreService.queryList(utMyproductstore);
        if (size.size() > 0) {
            return new AjaxResult(1);
        }else{
            return new AjaxResult(2);
        }

    }

}
