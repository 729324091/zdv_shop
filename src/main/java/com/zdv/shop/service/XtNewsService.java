package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.mapper.XtNewsMapper;
import com.zdv.shop.model.XtNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XtNewsService extends AbstratService<XtNews> {

    @Autowired
    private XtNewsMapper newsMapper;

    public PageAjax<XtNews> queryNewsPage(PageAjax<XtNews> page, XtNews news) {

        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<XtNews> list = newsMapper.queryNewsList(news);

        return new PageAjax<>(list);


    }

    public AjaxResult updateNewsBynewsid(XtNews news) {
        String result = null;
        int i =newsMapper.updateNewsBynewsid(news);
        if (i < 1) {
            result = "操作失败";
            return new AjaxResult(result);

        }
        return new AjaxResult();




    }

    public AjaxResult addNews(XtNews news) {
        String result = null;
        int i = newsMapper.addNews(news);
        if (i < 1) {
            result = "操作失败";
            return new AjaxResult(result);

        }
        return new AjaxResult();

    }

    public XtNews queryNewsBynewsid(String newsid) {
        return newsMapper.queryNewsBynewsid(newsid);
    }

    public int delNews(String newsid) {
        return newsMapper.delNews(newsid);
    }

    public List<XtNews> queryNewsList(XtNews news) {
        return newsMapper.queryNewsList(news);

    }

    public List<XtNews> queryNewsList(XtNews news, Integer pageNo, Integer pageSize) {
        PageMethod.startPage(pageNo, pageSize);
        return newsMapper.queryNewsList(news);
    }
}
