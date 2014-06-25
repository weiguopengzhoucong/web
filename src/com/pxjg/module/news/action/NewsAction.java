package com.pxjg.module.news.action;


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
import com.pxjg.module.front.Context;
import com.pxjg.module.news.dao.INewsDAO;
import com.pxjg.module.news.entity.News;
import com.pxjg.module.user.entity.User;
import com.pxjg.util.PageBean;
import com.pxjg.util.UploadImage;



public class NewsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private PageBean     pageBean;
	private INewsDAO     newsDAO; 
	private List<News>   nList;
	private News 		 news;

	

	
	private File myFile; 
	private String myFileContentType;  
	private String myFileFileName; 
	
	
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public INewsDAO getNewsDAO() {
		return newsDAO;
	}

	public void setNewsDAO(INewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	public List<News> getnList() {
		return nList;
	}

	public void setnList(List<News> nList) {
		this.nList = nList;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	
	/**
	 * 校区环境列表
	 * @return
	 */
	
	public String teacherNewsList(){
		PageBean currPageBean=new PageBean();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("userBean");
		
		if(user!=null){
			
			String  search = request.getParameter("search");
		
		int count = newsDAO.queryCountByDeptid(user.getDeptid(),Context.教师简介新闻,search);
			if(pageBean!=null){  
				pageBean.setPageSize(10);
				currPageBean.init(pageBean.getCurrentPage(), pageBean.getPageSize());
				if(currPageBean.getCurrentPage()!=1){
					currPageBean.setEndCount(currPageBean.getEndCount()-1);
				}
				
				nList = newsDAO.queryNewsByDeptId(currPageBean,user.getDeptid(),Context.教师简介新闻,search);
				
				
			}else{
				currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
				currPageBean.setEndCount(10);
				currPageBean.setPageSize(10);
				int totalPage = count%currPageBean.getPageSize()==0?count/currPageBean.getPageSize():count/currPageBean.getPageSize()+1;
				currPageBean.setTotalPage(totalPage);
				
				nList = newsDAO.queryNewsByDeptId(currPageBean,user.getDeptid(),Context.教师简介新闻,search);
				
			}
			
			currPageBean.setTotal(count);
		
		}
		
		pageBean=currPageBean;
		return "news_teacher_list";
	}
	
	
	
	public String addTeacherNews(){
		return "add_teacher_news";
	}
	
	public String updateTeacherNewsById(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		try {
			
			String id = request.getParameter("id");
			
			if(StringUtils.hasText(id)){
				
				News cnews = newsDAO.queryNewsById(id);
				if (cnews != null) { 
					request.setAttribute("news", cnews);
				}  
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "update_teacher_news";
	}
	
	
	public String insertTeacherNews(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		User user = (User)request.getSession().getAttribute("userBean");
		
		if(user!=null){
			news.setId(UUID.randomUUID().toString());
			news.setDeptid(user.getDeptid());
			news.setUserid(user.getUser_id());
			news.setFtime(new Date());
			news.setNum(0);
			news.setState(0);
			news.setTop(0);
			
			if(myFile!=null){
				String imagepath = UploadImage.uploadImage(myFile, myFileFileName, null);
				news.setUrl(imagepath);
			}
			
		}
		
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
		int iflag = newsDAO.insertNews(news);
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
		return teacherNewsList();
	}

	
	
	public String updateTeacherNews(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
		int iflag = newsDAO.updateNews(news);
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

		return teacherNewsList();
	}
	
	
	public String deleteTeacher(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			
			String id = request.getParameter("id");
			out = response.getWriter();
			if(StringUtils.hasText(id)){
				
				
				int iflag = newsDAO.deleteNews(id);
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
		return teacherNewsList();
	}


	
	
	/**
	 * ----------------------------------------------------------------------------------------------------------------------------
	 * 平台新闻
	 * @return
	 */
	public String newsList(){
		
		PageBean currPageBean=new PageBean();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("userBean");
		
		if(user!=null){
			
		String  search = request.getParameter("search");
		
		int count = newsDAO.queryCount(Context.平台新闻,search);
			if(pageBean!=null){
				pageBean.setPageSize(10);
				currPageBean.init(pageBean.getCurrentPage(), pageBean.getPageSize());
				if(currPageBean.getCurrentPage()!=1){
					currPageBean.setEndCount(currPageBean.getEndCount()-1);
				}
				
				nList = newsDAO.queryNews(currPageBean,Context.平台新闻,search);
				
				
			}else{
				currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
				currPageBean.setEndCount(10);
				currPageBean.setPageSize(10);
				int totalPage = count%currPageBean.getPageSize()==0?count/currPageBean.getPageSize():count/currPageBean.getPageSize()+1;
				currPageBean.setTotalPage(totalPage);
				
				nList = newsDAO.queryNews(currPageBean,Context.平台新闻,search);
				
			}
			
			currPageBean.setTotal(count);
		
		}
		
		pageBean=currPageBean;
		
		return "news_list";
	}
	
	public String addNews(){
		return "addnews";
	}
	
	public String updateNews(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		try {
			
			String id = request.getParameter("id");
			
			if(StringUtils.hasText(id)){
				
				News cnews = newsDAO.queryNewsById(id);
				if (cnews != null) { 
					request.setAttribute("news", cnews);
				}  
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "updatenews";
	}
	
	
	public String insert(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		User user = (User)request.getSession().getAttribute("userBean");
		
		if(user!=null){
			news.setId(UUID.randomUUID().toString());
			news.setDeptid(user.getDeptid());
			news.setUserid(user.getUser_id());
			news.setFtime(new Date());
			news.setNum(0);
			news.setState(0);
			news.setTop(0);
			
			if(myFile!=null){
				String imagepath = UploadImage.uploadImage(myFile, myFileFileName, null);
				news.setUrl(imagepath);
			}
			
		}
		
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
		int iflag = newsDAO.insertNews(news);
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
		return newsList();
	}

	
	
	public String update(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
		int iflag = newsDAO.updateNews(news);
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

		return newsList();
	}
	
	
	public String delete(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			
			String id = request.getParameter("id");
			out = response.getWriter();
			if(StringUtils.hasText(id)){
				int iflag = newsDAO.deleteNews(id);
					if (iflag == 1) { 
						out.println("1");   
					}  else{
						out.println("0");   
					}
					
			}else{
				out.println("0");   
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} if(out!=null)
			out.close();
		return newsList();
	}
	
	public void openOrClose(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			
			String id = request.getParameter("id");
			String state = request.getParameter("state");
			out = response.getWriter();
			if(StringUtils.hasText(id)){
				int iflag = newsDAO.openOrClose(id,Integer.parseInt(state));
					if (iflag == 1) { 
						out.println("1");   
					}  else{
						out.println("0");   
					}
					
			}else{
				out.println("0");   
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} if(out!=null)
			out.close();
	}

	
	/**
	 * ----------------------------------------------------------------------------------------
	 * @return
	 */
	

	/**
	 * 新闻列表
	 * @return
	 */
	

	/**
	 * 校区环境列表
	 * @return
	 */
	
	public String schoolNewsList(){
		PageBean currPageBean=new PageBean();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("userBean");
		
		if(user!=null){
			String  search = request.getParameter("search");
		int count = newsDAO.queryCountByDeptid(user.getDeptid(),Context.校区环境新闻,search);
			if(pageBean!=null){  
				pageBean.setPageSize(10);
				currPageBean.init(pageBean.getCurrentPage(), pageBean.getPageSize());
				if(currPageBean.getCurrentPage()!=1){
					currPageBean.setEndCount(currPageBean.getEndCount()-1);
				}
				
				nList = newsDAO.queryNewsByDeptId(currPageBean,user.getDeptid(),Context.校区环境新闻,search);
				
				
			}else{
				currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
				currPageBean.setEndCount(10);
				currPageBean.setPageSize(10);
				int totalPage = count%currPageBean.getPageSize()==0?count/currPageBean.getPageSize():count/currPageBean.getPageSize()+1;
				currPageBean.setTotalPage(totalPage);
				
				nList = newsDAO.queryNewsByDeptId(currPageBean,user.getDeptid(),Context.校区环境新闻,search);
				
			}
			
			currPageBean.setTotal(count);
		
		}
		
		pageBean=currPageBean;
		return "news_school_list";
	}
	
	
	public String addSchoolNews(){
		return "add_school_news";
	}
	
	public String updateSchoolNewsById(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		try {
			
			String id = request.getParameter("id");
			
			if(StringUtils.hasText(id)){
				
				News cnews = newsDAO.queryNewsById(id);
				if (cnews != null) { 
					request.setAttribute("news", cnews);
				}  
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "update_school_news";
	}
	
	
	public String insertSchoolNews(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		User user = (User)request.getSession().getAttribute("userBean");
		
		if(user!=null){
			news.setId(UUID.randomUUID().toString());
			news.setDeptid(user.getDeptid());
			news.setUserid(user.getUser_id());
			news.setFtime(new Date());
			news.setNum(0);
			news.setState(0);
			news.setTop(0);
			
			
			if(myFile!=null){
				String imagepath = UploadImage.uploadImage(myFile, myFileFileName, null);
				news.setUrl(imagepath);
			}
			
		}
		
		
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
		int iflag = newsDAO.insertNews(news);
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
		return schoolNewsList();
	}

	
	
	public String updateSchoolNews(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
		int iflag = newsDAO.updateNews(news);
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

		return schoolNewsList();
	}
	
	
	public String deleteSchoolNews(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			
			String id = request.getParameter("id");
			out = response.getWriter();
			if(StringUtils.hasText(id)){
				
				
				int iflag = newsDAO.deleteNews(id);
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
		return schoolNewsList();
	}
	
	

	/**
	 * ----------------------------------------------------------------------------------------
	 * @return
	 */
	

	/**
	 * 新闻列表
	 * @return
	 */
	
	public String newsDeptList(){
		PageBean currPageBean=new PageBean();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("userBean");
		
		if(user!=null){
			String  search = request.getParameter("search");
		int count = newsDAO.queryCountByDeptid(user.getDeptid(),Context.机构新闻,search);
			if(pageBean!=null){  
				pageBean.setPageSize(10);
				currPageBean.init(pageBean.getCurrentPage(), pageBean.getPageSize());
				if(currPageBean.getCurrentPage()!=1){
					currPageBean.setEndCount(currPageBean.getEndCount()-1);
				}
				
				nList = newsDAO.queryNewsByDeptId(currPageBean,user.getDeptid(),Context.机构新闻,search);
				
				
			}else{
				currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
				currPageBean.setEndCount(10);
				currPageBean.setPageSize(10);
				int totalPage = count%currPageBean.getPageSize()==0?count/currPageBean.getPageSize():count/currPageBean.getPageSize()+1;
				currPageBean.setTotalPage(totalPage);
				
				nList = newsDAO.queryNewsByDeptId(currPageBean,user.getDeptid(),Context.机构新闻,search);
				
			}
			
			currPageBean.setTotal(count);
		
		}
		
		pageBean=currPageBean;
		return "news_dept_list";
	}
	
	/**
	 * 新闻列表
	 * @return
	 */
	
	public String newsDeptListAdmin() {
		PageBean currPageBean = new PageBean();
		HttpServletRequest request = ServletActionContext.getRequest();
		String search = request.getParameter("search");
		int count = newsDAO.queryCount(Context.机构新闻, search);
		if (pageBean != null) {
			pageBean.setPageSize(10);
			currPageBean
					.init(pageBean.getCurrentPage(), pageBean.getPageSize());
			if (currPageBean.getCurrentPage() != 1) {
				currPageBean.setEndCount(currPageBean.getEndCount() - 1);
			}

			nList = newsDAO.queryNewsByAdmin(currPageBean, Context.机构新闻, search);

		} else {
			currPageBean.init(currPageBean.getCurrentPage(), currPageBean
					.getPageSize());
			currPageBean.setEndCount(10);
			currPageBean.setPageSize(10);
			int totalPage = count % currPageBean.getPageSize() == 0 ? count
					/ currPageBean.getPageSize() : count
					/ currPageBean.getPageSize() + 1;
			currPageBean.setTotalPage(totalPage);
			nList = newsDAO.queryNewsByAdmin(currPageBean, Context.机构新闻, search);
		}
		currPageBean.setTotal(count);
		pageBean = currPageBean;
		return "news_dept_list_admin";
	}

	
	public String addDeptNews(){
		return "add_dept_news";
	}
	
	public String updateDeptNewsById(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		try {
			
			String id = request.getParameter("id");
			
			if(StringUtils.hasText(id)){
				
				News cnews = newsDAO.queryNewsById(id);
				if (cnews != null) { 
					request.setAttribute("news", cnews);
				}  
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "update_dept_news";
	}
	
	public String goUpdateDeptNewsByAdmin(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		try {
			
			String id = request.getParameter("id");
			
			if(StringUtils.hasText(id)){
				
				News cnews = newsDAO.queryNewsById(id);
				if (cnews != null) { 
					request.setAttribute("news", cnews);
				}  
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "update_dept_news_admin";
	}
	
	
	public String insertDeptNews(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		User user = (User)request.getSession().getAttribute("userBean");
		
		if(user!=null){
			news.setId(UUID.randomUUID().toString());
			news.setDeptid(user.getDeptid());
			news.setUserid(user.getUser_id());
			news.setFtime(new Date());
			news.setNum(0);
			news.setState(0);
			news.setTop(0);
			
			if(myFile!=null){
				String imagepath = UploadImage.uploadImage(myFile, myFileFileName, null);
				news.setUrl(imagepath);
			}
			
		}
		
		
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
		int iflag = newsDAO.insertNews(news);
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
		return newsDeptList();
	}

	
	
	public String updateDeptNews(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
		int iflag = newsDAO.updateNews(news);
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

		return newsDeptList();
	}
	
	public String updateDeptNewsByAdmin(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
		int iflag = newsDAO.updateNews(news);
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

		return newsDeptListAdmin();
	}
	
	
	public String deleteDeptNews(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter out = null;
		try {
			
			String id = request.getParameter("id");
			out = response.getWriter();
			if(StringUtils.hasText(id)){
				
				
				int iflag = newsDAO.deleteNews(id);
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
		return newsDeptList();
	}
	
	
}
