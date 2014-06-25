package com.pxjg.module.region.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pxjg.module.region.dao.IRegionDAO;
import com.pxjg.module.region.entity.Region;

public class RegionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Region region;
	private Region newregion;
	private Region oldregion;
	private Region pageBean;
	private List<Region> regiontList;
	private List<Region> regionListTree;
	private IRegionDAO regionDAO;
	private int flag=0;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Region getNewregion() {
		return newregion;
	}
	public void setNewregion(Region newregion) {
		this.newregion = newregion;
	}
	public Region getOldregion() {
		return oldregion;
	}
	public void setOldregion(Region oldregion) {
		this.oldregion = oldregion;
	}
	public Region getPageBean() {
		return pageBean;
	}
	public void setPageBean(Region pageBean) {
		this.pageBean = pageBean;
	}
	public List<Region> getRegiontList() {
		return regiontList;
	}
	public void setRegiontList(List<Region> regiontList) {
		this.regiontList = regiontList;
	}
	public List<Region> getRegionListTree() {
		return regionListTree;
	}
	public void setRegionListTree(List<Region> regionListTree) {
		this.regionListTree = regionListTree;
	}
	
	public IRegionDAO getRegionDAO() {
		return regionDAO;
	}
	public void setRegionDAO(IRegionDAO regionDAO) {
		this.regionDAO = regionDAO;
	}
	public String queryRegionById(){
		regionListTree=regionDAO.queryRegionListTree();
		newregion=regionDAO.queryRegionById(this.region.getRe_id());
		if(!this.region.getRe_id().equals("0")&&this.region.getRe_id()!=null){

			oldregion=regionDAO.queryRegionById(this.newregion.getParent_id());
		}
		return "regionList";
		
	}
	
	public String goUpdateUserDept(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String userid = request.getParameter("userid");
		regionListTree=regionDAO.queryRegionListTree();
		newregion=regionDAO.queryRegionById(this.region.getRe_id());
		if(!this.region.getRe_id().equals("0")&&this.region.getRe_id()!=null){

			oldregion=regionDAO.queryRegionById(this.newregion.getParent_id());
		}
		request.setAttribute("userid", userid);
		return "departmentList2";
		
	}
	
	public String addRegionById(){
		oldregion=regionDAO.queryRegionById(this.region.getRe_id());
		regionListTree=regionDAO.queryRegionListTree();
		return "regionList";
		
	}
	public String saveRegion() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		
		//上级区域id是0，说明是城市，区域类型设置为1
		if(newregion.getParent_id().equals("0")){
			newregion.setRegion_type(1);
		}else{
			Region parentRegion = regionDAO.queryRegionById(newregion.getParent_id());
			//上级区域类型是1，说明是在城市下建立区县，区域类型设置为2
			if(parentRegion.getRegion_type()==1){
				newregion.setRegion_type(2);
			}
			//上级区域类型是2，说明是在区县下建立商圈，区域类型设置为3
			if(parentRegion.getRegion_type()==2){
				newregion.setRegion_type(3);
			}
			//上级区域类型是3，无法在商圈下再次加区县
			if(parentRegion.getRegion_type()==3){
				out = response.getWriter();
				out.println("<html>");   
				out.println("<script>");   
				out.println("alert('商圈下无法添加区域')");
				out.println("</script>");   
				out.println("</html>"); 
				out.flush();
				newregion=regionDAO.queryRegionById(this.region.getRe_id());
				regionListTree=regionDAO.queryRegionListTree();
				return "regionList";
			}
		}
		if(regionDAO.addRegion(newregion)){
			out = response.getWriter();
			out.println("<html>");   
			out.println("<script>");   
			out.println("alert('添加成功')");
			out.println("</script>");   
			out.println("</html>"); 
			out.flush();
			region.setRe_id(newregion.getRe_id());
			newregion=regionDAO.queryRegionById(newregion.getRe_id());
			regionListTree=regionDAO.queryRegionListTree();
			return "regionList";
		
		}else{
			out = response.getWriter();
			out.println("<html>");   
			out.println("<script>");   
			out.println("alert('添加失败')");
			out.println("history.go(-1)");
			out.println("</script>"); 
			out.println("</html>");
			out.flush();
			newregion=regionDAO.queryRegionById(this.region.getRe_id());
			return "regionList";
		}
		
	}
	public String updateRegion()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		if(regionDAO.updateRegion(newregion)){
			out = response.getWriter();
			out.println("<html>");   
			out.println("<script>");   
			out.println("alert('修改成功')");
			out.println("</script>");   
			out.println("</html>"); 
			out.flush();
			flag = 0;
			regionListTree=regionDAO.queryRegionListTree();
			return "regionList";
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
	public String deleteRegionById()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = null;
		if(regionDAO.getParentCount(this.region.getRe_id())>0){
			out = response.getWriter();
			out.println("<html>");   
			out.println("<script>");   
			out.println("alert('无法删除：请确定该组织下无子组织')");
			out.println("history.go(-1)");
			out.println("</script>");   
			out.println("</html>"); 
			return null;
			
		}else{
			
				newregion=new Region();
				newregion.setRe_id(this.region.getRe_id());
				if(regionDAO.deleteRegion(newregion)){
					out = response.getWriter();
					out.println("<html>");   
					out.println("<script>");   
					out.println("alert('删除成功')");
					out.println("</script>");   
					out.println("</html>"); 
					region.setRe_id("0");
					newregion=regionDAO.queryRegionById("0");
					regionListTree=regionDAO.queryRegionListTree();
					return "regionList";
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
