package com.pxjg.module.depttype.dao;

import java.util.List;

import com.pxjg.module.department.entity.Department;
import com.pxjg.module.depttype.entity.DeptType;
import com.pxjg.util.PageBean;



public interface IDeptTypeDAO {
	public DeptType queryDeptTypeList(DeptType depttype);
	public List<DeptType> queryDeptTypeListTree();
	public DeptType queryDeptTypeById(String id);
	public boolean updateDeptType(DeptType depttype);
	public boolean addDeptType(DeptType depttype);
	public Integer getParentCount(String id);
	public List<DeptType> getChildDeptType(String id);
	public boolean deleteDeptType(DeptType depttype);

}
