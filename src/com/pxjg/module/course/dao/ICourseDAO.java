package com.pxjg.module.course.dao;

import java.util.List;

import com.pxjg.module.course.entity.Course;
import com.pxjg.util.PageBean;



public interface ICourseDAO {
	public int addCourse(Course course);
	public List<Course> queryCourseByDeptid(PageBean pageBean,String deptid,String search);
	public int updateCourse(Course course);
	public int deleteCourse(String id);
	public Course queryCourseByid(String id);
	public int queryCountByDeptid(String deptid,String search);
	
	public List<String> queryCourseByGroup(String deptid);
	
	public List<Course> queryCourseByType(PageBean pageBean,String deptid,String type);
}
