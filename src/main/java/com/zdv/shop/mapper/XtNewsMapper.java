package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.XtNews;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtNewsMapper extends MyMapper<XtNews> {


    List<XtNews> queryNewsList(XtNews news);

    int updateNewsBynewsid(XtNews news);

    int addNews(XtNews news);

    XtNews queryNewsBynewsid(@Param("newsid") String newsid);

    int delNews(@Param("newsid") String newsid);
}
