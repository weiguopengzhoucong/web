package com.pxjg.module.news.dao;

import java.util.List;

import com.pxjg.module.news.entity.News;
import com.pxjg.util.PageBean;


public interface INewsDAO {

	public News queryNewsById(String id);
	public int updateNews(News news);
	public int insertNews(News news);
	public int deleteNews(String id);
	public int queryCountByDeptid(String deptid,String type,String search);
	public int queryCount(String type,String search);
	public List<News> queryNews(PageBean pageBean,String type,String search);
	public List<News> queryNewsByDeptId(PageBean pageBean,String deptid,String type,String search);
	public int queryNewsCount(String string);
	public List<News> queryNewsByAdmin(PageBean pageBean,String type,String search);
	//激活
	public int openOrClose(String id,int state);
}
