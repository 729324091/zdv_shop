package com.zdv.shop.weixin.message;

import java.util.List;


public class NewsMessage extends BaseMessage {
	// 图文消息个数，限制为10条
	private int ArticleCount;
	// 多条图文消息信息
	private List<News> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<News> getArticles() {
		return Articles;
	}

	public void setArticles(List<News> articles) {
		Articles = articles;
	}
}
