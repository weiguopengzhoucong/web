package com.pxjg.module.department.dao;

import java.util.List;

import com.pxjg.module.department.entity.Department;
import com.pxjg.util.PageBean;



public interface IDepartmentDAO {
	public int addDept(Department dept);
	public List<Department> queryDeptBySQ(String shangquanid);
	public List<Department> queryDeptByCity(String cityid);
	public int updateDeptState(String id,int state);
	public int updateDept(Department dept);
	public Department queryDeptById(String id);
	
	public int queryDeptBySearchCount(String re_id,
			String select_type, String select_value);
	public List<Department> queryDeptBySearch(PageBean currPageBean,
			String re_id, String select_type, String select_value,String order);
	
	
	public int queryDeptCountByShangquanId(String type,String re_id, String areaid, String shangquanid);
	
	public List<Department> queryDeptByShangquanId(PageBean pageBean,
			String type,String re_id, String areaid, String shangquanid,String order);
	public int queryRankDeptCount();
	public List<Department> queryRank(PageBean pageBean);
	public int toTop(String id,String top);
}
