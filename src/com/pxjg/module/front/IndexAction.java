package com.pxjg.module.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.pxjg.module.course.dao.ICourseDAO;
import com.pxjg.module.course.entity.Course;
import com.pxjg.module.department.dao.IDepartmentDAO;
import com.pxjg.module.department.entity.Department;
import com.pxjg.module.depttype.dao.IDeptTypeDAO;
import com.pxjg.module.depttype.entity.DeptType;
import com.pxjg.module.news.dao.INewsDAO;
import com.pxjg.module.news.entity.News;
import com.pxjg.module.region.dao.IRegionDAO;
import com.pxjg.module.region.entity.Region;
import com.pxjg.module.user.entity.User;
import com.pxjg.module.userCollection.dao.ICollectionDAO;
import com.pxjg.module.userCollection.entity.UserCollection;
import com.pxjg.module.yh.dao.IYhDAO;
import com.pxjg.module.yh.entity.Yh;
import com.pxjg.util.Json;
import com.pxjg.util.PageBean;

public class IndexAction extends BaseAction{

	
	private IRegionDAO regionDAO;
	private INewsDAO newsDAO;
	private IDepartmentDAO deptDAO;
	private IDeptTypeDAO deptTypeDAO;
	private ICourseDAO courseDAO;
	private PageBean     pageBean;
	private List<Department>   nList;
	private List<News>   lsNew = new ArrayList<News>();
	private Region region;
	private IYhDAO     yhDAO; 
	private ICollectionDAO     collectionDAO; 
	private List<Yh>   yhList;
	private Course oneCourse;
	
