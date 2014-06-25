package com.pxjg.module.depttype.entity;

import java.util.ArrayList;
import java.util.List;

import com.pxjg.module.department.entity.Department;
import com.pxjg.util.PageBean;

/**
 * 
 *  组织机构管理实体类
 * 
 * @author hanzl
 *
 */
public class DeptType extends PageBean {
	/** 主键id*/
	private String id;
	/** 上级id*/
	private String parentid;
	/** 区域名称*/
	private String typename;
	/** 区域类型*/
	private String content;
	/** 备份1*/
	private int seq;
	
	private List<DeptType> lsDeptType = new ArrayList<DeptType>();
	
	private List<Department> lsDepartment = new ArrayList<Department>();
	
	/** 查询条件*/
	private String sql="";
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public List<DeptType> getLsDeptType() {
		return lsDeptType;
	}
	public void setLsDeptType(List<DeptType> lsDeptType) {
		this.lsDeptType = lsDeptType;
	}
	public List<Department> getLsDepartment() {
		return lsDepartment;
	}
	public void setLsDepartment(List<Department> lsDepartment) {
		this.lsDepartment = lsDepartment;
	}
	
	
	
}
