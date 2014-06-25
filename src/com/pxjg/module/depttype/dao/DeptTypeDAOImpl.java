package com.pxjg.module.depttype.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pxjg.base.dao.BaseDAO;
import com.pxjg.module.department.entity.Department;
import com.pxjg.module.depttype.entity.DeptType;
import com.pxjg.util.PageBean;

public class DeptTypeDAOImpl extends BaseDAO implements IDeptTypeDAO {

	@Override
	public DeptType queryDeptTypeList(DeptType depttype) {
		depttype.setTotal(getDeptTypeCount(depttype));
		depttype.setDatas(getSqlSession().selectList("my_depttype.queryDeptType",depttype));
		return depttype;
	}

	public int getDeptTypeCount(DeptType depttype){
		return getSqlSession().selectOne("my_depttype.getDeptTypeCount",depttype);
	}
	public List<DeptType> queryDeptTypeListTree(){
		return getSqlSession().selectList("my_depttype.queryDeptTypeTree");
	}

	@Override
	public DeptType queryDeptTypeById(String id) {
		return getSqlSession().selectOne("my_depttype.queryDeptTypeById",id);
	}

	@Override
	public boolean updateDeptType(DeptType depttype) {
		boolean flag=false;
		try {
			getSqlSession().update("my_depttype.updateDeptType",depttype);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addDeptType(DeptType depttype) {
		boolean flag=false;
		try {
			getSqlSession().insert("my_depttype.addDeptType",depttype);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteDeptType(DeptType depttype) {
		boolean flag=false;
		try {
			
			getSqlSession().update("my_depttype.deleteDeptType",depttype);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public Integer getParentCount(String id) {
		return getSqlSession().selectOne("my_depttype.getParentCount",id);
	}

	@Override
	public List<DeptType> getChildDeptType(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("my_depttype.queryChildDeptType",id);
	}




}


