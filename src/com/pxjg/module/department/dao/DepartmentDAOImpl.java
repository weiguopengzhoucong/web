package com.pxjg.module.department.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.pxjg.base.dao.BaseDAO;
import com.pxjg.module.course.entity.Course;
import com.pxjg.module.department.entity.Department;
import com.pxjg.module.user.entity.User;
import com.pxjg.module.userCollection.dao.CollectionDAOImpl;
import com.pxjg.module.userCollection.dao.ICollectionDAO;
import com.pxjg.module.userCollection.entity.UserCollection;
import com.pxjg.module.yh.entity.Yh;
import com.pxjg.util.PageBean;
import com.pxjg.util.UtilTool;

public class DepartmentDAOImpl extends BaseDAO implements IDepartmentDAO {

	@Override
	public int addDept(Department dept) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("my_dept.addDept",dept);
	}

	@Override
	public List<Department> queryDeptBySQ(String shangquanid) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("my_dept.queryDeptBySQ",shangquanid);
	}

	@Override
	public int updateDeptState(String id,int state) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("state", state);
		return getSqlSession().update("my_dept.updateDeptState",map);
	}

	@Override
	public int updateDept(Department dept) {
		// TODO Auto-generated method stub
		return getSqlSession().update("my_dept.updateDept",dept);
	}

	@Override
	public Department queryDeptById(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("my_dept.queryDeptById",id);
	}

	@Override
	public int queryDeptBySearchCount( String re_id,
			String select_type, String select_value) {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer();
		
		if(select_type.equals("课程")){
			sql.append("select count(*) from tbl_department d,tbl_department_course c "); 
			sql.append("where d.cityid =  '"+re_id+"' and d.id = c.deptid and  c.cname like '%"+select_value+"%' "); 
			sql.append("  order by d.top asc  "); 
		}
		
		if(select_type.equals("机构")){
			sql.append(" select count(*) from tbl_department d,tbl_department_type t where ");
			sql.append(" d.cityid = '"+re_id+"' and d.depttype=t.id and (t.typename like '%"+select_value+"%' or  d.deptname like '%"+select_value+"%' )");
			sql.append("  order by d.top asc  "); 
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", sql.toString());
		
		return getSqlSession().selectOne("my_dept.queryDeptBySearchCount",map);
	}

	@Override
	public List<Department> queryDeptBySearch(PageBean pageBean,
			String re_id, String select_type, String select_value,String order) {
		// TODO Auto-generated method stub
	
		if(!StringUtils.hasText(order)){
			order = " order by top asc";
		}
		
		if("1".equals(order)){
			order = " order by top asc";
		}
		if("2".equals(order)){
			order = " order by num desc";
		}
		
		if("3".equals(order)){
			order = " order by top asc";
		}
		
		StringBuffer sql = new StringBuffer();
		
		if(select_type.equals("课程")){
			sql.append(" select s.* from tbl_department s where rowid in(select rid from (select rownum rn,rid from(select d.rowid rid from "); 
			sql.append(" tbl_department d,tbl_department_course c "); 
			sql.append("  where d.cityid = '"+re_id+"' and d.id = c.deptid ");
			
			if(StringUtils.hasText(select_value)){
				sql.append(" and c.cname like '%"+select_value+"%'  "); 
			}
			sql.append(" "+order+" )  "); 
			
			sql.append("   where rownum<="+pageBean.getEndCount()+") where rn>="+pageBean.getStartCount()+")  "+order+" "); 
		}
		
		if(select_type.equals("机构")){
			sql.append(" select s.* from tbl_department s where rowid in(select rid from (select rownum rn,rid from(select d.rowid rid from "); 
			sql.append(" tbl_department d,tbl_department_type t "); 
			sql.append("  where d.cityid = '"+re_id+"' and d.depttype=t.id "); 
			
			if(StringUtils.hasText(select_value)){
				sql.append(" and ( t.typename like  '%"+select_value+"%' or d.deptname like '%"+select_value+"%') "); 
			}
			sql.append(" "+order+" )  "); 
			
			sql.append("   where rownum<="+pageBean.getEndCount()+") where rn>="+pageBean.getStartCount()+") "+order+" "); 
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", sql.toString());
		
		List<Department> list = getSqlSession().selectList("my_dept.queryDeptBySearch",map);
		
		List<Department> lsDept = new ArrayList<Department>();
		
			
			for(Department d : list){
				
					Course course = d.getCourse();
					if(course!=null){
						
						if(select_type.equals("课程")){
						Yh lsYh = getSqlSession().selectOne("my_dept.queryYhBySearchType",course.getId());
						
							d.setYh(lsYh);
						
						}
						
						String content=course.getContent();
						if(StringUtils.hasText(content)){
							content=content.replaceAll("\r\n", "");
							content=content.replaceAll(UtilTool.ReservationsHtmlTags(), "");
							if(content.length()>15){
								content = content.substring(0, 12)+"....";
							}
						}
						
						course.setContent(content);
						
					}
					
				lsDept.add(d);
				
			}
		
		return lsDept;
	}
	
	
	
	@Override
	public List<Department> queryDeptByShangquanId(PageBean pageBean,
			String type, String re_id, String areaid, String shangquanid,String order) {
		// TODO Auto-generated method stub
		
		if(!StringUtils.hasText(order)||"null".equals(order)){
			order = " order by top asc";
		}
		
		if("1".equals(order)){
			order = " order by top asc";
		}
		if("2".equals(order)){
			order = " order by num desc";
		}
		
		if("3".equals(order)){
			order = " order by top asc";
		}
		
		
		StringBuffer sql = new StringBuffer();
		
			sql.append(" select s.* from tbl_department s where rowid in(select rid from (select rownum rn,rid from(select d.rowid rid from "); 
			sql.append(" tbl_department d "); 
			sql.append("  where  1=1 "); 
			
			if(StringUtils.hasText(type)&&!"null".equals(type)){
				type = type.replace("type", "");
				sql.append(" and d.depttype = '"+type+"'");
			}
			
			if(StringUtils.hasText(re_id)){
				sql.append(" and d.cityid = '"+re_id+"'");
			}
			
			if(StringUtils.hasText(areaid)&&!"null".equals(areaid)){
				areaid = areaid.replace("area", "");
				sql.append(" and d.quxianid = '"+areaid+"'");
			}
			
			if(StringUtils.hasText(shangquanid)&&!"null".equals(shangquanid)){
				shangquanid = shangquanid.replace("shangquan", "");
				sql.append(" and d.shangquanid = '"+shangquanid+"'");
			}
			
			
			sql.append(order); 
			
			sql.append(" )  where rownum<="+pageBean.getEndCount()+") where rn>="+pageBean.getStartCount()+")  "+order+" "); 
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", sql.toString());
		
		List<Department> list = getSqlSession().selectList("my_dept.queryDeptBySearch",map);
		
		List<Department> lsDept = new ArrayList<Department>();
		
			
			for(Department d : list){
				
					Course course = d.getCourse();
					if(course!=null){
						
						String content=course.getContent();
						if(StringUtils.hasText(content)){
							content=content.replaceAll("\r\n", "");
							content=content.replaceAll(UtilTool.ReservationsHtmlTags(), "");
							if(content.length()>15){
								content = content.substring(0, 12)+"....";
							}
						}
						
						//检索此课程是否已经被收藏
						
						HttpServletRequest request = ServletActionContext.getRequest();
						User user =(User) request.getSession().getAttribute("userBean");
						if(user!=null){
							Map<String, Object> cmap = new HashMap<String, Object>();
							cmap.put("userid", user.getUser_id());
							cmap.put("cid", course.getId());
							UserCollection userCollection = getSqlSession().selectOne("my_collection.queryCourseCollection",cmap);
							
							
							if(userCollection==null){
								course.setZhouqi("收藏");
								course.setSeq(course.getId());
							
							}else{
								course.setZhouqi("取消收藏");
								course.setSeq(userCollection.getId());
							}
							
						}else{
							course.setZhouqi("");
						}
						course.setContent(content);
						
					}
					
				lsDept.add(d);
				
			}
		
		return lsDept;
	}
	
	
	
	@Override
	public int queryDeptCountByShangquanId(String type, String re_id, String areaid, String shangquanid) {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer();
		
		
			sql.append(" select count(*) from tbl_department d where 1=1 ");

			if(StringUtils.hasText(type)){
				type = type.replace("type", "");
				sql.append(" and d.depttype = '"+type+"'");
			}
			
			if(StringUtils.hasText(re_id)){
				sql.append(" and d.cityid = '"+re_id+"'");
			}
			
			if(StringUtils.hasText(areaid)){
				areaid = areaid.replace("area", "");
				sql.append(" and d.quxianid = '"+areaid+"'");
			}
			
			if(StringUtils.hasText(shangquanid)){
				shangquanid = shangquanid.replace("shangquan", "");
				sql.append(" and d.shangquanid = '"+shangquanid+"'");
			}
			
			 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", sql.toString());
		
		return getSqlSession().selectOne("my_dept.queryDeptBySearchCount",map);
	}

	@Override
	public List<Department> queryDeptByCity(String cityid) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("my_dept.queryDeptByCity",cityid);
	}

	@Override
	public List<Department> queryRank(PageBean pageBean) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		return getSqlSession().selectList("my_dept.queryRank",map);
	}

	@Override
	public int queryRankDeptCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("my_dept.queryRankDeptCount");
	}

	@Override
	public int toTop(String id, String top) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("top", top);
		return getSqlSession().update("my_dept.toTop",map);
	}
	
	
	
}


