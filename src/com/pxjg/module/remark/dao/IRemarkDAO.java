package com.pxjg.module.remark.dao;

import java.util.List;

import com.pxjg.module.remark.entity.Remark;
import com.pxjg.util.PageBean;





public interface IRemarkDAO {
	public int insertRemark(Remark remark);
	public List<Remark> queryRemarkByPage(PageBean pageBean);
	public List<Remark> queryRemarkByDeptName(PageBean pageBean);
	public int queryRemarkCountByDeptName(String param);
	public int queryPageCount(String param);
	public int deleteRemark(String id);
	public int updateRemark(Remark remark);
	public Remark queryRemarkById(String id);
}
