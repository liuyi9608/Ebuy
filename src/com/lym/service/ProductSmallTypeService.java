package com.lym.service;

import java.util.List;

import com.lym.entity.PageBean;
import com.lym.entity.ProductBigType;
import com.lym.entity.ProductSmallType;

public interface ProductSmallTypeService {
	
	public List<ProductSmallType> findProductSmallTypeList(ProductSmallType s_productSmallType,PageBean pageBean);
	
	public boolean existSmallTypeWithBigTypeId(int bigTypeId);
	
	public Long getCountSmallType(ProductSmallType s_productSmallType );

	public void saveSmallType(ProductSmallType productSmallType);
	
	public void deleteSmallType(ProductSmallType productSmallType);
	
	public ProductSmallType getProductSmallTypeById(int id);
}
