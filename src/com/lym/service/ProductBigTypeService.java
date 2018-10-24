package com.lym.service;

import java.util.List;

import org.omg.CORBA.INTERNAL;

import com.lym.entity.PageBean;
import com.lym.entity.ProductBigType;

public interface ProductBigTypeService {
	
	/**
	 * 查找所有的大标集合
	 * @return
	 */
	public List<ProductBigType> findAllBigTypeList();
	
	public List<ProductBigType> findBigTypeList(ProductBigType s_productBigType,PageBean pageBean);
	
	public Long getCountBigType(ProductBigType s_productBigType );

	public void saveBigType(ProductBigType productBigType);
	
	public void deleteBigType(ProductBigType productBigType);
	
	public ProductBigType getProductBigTypeById(int id);
}
