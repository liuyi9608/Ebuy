package com.lym.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.lym.entity.News;
import com.lym.entity.Notice;
import com.lym.entity.PageBean;
import com.lym.entity.Product;
import com.lym.entity.ProductBigType;
import com.lym.entity.Tag;
import com.lym.service.NewsService;
import com.lym.service.NoticeService;
import com.lym.service.ProductBigTypeService;
import com.lym.service.ProductService;
import com.lym.service.TagService;

@Component
public class InitAction implements ServletContextListener,ApplicationContextAware{
	private static ApplicationContext applicationContext;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		ServletContext application=servletContextEvent.getServletContext();
		
		//吧大分类保存到application中
		ProductBigTypeService productBigTypeService=(ProductBigTypeService)applicationContext.
				getBean("productBigTypeService");
		List<ProductBigType> bigTypeList=productBigTypeService.findAllBigTypeList();
		application.setAttribute("bigTypeList", bigTypeList);
		
		//吧标签保存到application中
		TagService tagService=(TagService) applicationContext.getBean("tagService");
		List<Tag> tagList = tagService.findTagList(null,null);
		application.setAttribute("tagList", tagList);
		
		//把公告保存到application中
		NoticeService noticeService=(NoticeService) applicationContext.getBean("noticeService");
		List<Notice> noticeList = noticeService.findNoticeList(null,new PageBean(1, 7));
		application.setAttribute("noticeList", noticeList);
		
		//把新闻保存到application中
		NewsService newsService=(NewsService) applicationContext.getBean("newsService");
		List<News> newsList = newsService.findNewsList(null, new PageBean(1, 7));
		application.setAttribute("newsList", newsList);
		
		//把特价商品保存到application中
		
		ProductService productService=(ProductService)applicationContext.getBean("productService");
		Product s_product=new Product();
		s_product.setSpecialPrice(1);
		List<Product> specialPriceProductList=productService.findProductList(s_product, new PageBean(1,8));
		application.setAttribute("specialPriceProductList", specialPriceProductList);
		
		
		//把热门商品保存到application中
		s_product=new Product();
		s_product.setHot(1);
		List<Product> hotProductList=productService.findProductList(s_product, new PageBean(1, 8));
		application.setAttribute("hotProductList", hotProductList);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext=applicationContext;
	}
	
}
