package com.pxjg.module.remark.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pxjg.base.dao.BaseDAO;
import com.pxjg.module.remark.entity.Remark;
import com.pxjg.util.PageBean;

public class RemarkDAOImpl extends BaseDAO implements IRemarkDAO {

	@Override
	public int insertRemark(Remark remark) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("my_remark.insertRemark",remark);
	}

	@Override
	public int queryPageCount(String param) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("param",param);
		return getSqlSession().selectOne("my_remark.queryPageCount",map);
	}

	@Override
	public List<Remark> queryRemarkByPage(PageBean pageBean) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		map.put("param", pageBean.getParam());
		return getSqlSession().selectList("my_remark.queryRemarkByPage",map);
	}

	@Override
	public int deleteRemark(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("my_remark.deleteRemark",id);
	}

	@Override
	public Remark queryRemarkById(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("my_remark.queryRemarkById",id);
	}

	@Override
	public int updateRemark(Remark remark) {
		// TODO Auto-generated method stub
		return getSqlSession().update("my_remark.updateRemark",remark);
	}

	@Override
	public List<Remark> queryRemarkByDeptName(PageBean pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		map.put("param", pageBean.getParam());
		return getSqlSession().selectList("my_remark.queryRemarkByDeptName",map);
	}

	@Override
	public int queryRemarkCountByDeptName(String param) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("param",param);
		return getSqlSession().selectOne("my_remark.queryRemarkCountByDeptName",map);
	}
	

}


