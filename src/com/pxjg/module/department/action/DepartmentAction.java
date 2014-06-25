package com.pxjg.module.department.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pxjg.module.department.dao.IDepartmentDAO;
import com.pxjg.module.department.entity.Department;
import com.pxjg.module.depttype.dao.IDeptTypeDAO;
import com.pxjg.module.depttype.entity.DeptType;
import com.pxjg.module.region.dao.IRegionDAO;
import com.pxjg.module.region.entity.Region;
import com.pxjg.module.user.entity.User;
import com.pxjg.util.PageBean;
import com.pxjg.util.UploadImage;

public class DepartmentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Region pageBean;
	private PageBean pageBean1;
	private List<Department> deptList;
	private Department dept;
	private IDepartmentDAO deptDAO;
	private IRegionDAO  regionDAO;
	private File myFile; 
	private String myFileContentType;  
	private String myFileFileName; 
	private IDeptTypeDAO deptTypeDAO;
	private List<DeptType> deptTypeList;
	
	public IDeptTypeDAO getDeptTypeDAO() {
		return deptTypeDAO;
	}
	public void setDeptTypeDAO(IDeptTypeDAO deptTypeDAO) {
		this.deptTypeDAO = deptTypeDAO;
	}
	public List<DeptType> getDeptTypeList() {
		return deptTypeList;
	}
	public void setDeptTypeList(List<DeptType> deptTypeList) {
		this.deptTypeList = deptTypeList;
	}
	public Region getPageBean() {
		return pageBean;
	}
	public void setPageBean(Region pageBean) {
		this.pageBean = pageBean;
	}
	public List<Department> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public IDepartmentDAO getDeptDAO() {
		return deptDAO;
	}
	public void setDeptDAO(IDepartmentDAO deptDAO) {
		this.deptDAO = deptDAO;
	}
	
	public IRegionDAO getRegionDAO() {
		return regionDAO;
	}
	public void setRegionDAO(IRegionDAO regionDAO) {
		this.regionDAO = regionDAO;
	}
	
	
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	public String getMyFileContentType() {
		return myFileContentType;
	}
	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}
	public String getMyFileFileName() {
		return myFileFileName;
	}
	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}
	
	
	
	public PageBean getPageBean1() {
		return pageBean1;
	}
	public void setPageBean1(PageBean pageBean1) {
		this.pageBean1 = pageBean1;
	}
	public String queryDeptByShangQuan(String shangquanid){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(shangquanid==null){
			shangquanid = request.getParameter("re_id");
		}
		deptList = deptDAO.queryDeptBySQ(shangquanid);
		request.setAttribute("shangquanid", shangquanid);
		return "deptList";
	}
	
	public String queryDeptByShangQuan(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String	shangquanid = request.getParameter("re_id");
		deptList = deptDAO.queryDeptBySQ(shangquanid);
		request.setAttribute("shangquanid", shangquanid);
		return "deptList";
	}
		
	public String goAddDept(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String shangquanid = request.getParameter("shangquanid");
		deptTypeList = deptTypeDAO.queryDeptTypeListTree();
		request.setAttribute("shangquanid", shangquanid);
		return "addDept";
	}
	
	public String saveDept() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		if(myFile!=null){
			String imagepath = UploadImage.uploadImage(myFile, myFileFileName, null);
		
			dept.setUrl(imagepath);
		}
		String content = request.getParameter("fileUpload");
		Region shangquanRegion = regionDAO.queryRegionById(dept.getShangquanid());
		Region quxianRegion = regionDAO.queryRegionById(shangquanRegion.getParent_id());
		Region cityRegion = regionDAO.queryRegionById(quxianRegion.getParent_id());
		dept.setQuxianid(quxianRegion.getRe_id());
		dept.setCityid(cityRegion.getRe_id());
		dept.setId(UUID.randomUUID().toString());
		dept.setDescription(content);
		dept.setState(1);
		int i = deptDAO.addDept(dept);
		if(i==1){
			out = response.getWriter();
			out.print("<script>alert('添加成功！')</script>"); 
			out.flush();
			return queryDeptByShangQuan(dept.getShangquanid());
		}else{
			out = response.getWriter();
			out.print("<script>alert('添加失败！')</script>");
			out.flush();
			return null;
		}
	}
	
	public void updateDeptState(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String state = request.getParameter("state");
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		int result = 0;
		try {
			out = response.getWriter();
			result = deptDAO.updateDeptState(id, Integer.parseInt(state));
			out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null)
				out.close();
		}
	}
	
	public String updateDept() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("userBean");
		String content = request.getParameter("fileUpload");
		PrintWriter out = null;
		if(myFile!=null){
			String imagepath = UploadImage.uploadImage(myFile, myFileFileName, dept.getUrl());
			
			
			
			dept.setUrl(imagepath);
		}
		if(user.getRole()==1){
			dept.setC1("0");
		}else{
			dept.setC1("1");
		}
		dept.setDescription(content);
		int i = deptDAO.updateDept(dept);
		if(i==1){
			out = response.getWriter();
			out.print("<script>alert('修改成功！')</script>");
			out.flush();
			if(user.getRole()==1){
				return detailDept();
			}else{
				return queryDeptByShangQuan(dept.getShangquanid());
			}
			
		}else{
			out = response.getWriter();
			out.print("<script>alert('修改失败！')</script>");
			out.flush();
			return null;
		}
	}
	
	public String goUpdateDept(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		dept = deptDAO.queryDeptById(id);
		deptTypeList = deptTypeDAO.queryDeptTypeListTree();
		return "updateDept";
	}
	
	public String detailDept(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("userBean");
		dept = deptDAO.queryDeptById(user.getDeptid());
		deptTypeList = deptTypeDAO.queryDeptTypeListTree();
		return "detailDept";
		
	}
	
	public String rank(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//查询机构收藏还是课程收藏
		PageBean currPageBean=new PageBean();
		int count = deptDAO.queryRankDeptCount();
		if (pageBean1 != null) {
			pageBean1.setPageSize(10);
			currPageBean
					.init(pageBean1.getCurrentPage(), pageBean1.getPageSize());
			if (currPageBean.getCurrentPage() != 1) {
				currPageBean.setEndCount(currPageBean.getEndCount() - 1);
			}
			deptList = deptDAO.queryRank(currPageBean);

		} else {
			currPageBean.init(currPageBean.getCurrentPage(), currPageBean
					.getPageSize());
			currPageBean.setEndCount(10);
			currPageBean.setPageSize(10);
			int totalPage = count % currPageBean.getPageSize() == 0 ? count
					/ currPageBean.getPageSize() : count
					/ currPageBean.getPageSize() + 1;
			currPageBean.setTotalPage(totalPage);
			deptList = deptDAO.queryRank(currPageBean);


		}
		currPageBean.setTotal(count);
		pageBean1 = currPageBean;
		return "rank";
	}
	
	public void toTop() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String top = request.getParameter("top");
		int result = deptDAO.toTop(id, top);
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
}
