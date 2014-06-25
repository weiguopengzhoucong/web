package com.pxjg.module.course.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.pxjg.base.dao.BaseDAO;
import com.pxjg.module.course.entity.Course;
import com.pxjg.module.department.entity.Department;
import com.pxjg.util.PageBean;

public class CourseDAOImpl extends BaseDAO implements ICourseDAO {

	@Override
	public int addCourse(Course course) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("my_course.insertCourse",course);
	}

	@Override
	public List<Course> queryCourseByDeptid(PageBean pageBean,String deptid, String search) {
		// TODO Auto-generated method stub
		String sql = " ";
		if(StringUtils.hasText(search)){
			sql = " and ( skey like '%"+search+"%' or cname like '%"+search+"%') ";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		map.put("deptid",deptid);
		map.put("search",sql);
		
		return getSqlSession().selectList("my_course.queryCourseByDeptid", map);
		
	}

	@Override
	public int updateCourse(Course course) {
		// TODO Auto-generated method stub
		return getSqlSession().update("my_course.updateCourse",course);
	}

	@Override
	public int deleteCourse(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().update("my_course.deleteCourse",id);
	}

	@Override
	public Course queryCourseByid(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("my_course.queryCourseByid", id);
	}

	@Override
	public int queryCountByDeptid(String deptid, String search) {
		// TODO Auto-generated method stub
		String sql = " ";
		if(StringUtils.hasText(search)){
			sql = " and (skey like '%"+search+"%' or cname like '%"+search+"%') ";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptid",deptid);
		map.put("search",sql);
		
		return getSqlSession().selectOne("my_course.queryCountByDeptid", map);
	}

	@Override
	public List<String> queryCourseByGroup(String deptid) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("my_course.queryCourseByGroup", deptid);
	}

	@Override
	public List<Course> queryCourseByType(PageBean pageBean, String deptid,
			String type) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		map.put("deptid",deptid);
		map.put("type",type);
		return getSqlSession().selectList("my_course.queryCourseByType", map);
	}
	
	

	

}


