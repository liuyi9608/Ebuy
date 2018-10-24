package com.lym.service;

import java.util.List;

import com.lym.entity.News;
import com.lym.entity.Notice;
import com.lym.entity.PageBean;
import com.lym.entity.Product;

public interface NewsService {
	/**
	 * 查找所有的新闻集合
	 * @param s_news 查找的新闻
	 * @param pageBean 分页操作
	 * @return
	 */
	public List<News> findNewsList(News s_news,PageBean pageBean);
	/**
	 * 查找新闻内容
	 * @param newsId 新闻ID
	 * @return
	 */
	public News getNewsId(int newsId);
	
	public Long getNewsCount(News s_news);
	
	public void saveNews(News news);
	
	public void delete(News news);
}
