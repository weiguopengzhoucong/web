package com.pxjg.module.userCollection.dao;

import java.util.List;

import com.pxjg.module.userCollection.entity.UserCollection;
import com.pxjg.util.PageBean;




public interface ICollectionDAO {
	public int insertCollection(UserCollection collection);
	public int deleteCollection(String id);
	public List<UserCollection> queryCollectionByPage(PageBean pageBean);
	public UserCollection queryDeptCollection(String userid,String deptid);
	public UserCollection queryCourseCollection(String userid,String courseid);
	public int queryPageCount(String param);
}
