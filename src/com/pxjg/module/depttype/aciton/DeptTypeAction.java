package com.pxjg.module.depttype.aciton;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pxjg.module.depttype.dao.IDeptTypeDAO;
import com.pxjg.module.depttype.entity.DeptType;

public class DeptTypeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DeptType depttype;
	private DeptType newdepttype;
	private DeptType olddepttype;
	private DeptType pageBean;
	private List<DeptType> depttypeList;
	private List<DeptType> depttypeListTree;
	private IDeptTypeDAO deptTypeDAO;
	private int flag=0;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public DeptType getDepttype() {
		return depttype;
	}
	public void setDepttype(DeptType depttype) {
		this.depttype = depttype;
	}
	public DeptType getNewdepttype() {
		return newdepttype;
	}
	public void setNewdepttype(DeptType newdepttype) {
		this.newdepttype = newdepttype;
	}
	public DeptType getOlddepttype() {
		return olddepttype;
	}
	public void setOlddepttype(DeptType olddepttype) {
		this.olddepttype = olddepttype;
	}
	public DeptType getPageBean() {
		return pageBean;
	}
	public void setPageBean(DeptType pageBean) {
		this.pageBean = pageBean;
	}
	public List<DeptType> getDepttypeList() {
		return depttypeList;
	}
	public void setDepttypeList(List<DeptType> depttypeList) {
		this.depttypeList = depttypeList;
	}
	public List<DeptType> getDepttypeListTree() {
		return depttypeListTree;
	}
	public void setDepttypeListTree(List<DeptType> depttypeListTree) {
		this.depttypeListTree = depttypeListTree;
	}
	public IDeptTypeDAO getDeptTypeDAO() {
		return deptTypeDAO;
	}
	public void setDeptTypeDAO(IDeptTypeDAO deptTypeDAO) {
		this.deptTypeDAO = deptTypeDAO;
	}
	
	public String queryDeptTypeById(){
		depttypeListTree=deptTypeDAO.queryDeptTypeListTree();
		newdepttype=deptTypeDAO.queryDeptTypeById(this.depttype.getId());
		if(!this.depttype.getId().equals("0")&&this.depttype.getId()!=null){

			olddepttype=deptTypeDAO.queryDeptTypeById(this.newdepttype.getParentid());
		}
		return "depttypeList";
		
	}
	
	
	public String addDeptTypeById(){
		olddepttype=deptTypeDAO.queryDeptTypeById(this.depttype.getId());
		depttypeListTree=deptTypeDAO.queryDeptTypeListTree();
		return "depttypeList";
		
	}
	public String saveDeptType() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		if(deptTypeDAO.addDeptType(newdepttype)){
			out = response.getWriter();
			out.println("<html>");   
			out.println("<script>");   
			out.println("alert('添加成功')");
			out.println("</script>");   
			out.println("</html>"); 
			out.flush();
			depttype.setId(newdepttype.getId());
			newdepttype=deptTypeDAO.queryDeptTypeById(newdepttype.getId());
			depttypeListTree=deptTypeDAO.queryDeptTypeListTree();
			return "depttypeList";
		
		}else{
			out = response.getWriter();
			out.println("<html>");   
			out.println("<script>");   
			out.println("alert('添加失败')");
			out.println("history.go(-1)");
			out.println("</script>"); 
			out.println("</html>");
			out.flush();
			newdepttype=deptTypeDAO.queryDeptTypeById(this.depttype.getId());
			return "depttypeList";
		}
		
	}
	public String updateDeptType()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		if(deptTypeDAO.updateDeptType(newdepttype)){
			out = response.getWriter();
			out.println("<html>");   
			out.println("<script>");   
			out.println("alert('修改成功')");
			out.println("</script>");   
			out.println("</html>"); 
			out.flush();
			flag = 0;
			depttypeListTree=deptTypeDAO.queryDeptTypeListTree();
			return "depttypeList";
		}else{
			out = response.getWriter();
			out.println("<html>");   
			out.println("<script>");   
			out.println("alert('修改失败')");
			out.println("history.go(-1)");
			out.println("</script>");   
			out.println("</html>"); 
			out.flush();
			return null;
		}
		
	}
	public String deleteDeptTypeById()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		if(deptTypeDAO.getParentCount(this.depttype.getId())>0){
			out = response.getWriter();
			out.println("<html>");   
			out.println("<script>");   
			out.println("alert('无法删除：请确定该组织下无子组织')");
			out.println("history.go(-1)");
			out.println("</script>");   
			out.println("</html>"); 
			return null;
			
		}else{
			
			newdepttype=new DeptType();
			newdepttype.setId(this.depttype.getId());
				if(deptTypeDAO.deleteDeptType(newdepttype)){
					out = response.getWriter();
					out.println("<html>");   
					out.println("<script>");   
					out.println("alert('删除成功')");
					out.println("</script>");   
					out.println("</html>"); 
					depttype.setId("0");
					newdepttype=deptTypeDAO.queryDeptTypeById("0");
					depttypeListTree=deptTypeDAO.queryDeptTypeListTree();
					return "depttypeList";
				}else{
					out = response.getWriter();
					out.println("<html>");   
					out.println("<script>");   
					out.println("alert('删除失败,请确认部门下无用户')");
					out.println("history.go(-1)");
					out.println("</script>");   
					out.println("</html>"); 
					return null;
				}
			}
		}
		
		
		
	}
