package com.lym.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lym.dao.BaseDAO;
import com.lym.entity.PageBean;
import com.lym.entity.Product;
import com.lym.service.ProductService;
import com.lym.utli.StringUtil;

@Component("productService")
public class ProductServiceImpl implements ProductService {

	@Resource
	private BaseDAO<Product> baseDAO;

	/**
	 * 获得全部商品
	 */
	@Override
	public List<Product> findProductList(Product s_product, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Product ");
		if (s_product!= null) {
			if(s_product.getBigType()!=null){
				hql.append("and bigType.id=?");
				param.add(s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				hql.append("and smallType.id=?");
				param.add(s_product.getSmallType().getId());
			}
			if (s_product.getSpecialPrice()==1) {
				hql.append("and specialPrice=1 order by specialPriceTime desc");
			}
			if (s_product.getHot()==1) {
				hql.append("and hot=1 order by hotTime desc");
			}
			if(StringUtil.isNotEmpty(s_product.getName())){
				hql.append("and name like ?");
				param.add("%"+s_product.getName()+"%");
			}
		}
		if (pageBean!=null) {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}
	}
	
	/**
	 * 获得商品的数量
	 */
	@Override
	public Long getProductCount(Product s_product) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Product ");
		if (s_product!= null) {
			if(s_product.getBigType()!=null){
				hql.append("and bigType.id=?");
				param.add(s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				hql.append("and smallType.id=?");
				param.add(s_product.getSmallType().getId());
			}
			if(StringUtil.isNotEmpty(s_product.getName())){
				hql.append("and name like ?");
				param.add("%"+s_product.getName()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	/**
	 * 按照商品ID查找商品详情
	 */
	@Override
	public Product getProductById(int productId) {
		
		return baseDAO.get(Product.class, productId);
	}

	@Override
	public void save(Product product) {
		baseDAO.merge(product);
	}

	@Override
	public void delete(Product product) {
		baseDAO.delete(product);
	}

	@Override
	public void setProductWithHot(int productId) {
		Product product = baseDAO.get(Product.class, productId);
		product.setHot(1);
		product.setHotTime(new Date());
		baseDAO.save(product);
		
	}

	@Override
	public void setProductWithSpecialPrice(int productId) {
		Product product = baseDAO.get(Product.class, productId);
		product.setSpecialPrice(1);
		product.setSpecialPriceTime(new Date());
	}

	@Override
	public boolean existProductWithSmallType(int smallTypeId) {
		String hql="from Product where smallType.id="+smallTypeId;
		
		if(baseDAO.find(hql).size()>0){
			return true;
		}else {
			return false;
		}
	}
	
}
