package com.lym.service;

import java.util.List;

import org.hibernate.sql.Delete;

import com.lym.entity.Notice;
import com.lym.entity.PageBean;

public interface NoticeService {
	/**
	 * 查找所有的公告集合
	 * @param s_notice 查找的公告
	 * @param pageBean 分页信息
	 * @return
	 */
	public List<Notice> findNoticeList(Notice s_notice,PageBean pageBean);
	/**
	 * 查找公告详情
	 * @param noticeId 公告ID
	 * @return
	 */
	public Notice getNoticeId(int noticeId);
	
	public Long getNoticeCount(Notice s_notice);
	
	public void saveNotice(Notice notice);
	
	public void delete(Notice notice);
}
