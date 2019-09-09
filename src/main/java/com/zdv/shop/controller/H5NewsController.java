package com.zdv.shop.controller;

import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.model.XtNews;
import com.zdv.shop.service.XtNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/h5/news/")
@Controller
public class H5NewsController extends BaseController {

    @Autowired
    private XtNewsService newsService;

    @Value("${ucustomerid}")
    private String ucustomerid;


    @RequestMapping("newsDetail/{newsid}")
    public String newsDetail(@PathVariable("newsid")String newsid, Map<String,Object> map) {

        XtNews news = newsService.queryNewsBynewsid(newsid);
        if (news != null && news.getUcustomerid().equalsIgnoreCase(ucustomerid)) {
            map.put("news", news);
        }

        return "templates/weui/news";

    }
    @RequestMapping("newsList")
    public String newsList(Map<String,Object> map) {
        XtNews news = new XtNews();
        news.setUcustomerid(ucustomerid);
        List<XtNews> newsList = newsService.queryNewsList(news);
        map.put("newsList", newsList);

        return "templates/weui/newslist";

    }

    @RequestMapping("queryNewsPage")
    @ResponseBody
    public AjaxResult queryNewsPage(Map<String,Object> map, Integer pageNo, Integer pageSize) {
        XtNews news = new XtNews();
        news.setUcustomerid(ucustomerid);
        List<XtNews> newsList = newsService.queryNewsList(news, pageNo, pageSize);
//        map.put("newsList", newsList);
//        return "templates/weui/newslist";

        return new AjaxResult(newsList);
    }
}
