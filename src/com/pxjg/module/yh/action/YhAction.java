package com.pxjg.module.yh.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pxjg.module.yh.dao.IYhDAO;
import com.pxjg.module.yh.entity.Yh;
import com.pxjg.util.DateUtil;
import com.pxjg.util.PageBean;



public class YhAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private PageBean     pageBean;
	private IYhDAO     yhDAO; 
	private List<Yh>   yhList;
	private Yh 		 yh;
	
	
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
	public IYhDAO getYhDAO() {
		return yhDAO;
	}

	public void setYhDAO(IYhDAO yhDAO) {
		this.yhDAO = yhDAO;
	}

	public List<Yh> getYhList() {
		return yhList;
	}

	public void setYhList(List<Yh> yhList) {
		this.yhList = yhList;
	}

	public Yh getYh() {
		return yh;
	}

	public void setYh(Yh yh) {
		this.yh = yh;
	}

	
	public String yhList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String deptorcourse = request.getParameter("deptorcourse");
		String deptorcourseid = request.getParameter("deptorcourseid");
		String param = "";
		param = " and deptorcourse="+deptorcourse+" and deptorcourseid='"+deptorcourseid+"'";
		if(pageBean!=null&&pageBean.getParam()!=null){
			param = pageBean.getParam();
		}
		PageBean currPageBean=new PageBean();
		int count = yhDAO.queryCountByParam(param);
		if (pageBean != null) {
			pageBean.setPageSize(10);
			currPageBean
					.init(pageBean.getCurrentPage(), pageBean.getPageSize());
			if (currPageBean.getCurrentPage() != 1) {
				currPageBean.setEndCount(currPageBean.getEndCount() - 1);
			}
			currPageBean.setParam(param);
			yhList = yhDAO.queryYhByParam(currPageBean);

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
			yhList = yhDAO.queryYhByParam(currPageBean);


		}
		currPageBean.setTotal(count);
		request.setAttribute("deptorcourse", deptorcourse);
		request.setAttribute("deptorcourseid", deptorcourseid);
		pageBean = currPageBean;
		return "yhList";
	}
		
	
	
	public String goAddYh(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String deptorcourse = request.getParameter("deptorcourse");
		String deptorcourseid = request.getParameter("deptorcourseid");
		request.setAttribute("deptorcourse", deptorcourse);
		request.setAttribute("deptorcourseid", deptorcourseid);
		return "addYh";
	}
	
	public String goUpdateYh(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		yh = yhDAO.queryYhById(id);
		String startdate = DateUtil.formatDate(yh.getStartdate());
		String enddate = DateUtil.formatDate(yh.getEnddate());
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		return "updateYh";
	}
	
	
	public void openOrClose(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String state = request.getParameter("state");
		String deptorcourseid = request.getParameter("deptorcourseid");
		String id = request.getParameter("id");
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		int result = 0;
		try {
			out = response.getWriter();
			//激活,查询当前已激活数量
			if(state.equals("1")){
				String param = " and deptorcourseid='"+deptorcourseid+"' and state=1";
				int count = yhDAO.queryCountByParam(param);
				if(count>0){
					out.print("2");
					return;
				}
			}
			result = yhDAO.openOrClose(id, Integer.parseInt(state));
			if(result==1){
				out.print("1");
			}else{
				out.print("0");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null)
				out.close();
		}
	}
	
	public void addYh(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		int result = 0;
		yh.setId(UUID.randomUUID().toString());
		yh.setState(0);
		yh.setDelflag(0);
		try {
			out = response.getWriter();
			result = yhDAO.insertYh(yh);
			out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null)
				out.close();
		}
	}
	
	public void updateYh(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		int result = 0;
		try {
			out = response.getWriter();
			result = yhDAO.updateYh(yh);
			out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null)
				out.close();
		}
	}
	
	public void deleteYh(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		int result = 0;
		try {
			out = response.getWriter();
			result = yhDAO.deleteYh(id);
			out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null)
				out.close();
		}
	}
}
