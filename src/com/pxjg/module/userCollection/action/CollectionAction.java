package com.pxjg.module.userCollection.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.pxjg.module.front.BaseAction;
import com.pxjg.module.user.entity.User;
import com.pxjg.module.userCollection.dao.ICollectionDAO;
import com.pxjg.module.userCollection.entity.UserCollection;
import com.pxjg.util.PageBean;

public class CollectionAction extends BaseAction {

	private PageBean     pageBean;
	private ICollectionDAO collectionDAO;
	private List<UserCollection> cList;
	private UserCollection uc;
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public ICollectionDAO getCollectionDAO() {
		return collectionDAO;
	}
	public void setCollectionDAO(ICollectionDAO collectionDAO) {
		this.collectionDAO = collectionDAO;
	}
	public List<UserCollection> getCList() {
		return cList;
	}
	public void setCList(List<UserCollection> list) {
		cList = list;
	}
	public UserCollection getUc() {
		return uc;
	}
	public void setUc(UserCollection uc) {
		this.uc = uc;
	}
	
	public String ucList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//查询机构收藏还是课程收藏
		String type = request.getParameter("type");
		User user = (User)request.getSession().getAttribute("userBean");
		String param = "";
		param = " and userid='"+user.getUser_id()+"' and type='"+type+"'";
		
		if(pageBean!=null&&pageBean.getParam()!=null){
			param = pageBean.getParam();
		}
		PageBean currPageBean=new PageBean();
		int count = collectionDAO.queryPageCount(param);
		if (pageBean != null) {
			pageBean.setPageSize(10);
			currPageBean
					.init(pageBean.getCurrentPage(), pageBean.getPageSize());
			if (currPageBean.getCurrentPage() != 1) {
				currPageBean.setEndCount(currPageBean.getEndCount() - 1);
			}
			currPageBean.setParam(param);
			cList = collectionDAO.queryCollectionByPage(currPageBean);

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
			cList = collectionDAO.queryCollectionByPage(currPageBean);


		}
		currPageBean.setTotal(count);
		request.setAttribute("type", type);
		pageBean = currPageBean;
		if(type.equals("课程")){
			return "course_collection";
		}else{
			return "dept_collection";
		}
	}
	
	public void insertCollection() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		User userBean = (User)request.getSession().getAttribute("userBean");
		String userid = "";
		if(userBean!=null){
			userid = userBean.getUser_id();
		}else{
			super.writeToJson(0);
		}
		String deptid = request.getParameter("deptid");
		String cid = request.getParameter("cid");
		uc = new UserCollection();
		uc.setId(UUID.randomUUID().toString());
		uc.setUserid(userid);
		uc.setDeptid(deptid);
		uc.setType(type);
		uc.setCid(cid);
		int result = collectionDAO.insertCollection(uc);
		super.writeToJson(result);
	}
	
	public void deleteCollection() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		int result = collectionDAO.deleteCollection(id);
		super.writeToJson(result);
		
	}
}
