package com.pxjg.module.userCollection.entity;

import java.util.Date;

import com.pxjg.module.course.entity.Course;
import com.pxjg.module.department.entity.Department;

public class UserCollection {
	private String id;
	private String userid;
	private String type;
	private int sate;
	private String deptid;
	private String newid;
	private String cid;
	private Date happentime;
	private Department dept;
	private Course course;
	
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSate() {
		return sate;
	}
	public void setSate(int sate) {
		this.sate = sate;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getNewid() {
		return newid;
	}
	public void setNewid(String newid) {
		this.newid = newid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Date getHappentime() {
		return happentime;
	}
	public void setHappentime(Date happentime) {
		this.happentime = happentime;
	}
	
	
}
