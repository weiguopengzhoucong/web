package com.pxjg.module.user.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pxjg.module.user.dao.IUserDAO;
import com.pxjg.module.user.entity.User;
import com.pxjg.util.PageBean;



public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private PageBean     pageBean;
	private IUserDAO     userDAO; 
	private List<User>   uList;
	private User 		 user;
	
	
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<User> getUList() {
		return uList;
	}

	public void setUList(List<User> list) {
		uList = list;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * 返回登陆页
	 * @return
	 */
	public String loginPage(){
		return "loginPage";
	}

	/**
	 * 登录验证,登录成功将实体保存在session中userBean
	 * 
	 * @return
	 */
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Map<String, Object> map = userDAO.login(user);
			if (map.get("reFlag").equals("1")) { // 1-登陆成功，2-用户名或密码错误，3-没有权限
				User user = (User) map.get("bean");
				request.getSession().setAttribute("userBean", user);
				out.print("1");
				return null;
			} else if (map.get("reFlag").equals("2")) {
				request.setAttribute("message", "用户名或密码错误");
				out.print("0");
				return null;
			} else{
				out.print("3");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(out!=null)
				out.close();
		}
		return null;
	}
	
	public String indexAction(){
		return "main";
	}
	
	public void logout(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();
		String path = request.getContextPath();
		try {
			String CONTENT_TYPE = "text/html; charset=GBK";
			response.setContentType(CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.println("<html>");   
		    out.println("<script>");   
		    out.println("window.open ('"+path+"/userAction!loginPage.do','_top')");  
		    out.println("</script>");   
		    out.println("</html>"); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String userList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("user_name");
		String role = request.getParameter("role");
		String param = "";
		if(role!=null){
			param = " and role = "+role;
		}
		if (username != null && !"".equals(username.trim())) {
			param = param + " and user_name like '%" + username + "%'";
		}
		if(pageBean!=null&&pageBean.getParam()!=null){
			param = pageBean.getParam();
		}
		PageBean currPageBean=new PageBean();
		int count = userDAO.queryCountByParam(param);
		if (pageBean != null) {
			pageBean.setPageSize(10);
			currPageBean
					.init(pageBean.getCurrentPage(), pageBean.getPageSize());
			if (currPageBean.getCurrentPage() != 1) {
				currPageBean.setEndCount(currPageBean.getEndCount() - 1);
			}
			currPageBean.setParam(param);
			uList = userDAO.queryUserByParam(currPageBean);

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
			uList = userDAO.queryUserByParam(currPageBean);


		}

		currPageBean.setTotal(count);
		request.setAttribute("role", role);
		pageBean = currPageBean;
		return "userList";
	}
		
	public String queryDeptUserList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String deptid = request.getParameter("deptid");
		uList = userDAO.queryUserByDeptId(deptid);
		request.setAttribute("deptid", deptid);
		return "deptUserList";
	}
	
	public String goAddDeptUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String deptid = request.getParameter("deptid");
		request.setAttribute("deptid", deptid);
		return "addUser";
	}
	
	public void addDeptUser(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		int result = 0;
		user.setUser_id(UUID.randomUUID().toString());
		user.setRole(1);
		user.setPassword("123");
		user.setStatus(0);
		try {
			out = response.getWriter();
			User userTmp = userDAO.queryUsersByUsername(user.getUser_name());
			if(userTmp!=null){
				out.print("2");
				return;
			}
			result = userDAO.insertUsers(user);
			out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null)
				out.close();
		}
	}
	
	public void updateUserState(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		int result = 0;
		try {
			out = response.getWriter();
			result = userDAO.updateUserState(id, Integer.parseInt(status));
			if(result==1){
				out.print("操作成功");
			}else{
				out.print("操作失败");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null)
				out.close();
		}
	}
	
	public String updatePassword(){
		HttpServletRequest request = ServletActionContext.getRequest();
		user = (User)request.getSession().getAttribute("userBean");
		return "updatePassword";
	}
	
	public String savePassword(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		user = (User)request.getSession().getAttribute("userBean");
		String password = request.getParameter("user.password");
		try {
			user.setPassword(password);
			out = response.getWriter();
			int result = userDAO.updatePassword(user);
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
		return null;
	}
}
