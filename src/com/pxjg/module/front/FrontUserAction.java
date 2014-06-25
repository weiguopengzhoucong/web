package com.pxjg.module.front;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.pxjg.base.dao.IBaseDataDAO;
import com.pxjg.module.department.dao.IDepartmentDAO;
import com.pxjg.module.department.entity.Department;
import com.pxjg.module.region.dao.IRegionDAO;
import com.pxjg.module.region.entity.Region;
import com.pxjg.module.user.dao.IUserDAO;
import com.pxjg.module.user.entity.User;
import com.pxjg.util.DateUtil;
import com.pxjg.util.Json;
import com.pxjg.util.PageBean;
import com.pxjg.util.mail.JavaMail;



public class FrontUserAction extends BaseAction {

	private IRegionDAO regionDAO;
	private IUserDAO userDAO;
	private IBaseDataDAO baseDataDAO;
	private User user;
	private Region region;
	private List<Department>   nList;
	private IDepartmentDAO deptDAO;
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public IRegionDAO getRegionDAO() {
		return regionDAO;
	}
	public void setRegionDAO(IRegionDAO regionDAO) {
		this.regionDAO = regionDAO;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public List<Department> getNList() {
		return nList;
	}
	public void setNList(List<Department> list) {
		nList = list;
	}
	public IDepartmentDAO getDeptDAO() {
		return deptDAO;
	}
	public void setDeptDAO(IDepartmentDAO deptDAO) {
		this.deptDAO = deptDAO;
	}
	public IBaseDataDAO getBaseDataDAO() {
		return baseDataDAO;
	}
	public void setBaseDataDAO(IBaseDataDAO baseDataDAO) {
		this.baseDataDAO = baseDataDAO;
	}
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Map<String, Object> map = userDAO.frontLogin(user);
			if (map.get("reFlag").equals("1")) { // 1-登陆成功，2-用户名或密码错误，3-没有权限
				user = (User) map.get("bean");
				//未激活
				if(user.getStatus()==1){
					//提示激活页面
					request.setAttribute("username", user.getUser_name());
					return "jihuo_user";
				}
				request.getSession().setAttribute("userBean", user);
				
				return forward();
			} else{
				out.println("<html>");   
				out.println("<script>");   
				out.println("alert('用户名或密码错误')");
				out.println("</script>");   
				out.println("</html>"); 
				out.flush();
				return forward();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return forward();
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
		    out.println("window.open ('"+path+"/IndexAction!forward.do','_top')");  
		    out.println("</script>");   
		    out.println("</html>"); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reSendEmail(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		
		user = userDAO.queryUsersByUsername(username);
		
		try {
		
		
			//邮件接货链接
			String url = "http://"+request.getServerName()+":"+request.getServerPort()+"/web/"+"frontUserAction!updateUserState.do?id="+user.getUser_id();
			
			JavaMail.sendMail(user.getUser_name(),url );
			out = response.getWriter();
			out.print("1");
			out.flush();
			out.close();
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			out.print("0");
			out.flush();
			out.close();
		}
	}
	
	public String updateUser() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		user.setHobby(user.getHobby().trim());
		int result = userDAO.updateFrontUser(user);
		if(result>0){
			out = response.getWriter();
			out.print("1");
			
		}else{
			out = response.getWriter();
			out.print("0");
		}
		request.getSession().setAttribute("userBean", user);
		if(out!=null)
			out.close();
		return mySpace();
		
	}
	
	public String register(){
		return "register";
	}
	
	public void saveRegister(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			
			out = response.getWriter();
			User tmpUser = userDAO.queryUsersByUsername(user.getUser_name());
			if(tmpUser!=null){
				out.print("2");
			}
			user.setUser_id(UUID.randomUUID().toString());
			user.setStatus(1);
			int result = userDAO.insertUsers(user);
			if(result==1){
				//发送注册邮件
				//邮件接货链接
				String url = "http://"+request.getServerName()+":"+request.getServerPort()+"/web/"+"frontUserAction!updateUserState.do?id="+user.getUser_id();
				
				JavaMail.sendMail(user.getUser_name(),url );
				out.print("1");
			}else{
				out.print("0");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(out!=null)
				out.close();
		}
	}
	
	public String updateUserState(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String id = request.getParameter("id");
		
		if(StringUtils.hasText(id)){
			
			int ire = userDAO.updateUserState(id, 0);
			if(ire==1){
				user = userDAO.queryUsersById(id);
				request.getSession().setAttribute("userBean", user);
				return "index";
			}
			
		}else{
			
			return "jihuo_error";
		}
		
		
		return "jihuo_error";
	}
	
	public String forward(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
			
			region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
			
			if(region==null){
				
				region = regionDAO.getRegionByName("北京");
				request.getSession().setAttribute(Context.CURRENT_CITY,region);
				
			}
			
			String _value = request.getParameter("_value");
			
			if(StringUtils.hasText(_value)){
				
				if("index".equals(_value)){
					PageBean currPageBean=new PageBean();
					currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
					currPageBean.setEndCount(5);
					
					nList = deptDAO.queryDeptBySearch(currPageBean,region.getRe_id(),"机构","","");
					return _value;
					
				}else if("dept_list".equals(_value)){
					
					return _value;
				}else  if("map".equals(_value)){
					
					return _value;
				}else  if("jihuo_user".equals(_value)){
					String username = request.getParameter("username");
					request.setAttribute("username",username);
					return _value;
				}
				
			}
			
			return "index";
		}
	
	public String mySpace(){
		HttpServletRequest request = ServletActionContext.getRequest();
		user = (User)request.getSession().getAttribute("userBean");
		List<String> xzList = baseDataDAO.queryXingZuo();
		request.setAttribute("xzList", xzList);
		if(user.getBirthday()!=null){
			String birthday = DateUtil.formatDate(user.getBirthday());
			request.setAttribute("birthday", birthday);
		}
		return "updateUser";
	}
	
	public String updatePassword(){
		HttpServletRequest request = ServletActionContext.getRequest();
		user = (User)request.getSession().getAttribute("userBean");
		return "updatePassword";
	}
	
	public String savePassword() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String userid = request.getParameter("user_id");
		String password = request.getParameter("password");
		user = userDAO.queryUsersById(userid);
		user.setPassword(password);
		int result = userDAO.updatePassword(user);
		request.getSession().setAttribute("userBean", user);
		if(result>0){
			out = response.getWriter();
			out.println("<html>");   
			out.println("<script>");   
			out.println("alert('修改成功')");
			out.println("</script>");   
			out.println("</html>"); 
			out.flush();
		}else{
			out = response.getWriter();
			out.println("<html>");   
			out.println("<script>");   
			out.println("alert('修改失败')");
			out.println("</script>");   
			out.println("</html>"); 
			out.flush();
		}
		return "updatePassword";
	}
}
