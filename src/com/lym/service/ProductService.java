package com.lym.service;

import java.util.List;

import com.lym.entity.PageBean;
import com.lym.entity.Product;

public interface ProductService {
	/**
	 * 查找所有的商品集合
	 * @param s_product 查找的商品
	 * @param pageBean 分页
	 * @return
	 */
	public List<Product> findProductList(Product s_product, PageBean pageBean);
	
	/**
	 * 计算商品数量
	 * @param s_product
	 * @return
	 */
	public Long getProductCount(Product s_product);
	
	
	public Product getProductById(int productId);
	
	public void save(Product product);
	
	public void delete(Product product);
	
	public void setProductWithHot(int productId);
	
	public void setProductWithSpecialPrice(int productId);
	
	public boolean existProductWithSmallType(int smallTypeId);
	
}
