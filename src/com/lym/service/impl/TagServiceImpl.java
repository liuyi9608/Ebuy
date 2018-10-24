package com.lym.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.swing.text.html.parser.TagElement;

import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Component;

import com.lym.dao.BaseDAO;
import com.lym.entity.PageBean;
import com.lym.entity.Tag;
import com.lym.utli.StringUtil;

@Component("tagService")
public class TagServiceImpl implements com.lym.service.TagService {

	@Resource
	private BaseDAO<Tag> baseDAO;
	
	

	@Override
	public List<Tag> findTagList(Tag s_tag, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Tag");
		if(s_tag!=null){
			if(StringUtil.isNotEmpty(s_tag.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_tag.getName()+"%");
			}
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}
		return baseDAO.find(hql.toString());
	}

	@Override
	public Tag getTagById(int tagid) {
		return baseDAO.get(Tag.class, tagid);
	}

	@Override
	public Long getTagCount(Tag s_tag) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Tag");
		if(s_tag!=null){
			if(StringUtil.isNotEmpty(s_tag.getName())){
				hql.append(" and title like ?");
				param.add("%"+s_tag.getName()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}


	@Override
	public void save(Tag tag) {
		baseDAO.merge(tag);
	}

	@Override
	public void delete(Tag tag) {
		baseDAO.delete(tag);
	}

}
