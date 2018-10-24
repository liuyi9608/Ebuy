package com.lym.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lym.dao.BaseDAO;
import com.lym.entity.PageBean;
import com.lym.entity.ProductBigType;
import com.lym.service.ProductBigTypeService;
import com.lym.utli.StringUtil;

@Service("productBigTypeService")
public class ProductBigTypeServiceImpl implements ProductBigTypeService {
	
	@Resource
	private BaseDAO<ProductBigType> baseDAO;
	
	@Override
	public List<ProductBigType> findAllBigTypeList() {
		return baseDAO.find(" from ProductBigType");
	}

	@Override
	public List<ProductBigType> findBigTypeList(ProductBigType s_productBigType, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from ProductBigType ");
		if (s_productBigType!= null) {
			if(StringUtil.isNotEmpty(s_productBigType.getName())){
				hql.append("and name like ?");
				param.add("%"+s_productBigType.getName()+"%");
			}
		}
		if (pageBean!=null) {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}

	@Override
	public Long getCountBigType(ProductBigType s_productBigType) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from ProductBigType ");
		if (s_productBigType!= null) {
			if(StringUtil.isNotEmpty(s_productBigType.getName())){
				hql.append("and name like ?");
				param.add("%"+s_productBigType.getName()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void saveBigType(ProductBigType productBigType) {
		baseDAO.merge(productBigType);
	}

	@Override
	public void deleteBigType(ProductBigType productBigType) {
		baseDAO.delete(productBigType);
	}

	@Override
	public ProductBigType getProductBigTypeById(int id) {
		return baseDAO.get(ProductBigType.class, id);
	}
	

}
