package com.pxjg.module.remark.entity;

import java.util.Date;

import com.pxjg.module.course.entity.Course;
import com.pxjg.module.department.entity.Department;
import com.pxjg.module.user.entity.User;

public class Remark {
	private String id;
	private String deptid;
	private String newsid;
	private int type;
	private String content;
	private int state;
	private Date happentime;
	private String remotehost;
	private String userid;
	private String parentid;
	private int score;
	private String remark1;
	private String remark2;
	private String remark3;
	private User user;
	private Department dept;
	private Course course;
	private Remark childRemark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getHappentime() {
		return happentime;
	}
	public void setHappentime(Date happentime) {
		this.happentime = happentime;
	}
	public String getRemotehost() {
		return remotehost;
	}
	public void setRemotehost(String remotehost) {
		this.remotehost = remotehost;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getRemark3() {
		return remark3;
	}
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
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
	public Remark getChildRemark() {
		return childRemark;
	}
	public void setChildRemark(Remark childRemark) {
		this.childRemark = childRemark;
	}
	
	
}
