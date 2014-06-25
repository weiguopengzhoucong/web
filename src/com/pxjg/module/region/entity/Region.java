package com.pxjg.module.region.entity;

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
public class Region extends PageBean {
	/** 主键id*/
	private String re_id;
	/** 上级id*/
	private String parent_id;
	/** 区域名称*/
	private String region_name;
	/** 区域类型*/
	private int region_type;
	/** 备份1*/
	private String remark1;
	/** 备份2*/
	private String remark2;
	/** 备份3*/
	private String remark3;
	
	private List <Region> lsArea = new ArrayList<Region>();
	
	private List <Region> lsShangquan = new ArrayList<Region>();
	
	private List <Department> lsDepratment = new ArrayList<Department>();
	
	/** 查询条件*/
	private String sql="";
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getRe_id() {
		return re_id;
	}
	public void setRe_id(String re_id) {
		this.re_id = re_id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public int getRegion_type() {
		return region_type;
	}
	public void setRegion_type(int region_type) {
		this.region_type = region_type;
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

	public List<Department> getLsDepratment() {
		return lsDepratment;
	}
	public void setLsDepratment(List<Department> lsDepratment) {
		this.lsDepratment = lsDepratment;
	}
	public List<Region> getLsArea() {
		return lsArea;
	}
	public void setLsArea(List<Region> lsArea) {
		this.lsArea = lsArea;
	}
	public List<Region> getLsShangquan() {
		return lsShangquan;
	}
	public void setLsShangquan(List<Region> lsShangquan) {
		this.lsShangquan = lsShangquan;
	}



	
}
