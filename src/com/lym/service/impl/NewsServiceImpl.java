package com.lym.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lym.dao.BaseDAO;
import com.lym.entity.News;
import com.lym.entity.Notice;
import com.lym.entity.PageBean;
import com.lym.service.NewsService;
import com.lym.utli.StringUtil;

@Component("newsService")
public class NewsServiceImpl implements NewsService {
	@Resource
	private BaseDAO<News> baseDAO;
	
	@Override
	public List<News> findNewsList(News s_news, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from News");
		if(pageBean!=null){
			return baseDAO.find(hql.toString(), param, pageBean);
		}
		return null;
	}

	@Override
	public News getNewsId(int newsId) {
		return baseDAO.get(News.class, newsId);
	}

	@Override
	public Long getNewsCount(News s_news) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from News");
		if(s_news!=null){
			if(StringUtil.isNotEmpty(s_news.getTitle())){
				hql.append(" and title like ?");
				param.add("%"+s_news.getTitle()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void saveNews(News news) {
		// TODO Auto-generated method stub
		baseDAO.merge(news);
	}

	@Override
	public void delete(News news) {
		baseDAO.delete(news);
	}

	
}
