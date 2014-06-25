package com.pxjg.base.dao;

import org.apache.ibatis.session.SqlSession;

public class BaseDAO {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
}
