package com.pxjg.base.dao;

import java.util.List;

public class BaseDataDAOImpl extends BaseDAO implements IBaseDataDAO {

	@Override
	public List<String> queryDay() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("my_basedata.queryDay");
	}

	@Override
	public List<String> queryMonth() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("my_basedata.queryMonth");
	}

	@Override
	public List<String> queryXingZuo() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("my_basedata.queryXingZuo");
	}

	@Override
	public List<String> queryYear() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("my_basedata.queryYear");
	}

}
