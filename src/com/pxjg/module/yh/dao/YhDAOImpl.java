package com.pxjg.module.yh.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pxjg.base.dao.BaseDAO;
import com.pxjg.module.yh.entity.Yh;
import com.pxjg.util.PageBean;

 
public class YhDAOImpl extends BaseDAO implements IYhDAO {

	@Override
	public int deleteYh(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("my_yh.deleteYh",id);
	}

	@Override
	public int insertYh(Yh yh) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("my_yh.insertYh",yh);
	}

	@Override
	public int openOrClose(String id, int state) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("state", state);
		return getSqlSession().update("my_yh.openOrClose",map);
	}

	@Override
	public int queryCountByParam(String param) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("param", param);
		return getSqlSession().selectOne("my_yh.queryCountByParam",map);
	}

	@Override
	public Yh queryYhById(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("my_yh.queryYhById",id);
	}

	@Override
	public List<Yh> queryYhByParam(PageBean pageBean) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		map.put("param", pageBean.getParam());
		return getSqlSession().selectList("my_yh.queryYhByParam",map);
	}

	@Override
	public int updateYh(Yh yh) {
		// TODO Auto-generated method stub
		return getSqlSession().update("my_yh.updateYh",yh);
	}

	

}
