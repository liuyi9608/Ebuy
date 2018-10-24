package com.lym.service;

import java.util.List;

import com.lym.entity.PageBean;
import com.lym.entity.Tag;

public interface TagService {
	/**
	 * 查找标签集合
	 * @return
	 */
	public List<Tag> findTagList(Tag s_tag,PageBean pageBean);
	
	public Tag getTagById(int tagid);
	
	public Long getTagCount(Tag s_tag);
	
	public void save(Tag tag);
	
	public void delete(Tag tag);
	
	
}