	private Department dept ;
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	
	
	
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
			}
			
		}
		
		return "index";
	}
	
	
	public String DeptDetail(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String id = request.getParameter("id");
		
		if(StringUtils.hasText(id)){
			
			dept = deptDAO.queryDeptById(id);
			if(dept!=null){
				//修改机构访问数
				
				deptDAO.updateDept(dept);
				
				User user = (User)request.getSession().getAttribute("userBean");
				
				if(user!=null){
					UserCollection userCollection =	collectionDAO.queryDeptCollection(user.getUser_id(), dept.getId());
					
					if(userCollection==null){
						dept.setQuxianid("收藏");
						dept.setShangquanid(dept.getId());
					}else{
						dept.setQuxianid("取消收藏");
						dept.setShangquanid(userCollection.getId());
					}
					
				}else{
					dept.setQuxianid("");
				}
				
				
				
				PageBean currPageBean = new PageBean();
				currPageBean.init(currPageBean.getCurrentPage(), currPageBean
						.getPageSize());
				currPageBean.setEndCount(10);
				
				List<News> lsNews = newsDAO.queryNewsByDeptId(currPageBean, dept.getId(), Context.校区环境新闻, "");
				
				dept.setLsNews(lsNews);
				
				List<Course> lsCourse = courseDAO.queryCourseByDeptid(currPageBean, dept.getId(), "");
				
				dept.setLsCourse(lsCourse);
				
			}
			
			
		}
		
		return "dept_detail";
		
		
	}
	
	
	public void DeptById(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String id = request.getParameter("id");
		
		if(StringUtils.hasText(id)){
			
			dept = deptDAO.queryDeptById(id);
			
		}
		
		super.writeToJson(dept);
		
		
	}
	
	
	public String CourseDetail(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String id = request.getParameter("id");
		
		if(StringUtils.hasText(id)){
			
			oneCourse = courseDAO.queryCourseByid(id);
			
			oneCourse.setNum(oneCourse.getNum()+1);
			courseDAO.updateCourse(oneCourse);
			
			User user = (User)request.getSession().getAttribute("userBean");
			if(user!=null){
				UserCollection userCollection =collectionDAO.queryCourseCollection(user.getUser_id(), oneCourse.getId());
				
				if(userCollection==null){
					oneCourse.setC3("收藏");
					oneCourse.setC2(oneCourse.getId());
				}else{
					oneCourse.setC3("取消收藏");
					oneCourse.setC2(userCollection.getId());
				}
				
			}else{
				oneCourse.setC2("");
			}
			
			
			
		}
		
		return "course_detail";
		
	}
	
	
	
	
	
	public void replaceCity(){
		
		Json json = new Json();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
		
	
			
			String city = request.getParameter("city");
			
			if(StringUtils.hasText(city)){
				
				region = regionDAO.getRegionByName(city);
			}else{
				region = regionDAO.getRegionByName("北京");
			}
			
			request.getSession().setAttribute(Context.CURRENT_CITY,region);
			json.setMsg("更改成功");
			json.setSuccess(true);
		
		
			super.writeToJson(json);
	}
	
	
	public String getNewsById(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String id = request.getParameter("id");
		
		if(StringUtils.hasText(id)){
			News news = newsDAO.queryNewsById(id);
			request.setAttribute("news",news);
			lsNew.add(news);
		}
		
		return "news_detail";
		
		
	}
	
	/**
	 * 首页查询机构和课程
	 * @return
	 */
	public String onSearch(){
		
		PageBean currPageBean=new PageBean();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String select_type = request.getParameter("select_type");
		String select_value =  request.getParameter("select_value");
		String order = request.getParameter("order");
		
		region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
		
		if(region==null){
			
			region = regionDAO.getRegionByName("北京");
			request.getSession().setAttribute(Context.CURRENT_CITY,region);
			
		}
		
		int count = deptDAO.queryDeptBySearchCount(region.getRe_id(),select_type,select_value);
			if(pageBean!=null){
				pageBean.setPageSize(10);
				currPageBean.init(pageBean.getCurrentPage(), pageBean.getPageSize());
				if(currPageBean.getCurrentPage()!=1){
					currPageBean.setEndCount(currPageBean.getEndCount()-1);
				}
				
				nList = deptDAO.queryDeptBySearch(currPageBean,region.getRe_id(),select_type,select_value,order);
				
			}else{
				currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
				currPageBean.setEndCount(10);
				currPageBean.setPageSize(10);
				int totalPage = count%currPageBean.getPageSize()==0?count/currPageBean.getPageSize():count/currPageBean.getPageSize()+1;
				currPageBean.setTotalPage(totalPage);
				
				nList = deptDAO.queryDeptBySearch(currPageBean,region.getRe_id(),select_type,select_value,order);
				
			}
			
			currPageBean.setTotal(count);
		
		pageBean=currPageBean;
		
		return "dept_list_frame";
		
	}
	
	
	
	/**
	 * 首页查询机构和课程
	 * @return
	 */
	public String clickTopValue(){
		
		PageBean currPageBean=new PageBean();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String type = request.getParameter("type");
		String areaid = request.getParameter("areaid");
		String shangquanid = request.getParameter("shangquanid");
		String order = request.getParameter("order");
		
		region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
		
		if(region==null){
			
			region = regionDAO.getRegionByName("北京");
			request.getSession().setAttribute(Context.CURRENT_CITY,region);
			
		}
		
		int count = deptDAO.queryDeptCountByShangquanId(type,region.getRe_id(),areaid,shangquanid);
			if(pageBean!=null){
				pageBean.setPageSize(10);
				currPageBean.init(pageBean.getCurrentPage(), pageBean.getPageSize());
				if(currPageBean.getCurrentPage()!=1){
					currPageBean.setEndCount(currPageBean.getEndCount()-1);
				}
				
				nList = deptDAO.queryDeptByShangquanId(currPageBean, type,region.getRe_id(), areaid, shangquanid, order);
				
			}else{
				currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
				currPageBean.setEndCount(10);
				currPageBean.setPageSize(10);
				int totalPage = count%currPageBean.getPageSize()==0?count/currPageBean.getPageSize():count/currPageBean.getPageSize()+1;
				currPageBean.setTotalPage(totalPage);
				
				nList = deptDAO.queryDeptByShangquanId(currPageBean, type,region.getRe_id(), areaid, shangquanid, order);
				
				
			}
			
			currPageBean.setTotal(count);
		
		pageBean=currPageBean;
		
		return "dept_list_frame_click_top";
		
	}
	
	//返回父Id是0的机构
	public void getCity(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
		
		if(region==null){
			
			region = regionDAO.getRegionByName("北京");
			
			request.getSession().setAttribute(Context.CURRENT_CITY,region);
			
		}
		
		List<Region> list = regionDAO.getChildRegion("0");
		super.writeToJson(list);
	}
	
	
	
	public void getTopDept(){
		PageBean currPageBean=new PageBean();
		currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
		currPageBean.setEndCount(5);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
		
		if(region==null){
			
			region = regionDAO.getRegionByName("北京");
			request.getSession().setAttribute(Context.CURRENT_CITY,region);
			
		}
		nList = deptDAO.queryDeptBySearch(currPageBean,region.getRe_id(),"机构","","");
		
		super.writeToJson(nList);
	}
	
	
	/**
	 * 返回首页置顶新闻
	 */
	public void getTopNews(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
		
		if(region==null){
			
			region = regionDAO.getRegionByName("北京");
			request.getSession().setAttribute(Context.CURRENT_CITY,region);
			
		}
		
		PageBean currPageBean=new PageBean();
		currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
		currPageBean.setEndCount(5);
		
		List<News> nList = newsDAO.queryNews(currPageBean, "平台新闻", "");
		
		super.writeToJson(nList);
		
	}
	
	/**
	 * 点击更多查询新闻列表页面
	 * 
	 * @return
	 */
	public String getTopNewsMore(){
		
		PageBean currPageBean=new PageBean();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
		
		if(region==null){
			
			region = regionDAO.getRegionByName("北京");
			request.getSession().setAttribute(Context.CURRENT_CITY,region);
			
		}
		
		
		int count = newsDAO.queryNewsCount("平台新闻");
		if(pageBean!=null){  
			pageBean.setPageSize(10);
			currPageBean.init(pageBean.getCurrentPage(), pageBean.getPageSize());
			if(currPageBean.getCurrentPage()!=1){
				currPageBean.setEndCount(currPageBean.getEndCount()-1);
			}
			
			List<News> newsList = newsDAO.queryNews(currPageBean, "平台新闻", "");
			
			for(News news : newsList){
				
				String content=news.getContent();
				if(StringUtils.hasText(content)){
					content = content.substring(0, 12)+"....";
				}
				news.setContent(content);
				lsNew.add(news);
			}
			
			
		}else{
			currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
			currPageBean.setEndCount(10);
			currPageBean.setPageSize(10);
			int totalPage = count%currPageBean.getPageSize()==0?count/currPageBean.getPageSize():count/currPageBean.getPageSize()+1;
			currPageBean.setTotalPage(totalPage);
			
			List<News> newsList = newsDAO.queryNews(currPageBean, "平台新闻", "");
			
			for(News news : newsList){
				
				String content=news.getContent();
				if(StringUtils.hasText(content)){
					content = content.substring(0, 40)+"....";
				}
				news.setContent(content);
				lsNew.add(news);
			}
			
		}
		
		currPageBean.setTotal(count);
		pageBean=currPageBean;
		
		return "news_list";
		
	}
	
	/*
	 * 
	 * 获取首页左侧机构类别项目
	 * 
	 */
	public void getDeptType(){
		
	HttpServletRequest request = ServletActionContext.getRequest();
		
		region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
		
		if(region==null){
			
			region = regionDAO.getRegionByName("北京");
			request.getSession().setAttribute(Context.CURRENT_CITY,region);
			
		}
		
		List<DeptType> list = deptTypeDAO.getChildDeptType("0");
		
		List <DeptType> plist = new ArrayList<DeptType>();
		
		for(DeptType d : list){
			
			List<DeptType> clist = deptTypeDAO.getChildDeptType(d.getId());
			
			for(DeptType type: clist){
				List lsDept = new ArrayList();
				for(Department dept :type.getLsDepartment()){
					if(region.getRe_id().equals(dept.getCityid())){
						lsDept.add(dept);
					}else{
						continue;
					}
				}
				
				type.setLsDepartment(lsDept);
				
			}
			
			d.setLsDeptType(clist);
			plist.add(d);
		}
		
		super.writeToJson(plist);
	}
	
	public void childRegionByType(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
		
		if(region==null){
			
			region = regionDAO.getRegionByName("北京");
			request.getSession().setAttribute(Context.CURRENT_CITY,region);
			
		}
		
		List<Region> lsArea = regionDAO.queryChildRegionByType(region.getRe_id(),"2");
		
		region.setLsArea(lsArea);
		
		super.writeToJson(region);
	}
	
	/**
	 * 查询首页课程
	 */
	public void getCourse(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
		
		if(region==null){
			
			region = regionDAO.getRegionByName("北京");
			request.getSession().setAttribute(Context.CURRENT_CITY,region);
			
		}
		
		List<String> list = courseDAO.queryCourseByGroup(region.getRe_id());
		
		Map<String,List<Course>> mpType = new HashMap<String,List<Course>>();
		
		for(String type : list){
			
			PageBean currPageBean=new PageBean();
			currPageBean.init(currPageBean.getCurrentPage(), currPageBean.getPageSize());
			currPageBean.setEndCount(4);
			List<Course> lsType = courseDAO.queryCourseByType(currPageBean, region.getRe_id(), type);
			
			mpType.put(type, lsType);
		}
		
		super.writeToJson(mpType);
	}
	
	
	
	/**
	 * 返回首页置顶新闻
	 */
	public void getDeptByRegion(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		region = (Region)request.getSession().getAttribute(Context.CURRENT_CITY);
		
		if(region==null){
			
			region = regionDAO.getRegionByName("北京");
			request.getSession().setAttribute(Context.CURRENT_CITY,region);
			
		}
		
		nList = deptDAO.queryDeptByCity(region.getRe_id());
		
		super.writeToJson(nList);
		
	}
	
	
	
	public IRegionDAO getRegionDAO() {
		return regionDAO;
	}
	public void setRegionDAO(IRegionDAO regionDAO) {
		this.regionDAO = regionDAO;
	}


	public INewsDAO getNewsDAO() {
		return newsDAO;
	}


	public void setNewsDAO(INewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}


	public IDeptTypeDAO getDeptTypeDAO() {
		return deptTypeDAO;
	}


	public void setDeptTypeDAO(IDeptTypeDAO deptTypeDAO) {
		this.deptTypeDAO = deptTypeDAO;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}


	public ICourseDAO getCourseDAO() {
		return courseDAO;
	}


	public void setCourseDAO(ICourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<Department> getnList() {
		return nList;
	}

	public void setnList(List<Department> nList) {
		this.nList = nList;
	}

	public IDepartmentDAO getDeptDAO() {
		return deptDAO;
	}

	public void setDeptDAO(IDepartmentDAO deptDAO) {
		this.deptDAO = deptDAO;
	}

	public List<News> getLsNew() {
		return lsNew;
	}

	public void setLsNew(List<News> lsNew) {
		this.lsNew = lsNew;
	}


	public Region getRegion() {
		return region;
	}


	public void setRegion(Region region) {
		this.region = region;
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


	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}


	public ICollectionDAO getCollectionDAO() {
		return collectionDAO;
	}


	public void setCollectionDAO(ICollectionDAO collectionDAO) {
		this.collectionDAO = collectionDAO;
	}


	public Course getOneCourse() {
		return oneCourse;
	}


	public void setOneCourse(Course oneCourse) {
		this.oneCourse = oneCourse;
	}


	public void setCourse(Course course) {
		this.oneCourse = course;
	}



	

}
