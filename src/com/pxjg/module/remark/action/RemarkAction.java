package com.pxjg.module.remark.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.pxjg.module.front.BaseAction;
import com.pxjg.module.remark.dao.IRemarkDAO;
import com.pxjg.module.remark.entity.Remark;
import com.pxjg.module.user.entity.User;
import com.pxjg.util.PageBean;

public class RemarkAction extends BaseAction{
	private IRemarkDAO remarkDAO;
	private Remark remark;
	private PageBean pageBean;
	private List<Remark> remarkList;

	public IRemarkDAO getRemarkDAO() {
		return remarkDAO;
	}

	public void setRemarkDAO(IRemarkDAO remarkDAO) {
		this.remarkDAO = remarkDAO;
	}
	
	
	public Remark getRemark() {
		return remark;
	}

	public void setRemark(Remark remark) {
		this.remark = remark;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	

	public List<Remark> getRemarkList() {
		return remarkList;
	}

	public void setRemarkList(List<Remark> remarkList) {
		this.remarkList = remarkList;
	}

	public String goRemark(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		String deptid = request.getParameter("deptid");
		String newsid = request.getParameter("newsid");
		request.setAttribute("type", type);
		request.setAttribute("deptid", deptid);
		request.setAttribute("newsid", newsid);
		return "goRemark";
	}
	
	public void saveRemark() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("userBean");
		String score = request.getParameter("score");
		String type = request.getParameter("type");
		if(remark.getId()!=null){
			remark.setParentid(remark.getId());
		}
		remark.setId(UUID.randomUUID().toString());
		remark.setScore(Integer.parseInt(score));
		remark.setContent(remark.getContent().trim());
		remark.setType(Integer.parseInt(type));
		remark.setUserid(user.getUser_id());
		int result = remarkDAO.insertRemark(remark);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		if(result>0){
			out = response.getWriter();
			out.print("1");
			
		}else{
			out = response.getWriter();
			out.print("0");
		}
		if(out!=null)
			out.close();
	}
	
