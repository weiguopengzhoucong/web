package com.pxjg.module.yh.dao;

import java.util.List;

import com.pxjg.module.yh.entity.Yh;
import com.pxjg.util.PageBean;


public interface IYhDAO {

	public List<Yh> queryYhByParam(PageBean pageBean);
	public int insertYh(Yh yh);
	public Yh queryYhById(String id);
	public int deleteYh(String id);
	public int openOrClose(String id,int state);
	public int updateYh(Yh yh);
	public int queryCountByParam(String param);
}
