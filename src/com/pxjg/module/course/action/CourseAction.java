package com.pxjg.module.course.action;


import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.pxjg.module.course.dao.ICourseDAO;
import com.pxjg.module.course.entity.Course;
import com.pxjg.module.news.entity.News;
import com.pxjg.module.user.entity.User;
import com.pxjg.util.PageBean;
import com.pxjg.util.UploadImage;



public class CourseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private PageBean     pageBean;
	private ICourseDAO     courseDAO; 
	private List<Course>   nList;
	private Course 		 course;

	
	private File myFile; 
	private String myFileContentType;  
	private String myFileFileName; 
	
	
	
	public PageBean getPageBean() {
		return pageBean;
	}



	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}



	public ICourseDAO getCourseDAO() {
		return courseDAO;
	}



	public void setCourseDAO(ICourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}



	public List<Course> getnList() {
		return nList;
	}



	public void setnList(List<Course> nList) {
		this.nList = nList;
	}



	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
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



	/**
	 * 校区环境列表
	 * @return
	 */
	
	public String courseList(){
		PageBean currPageBean=new PageBean();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("userBean");
		
		if(user!=null){
			
			String  search = request.getParameter("search");
		
		int count = courseDAO.queryCountByDeptid(user.getDeptid(),search);
			if(pageBean!=null){  
				pageBean.setPageSize(10);
				currPageBean.init(pageBean.getCurrentPage(), pageBean.getPageSize());
				if(currPageBean.getCurrentPage()!=1){
					currPageBean.setEndCount(currPageBean.getEndCount()-1);
				}
				
				nList = courseDAO.queryCourseByDeptid(currPageBean,user.getDeptid(),search);
				
				
			}else{
				currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
				currPageBean.setEndCount(10);
				currPageBean.setPageSize(10);
				int totalPage = count%currPageBean.getPageSize()==0?count/currPageBean.getPageSize():count/currPageBean.getPageSize()+1;
				currPageBean.setTotalPage(totalPage);
				
				nList = courseDAO.queryCourseByDeptid(currPageBean,user.getDeptid(),search);
				
			}
			
			currPageBean.setTotal(count);
		
		}
		
		pageBean=currPageBean;
		return "course_list";
	}
	
	
	
	public String addCourse(){
		return "add_course";
	}
	
	public String updateCourseById(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		try {
			
			String id = request.getParameter("id");
			
			if(StringUtils.hasText(id)){
				
				Course cnews = courseDAO.queryCourseByid(id);
				if (cnews != null) { 
					request.setAttribute("course", cnews);
				}  
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "update_course";
	}
	
	
	public String insertCourse(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		User user = (User)request.getSession().getAttribute("userBean");
		
		if(user!=null){
			course.setId(UUID.randomUUID().toString());
			course.setDeptid(user.getDeptid());
			course.setHappentime(new Date());
			course.setNum(0);
			course.setState(0);
			
			if(myFile!=null){
				String imagepath = UploadImage.uploadImage(myFile, myFileFileName, null);
				course.setUrl(imagepath);
			}
			
		}
		
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
		int iflag = courseDAO.addCourse(course);
			if (iflag == 1) {
				out.println("<html>");   
				out.println("<script>");   
				out.println("alert('保存成功')");
				out.println("</script>");   
				out.println("</html>"); 
				out.flush();
			}  else{
				out.println("<html>");   
				out.println("<script>");   
				out.println("alert('保存失败')");
				out.println("history.go(-1)");
				out.println("</script>");   
				out.println("</html>"); 
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return courseList();
	}

	
	
	public String updateCourse(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
		int iflag = courseDAO.updateCourse(course);
			if (iflag == 1) { 
				out.println("<html>");   
				out.println("<script>");   
				out.println("alert('更新成功')");
				out.println("</script>");   
				out.println("</html>"); 
				out.flush();
			}  else{
				out.println("<html>");   
				out.println("<script>");   
				out.println("alert('更新失败')");
				out.println("history.go(-1)");
				out.println("</script>");   
				out.println("</html>"); 
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courseList();
	}
	
	
	public String deleteCourse(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			
			String id = request.getParameter("id");
			out = response.getWriter();
			if(StringUtils.hasText(id)){
				
				
				int iflag = courseDAO.deleteCourse(id);
					if (iflag == 1) { 
						out.println("<html>");   
						out.println("<script>");   
						out.println("alert('删除成功')");
						out.println("</script>");   
						out.println("</html>"); 
						out.flush();
					}  else{
						out.println("<html>");   
						out.println("<script>");   
						out.println("alert('删除失败')");
						out.println("history.go(-1)");
						out.println("</script>");   
						out.println("</html>"); 
						out.flush();
					}
					
			}else{
				out.println("<html>");   
				out.println("<script>");   
				out.println("alert('删除失败')");
				out.println("</script>");   
				out.println("</html>"); 
				out.flush();
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return courseList();
	}

	
	
}
