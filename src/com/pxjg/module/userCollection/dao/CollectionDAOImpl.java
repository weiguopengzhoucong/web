package com.pxjg.module.userCollection.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pxjg.base.dao.BaseDAO;
import com.pxjg.module.userCollection.entity.UserCollection;
import com.pxjg.util.PageBean;

public class CollectionDAOImpl extends BaseDAO implements ICollectionDAO {

	@Override
	public int deleteCollection(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("my_collection.deleteCollection",id);
	}

	@Override
	public int insertCollection(UserCollection collection) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("my_collection.insertCollection",collection);
	}

	@Override
	public List<UserCollection> queryCollectionByPage(PageBean pageBean) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		map.put("param", pageBean.getParam());
		return getSqlSession().selectList("my_collection.queryCollectionByPage",map);
	}

	@Override
	public UserCollection queryCourseCollection(String userid, String courseid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("cid", courseid);
		return getSqlSession().selectOne("my_collection.queryCourseCollection",map);
	}

	@Override
	public UserCollection queryDeptCollection(String userid, String deptid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("deptid", deptid);
		return getSqlSession().selectOne("my_collection.queryDeptCollection",map);
	}

	@Override
	public int queryPageCount(String param) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("param",param);
		return getSqlSession().selectOne("my_collection.queryPageCount",map);
	}

	

}


