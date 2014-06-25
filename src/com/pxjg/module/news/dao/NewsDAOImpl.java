package com.pxjg.module.news.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.pxjg.base.dao.BaseDAO;
import com.pxjg.module.news.entity.News;
import com.pxjg.util.PageBean;


public class NewsDAOImpl extends BaseDAO implements INewsDAO {

	
	
	@Override
	public News queryNewsById(String id) {
		return getSqlSession().selectOne("my_news.queryNewsByid",id);
	}

	@Override
	public int updateNews(News news) {
		return getSqlSession().update("my_news.updateNews",news);
	}

	@Override
	public int insertNews(News news) {
		return getSqlSession().insert("my_news.insertNews",news);
	}

	@Override
	public int deleteNews(String id) {
		return getSqlSession().delete("my_news.deleteNews",id);
	}
	
	
	@Override
	public List<News> queryNewsByDeptId(PageBean pageBean,String deptid,String type,String search) {
		
		String sql = " ";
		if(StringUtils.hasText(search)){
			sql = " and title like '%"+search+"%' ";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		map.put("type",type);
		map.put("deptid",deptid);
		map.put("search",sql);
		
		return getSqlSession().selectList("my_news.queryNewsByDeptId", map);
		
	}
	
	@Override
	public List<News> queryNewsByAdmin(PageBean pageBean,String type,String search) {
		
		String sql = " ";
		if(StringUtils.hasText(search)){
			sql = " and title like '%"+search+"%' ";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		map.put("type",type);
		map.put("search",sql);
		
		return getSqlSession().selectList("my_news.queryNewsByAdmin", map);
		
	}
	
	@Override
	public int queryCountByDeptid(String deptid,String type,String search) {
		String sql = " ";
		if(StringUtils.hasText(search)){
			sql = " and title like '%"+search+"%' ";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type",type);
		map.put("deptid",deptid);
		map.put("search",sql);
		return getSqlSession().selectOne("my_news.queryCountByDeptid",map);
	}
	
	@Override
	public int queryCount(String type,String search) {
		String sql = " ";
		if(StringUtils.hasText(search)){
			sql = " and title like '%"+search+"%' ";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type",type);
		map.put("search",sql);
		return getSqlSession().selectOne("my_news.queryCount",map);
	}
	
	@Override
	public List<News> queryNews(PageBean pageBean,String type,String search) {
		String sql = " ";
		if(StringUtils.hasText(search)){
			sql = " and title like '%"+search+"%' and top=1 ";
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		map.put("type",type);
		
		map.put("search",sql);
		
		return getSqlSession().selectList("my_news.queryNews", map);
		
	}

	@Override
	public int queryNewsCount(String string) {
		String sql = " type='"+string+"' ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		return getSqlSession().selectOne("my_news.queryNewsCount", map);
	}

	@Override
	public int openOrClose(String id, int state) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("state", state);
		return getSqlSession().update("my_news.openOrClose",map);
	}


}
