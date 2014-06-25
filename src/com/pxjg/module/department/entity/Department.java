package com.pxjg.module.department.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pxjg.module.course.entity.Course;
import com.pxjg.module.news.entity.News;
import com.pxjg.module.yh.entity.Yh;

public class Department {
	private String id;
	private String deptname;
	private String depttype;
	private String cityid;
	private String description;
	private String address;
	private String tel;
	private String content;
	private int state;
	private int num;
	private int top;
	private String x;
	private String y;
	private String url;
	private Date happentime;
	private String c1;
	private String c2;
	private String c3;
	private String quxianid;
	private String shangquanid;
	
	
	private Course course;
	private Yh yh;
	private List <News> lsNews = new ArrayList<News>();
	private List <Course> lsCourse = new ArrayList<Course>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	public String getDepttype() {
		return depttype;
	}
	public void setDepttype(String depttype) {
		this.depttype = depttype;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getHappentime() {
		return happentime;
	}
	public void setHappentime(Date happentime) {
		this.happentime = happentime;
	}
	public String getC1() {
		return c1;
	}
	public void setC1(String c1) {
		this.c1 = c1;
	}
	public String getC2() {
		return c2;
	}
	public void setC2(String c2) {
		this.c2 = c2;
	}
	public String getC3() {
		return c3;
	}
	public void setC3(String c3) {
		this.c3 = c3;
	}
	public String getQuxianid() {
		return quxianid;
	}
	public void setQuxianid(String quxianid) {
		this.quxianid = quxianid;
	}
	public String getShangquanid() {
		return shangquanid;
	}
	public void setShangquanid(String shangquanid) {
		this.shangquanid = shangquanid;
	}

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Yh getYh() {
		return yh;
	}
	public void setYh(Yh yh) {
		this.yh = yh;
	}
	public List<News> getLsNews() {
		return lsNews;
	}
	public void setLsNews(List<News> lsNews) {
		this.lsNews = lsNews;
	}
	public List<Course> getLsCourse() {
		return lsCourse;
	}
	public void setLsCourse(List<Course> lsCourse) {
		this.lsCourse = lsCourse;
	}



	
}