	public String remarkList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//查询机构收藏还是课程收藏
		String type = request.getParameter("type");
		String dateSearch = request.getParameter("dateSearch");
		User user = (User)request.getSession().getAttribute("userBean");
		String deptid = user.getDeptid();
		String param = "";
		param = "  and type='"+type+"' and deptid='" + deptid+"'";
		if(dateSearch!=null&&!dateSearch.equals("")){
			param = param + " and to_char(happentime,'yyyy-mm-dd')='"+dateSearch+"'";
		}
		if(pageBean!=null&&pageBean.getParam()!=null){
			param = pageBean.getParam();
		}
		PageBean currPageBean=new PageBean();
		int count = remarkDAO.queryPageCount(param);
		if (pageBean != null) {
			pageBean.setPageSize(10);
			currPageBean
					.init(pageBean.getCurrentPage(), pageBean.getPageSize());
			if (currPageBean.getCurrentPage() != 1) {
				currPageBean.setEndCount(currPageBean.getEndCount() - 1);
			}
			currPageBean.setParam(param);
			remarkList = remarkDAO.queryRemarkByPage(currPageBean);

		} else {
			currPageBean.init(currPageBean.getCurrentPage(), currPageBean
					.getPageSize());
			currPageBean.setEndCount(10);
			currPageBean.setPageSize(10);
			int totalPage = count % currPageBean.getPageSize() == 0 ? count
					/ currPageBean.getPageSize() : count
					/ currPageBean.getPageSize() + 1;
			currPageBean.setTotalPage(totalPage);
			currPageBean.setParam(param);
			remarkList = remarkDAO.queryRemarkByPage(currPageBean);


		}
		currPageBean.setTotal(count);
		request.setAttribute("type", type);
		pageBean = currPageBean;
		return "dept_remark";
	}
	
	public String managerRemarkList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String dateSearch = request.getParameter("dateSearch");
		String deptSearch = request.getParameter("deptSearch");
		String param = "";
		if(deptSearch!=null&&!deptSearch.equals("")){
			param = param + " and d.deptname like '"+deptSearch+"%'";
		}
		if(dateSearch!=null&&!dateSearch.equals("")){
			param = param + " and to_char(r.happentime,'yyyy-mm-dd')='"+dateSearch+"'";
		}
		if(pageBean!=null&&pageBean.getParam()!=null){
			param = pageBean.getParam();
		}
		PageBean currPageBean=new PageBean();
		int count = remarkDAO.queryRemarkCountByDeptName(param);
		if (pageBean != null) {
			pageBean.setPageSize(10);
			currPageBean
					.init(pageBean.getCurrentPage(), pageBean.getPageSize());
			if (currPageBean.getCurrentPage() != 1) {
				currPageBean.setEndCount(currPageBean.getEndCount() - 1);
			}
			currPageBean.setParam(param);
			remarkList = remarkDAO.queryRemarkByDeptName(currPageBean);

		} else {
			currPageBean.init(currPageBean.getCurrentPage(), currPageBean
					.getPageSize());
			currPageBean.setEndCount(10);
			currPageBean.setPageSize(10);
			int totalPage = count % currPageBean.getPageSize() == 0 ? count
					/ currPageBean.getPageSize() : count
					/ currPageBean.getPageSize() + 1;
			currPageBean.setTotalPage(totalPage);
			currPageBean.setParam(param);
			remarkList = remarkDAO.queryRemarkByDeptName(currPageBean);


		}
		currPageBean.setTotal(count);
		pageBean = currPageBean;
		request.setAttribute("dateSearch", dateSearch);
		request.setAttribute("deptSearch", deptSearch);
		return "manager_remark";
	}
	
	public String reply(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		remark = remarkDAO.queryRemarkById(id);
		return "reply";
	}
	
	public String detail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		remark = remarkDAO.queryRemarkById(id);
		return "detail";
	}
	
	public void deleteRemark() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		int result = remarkDAO.deleteRemark(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		if(result>0){
			out = response.getWriter();
			out.print("1");
			
		}else{
			out = response.getWriter();
			out.print("0");
		}
		if(out!=null)
			out.close();
	}
	
	public String myRemark(){
		HttpServletRequest request = ServletActionContext.getRequest();
		PageBean currPageBean=new PageBean();
		User user = (User)request.getSession().getAttribute("userBean");
		String param = " and userid = '"+user.getUser_id()+"'";
		int count = remarkDAO.queryPageCount(param);
		if (pageBean != null) {
			pageBean.setPageSize(10);
			currPageBean
					.init(pageBean.getCurrentPage(), pageBean.getPageSize());
			if (currPageBean.getCurrentPage() != 1) {
				currPageBean.setEndCount(currPageBean.getEndCount() - 1);
			}
			currPageBean.setParam(param);
			remarkList = remarkDAO.queryRemarkByPage(currPageBean);

		} else {
			currPageBean.init(currPageBean.getCurrentPage(), currPageBean
					.getPageSize());
			currPageBean.setEndCount(10);
			currPageBean.setPageSize(10);
			int totalPage = count % currPageBean.getPageSize() == 0 ? count
					/ currPageBean.getPageSize() : count
					/ currPageBean.getPageSize() + 1;
			currPageBean.setTotalPage(totalPage);
			currPageBean.setParam(param);
			remarkList = remarkDAO.queryRemarkByPage(currPageBean);


		}
		currPageBean.setTotal(count);
		pageBean = currPageBean;
		return "myRemark";
	}
	
	public String goUpdateMyRemark(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		remark = remarkDAO.queryRemarkById(id);
		return "updateRemark";
	}
	
	public void updateRemark() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String score = request.getParameter("score");
		remark.setScore(Integer.parseInt(score));
		int result = remarkDAO.updateRemark(remark);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		if(result>0){
			out = response.getWriter();
			out.print("1");
			
		}else{
			out = response.getWriter();
			out.print("0");
		}
		if(out!=null)
			out.close();
	}
	
	public void frontRemarkList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String deptid = request.getParameter("deptid");
		String newsid = request.getParameter("newsid");
		String sqlnews = "";
		if(newsid!=null){
			sqlnews =" and newsid= '"+newsid+"'";
		}
		String type = request.getParameter("type");
		String param = " and type='"+type+"' and deptid='" + deptid+"' "+sqlnews;
		if(pageBean!=null&&pageBean.getParam()!=null){
			param = pageBean.getParam();
		}
		PageBean currPageBean=new PageBean();
		int count = remarkDAO.queryPageCount(param);
		if (pageBean != null) {
			pageBean.setPageSize(10);
			currPageBean
					.init(pageBean.getCurrentPage(), pageBean.getPageSize());
			if (currPageBean.getCurrentPage() != 1) {
				currPageBean.setEndCount(currPageBean.getEndCount() - 1);
			}
			currPageBean.setParam(param);
			remarkList = remarkDAO.queryRemarkByPage(currPageBean);

		} else {
			currPageBean.init(currPageBean.getCurrentPage(), currPageBean
					.getPageSize());
			currPageBean.setEndCount(10);
			currPageBean.setPageSize(10);
			int totalPage = count % currPageBean.getPageSize() == 0 ? count
					/ currPageBean.getPageSize() : count
					/ currPageBean.getPageSize() + 1;
			currPageBean.setTotalPage(totalPage);
			currPageBean.setParam(param);
			remarkList = remarkDAO.queryRemarkByPage(currPageBean);


		}
		currPageBean.setTotal(count);
		request.setAttribute("type", type);
		pageBean = currPageBean;
		
		super.writeToJson(remarkList);
		
	}
}
