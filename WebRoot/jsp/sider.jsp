<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.pxjg.module.user.entity.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("userBean");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>左侧导航</title>
	<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/layout.css" type="text/css" />
	<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
	<script type="text/javascript">
	$(function(){
		var hide = 1;
		$(".sider .big_list li").click(
			function (event){
				$(this).find(".small_list").toggle();
				$(".small_list li").click(
				function(event){
				event.stopPropagation();
			}
		);
			}
		);
		
	});
	
   </script>

  </head>
  	<body>
  		<div class="sider">
	<div class="title">
    	管理导航
    </div>
    <div class="list">
   	    <ul class="big_list">
   	    <%
   	    	if(user.getRole()==2){
   	    %>
    	<li>
			<div class="bottom">管理员管理</div>
			<ul class="small_list">
				<li>
					<a href="<%=path%>/deptTypeAction!queryDeptTypeById.do?depttype.id=0&flag=0" target="main"><div class="bottom">机构类型管理</div></a>
				</li>
				<li>					
					<a href="<%=path%>/regionAction!queryRegionById.do?region.re_id=0&flag=0" target="main"><div class="bottom">区域管理</div></a>
				</li>
				<li>					
					<a href="<%=path%>/newsAction!newsList.do" target="main"><div class="bottom">平台新闻</div></a>
				</li>
				<li>					
					<a href="<%=path%>/userAction!userList.do?role=2" target="main"><div class="bottom">会员管理</div></a>
				</li>
				<li>					
					<a href="<%=path%>/userAction!userList.do?role=1" target="main"><div class="bottom">机构用户管理</div></a>
				</li>
				<li>					
					<a href="<%=path%>/deptAction!rank.do" target="main"><div class="bottom">机构排名</div></a>
				</li>
				<li>					
					<a href="<%=path%>/newsAction!newsDeptListAdmin.do" target="main"><div class="bottom">机构新闻</div></a>
				</li>
				<li>
					<a href="<%=path%>/remarkAction!managerRemarkList.do" target="main"><div class="bottom">评论管理</div></a>
				</li>
			</ul>
		</li>
		<%
   	    	}
		%>		
		 <%
   	    	if(user.getRole()==1){
   	    %>
		<li>
			<div class="bottom">机构用户管理</div>
			<ul class="small_list">
				<li>
					<a href="<%=path%>/deptAction!detailDept.do" target="main"><div class="bottom">机构信息</div></a>
				</li>
				<li>
					<a href="<%=path%>/newsAction!newsDeptList.do" target="main"><div class="bottom">机构新闻</div></a>
				</li>
				<li>					
					<a href="<%=path%>/newsAction!schoolNewsList.do" target="main"><div class="bottom">校区环境新闻</div></a>
				</li>
				<li>					
					<a href="<%=path%>/newsAction!teacherNewsList.do" target="main"><div class="bottom">教师简介新闻</div></a>
				</li>
				<li>					
					<a href="<%=path%>/yhAction!yhList.do?deptorcourse=1&deptorcourseid=<%=user.getDeptid() %>" target="main"><div class="bottom">机构优惠信息</div></a>
				</li>
				<li>
					<a href="<%=path%>/courseAction!courseList.do" target="main"><div class="bottom">培训内容管理</div></a>
				</li>
				<li>
					<a href="<%=path%>/remarkAction!remarkList.do?type=0" target="main"><div class="bottom">机构评论管理</div></a>
				</li>
				<li>
					<a href="<%=path%>/remarkAction!remarkList.do?type=1" target="main"><div class="bottom">课程评论管理</div></a>
				</li>
			</ul>
			
		</li>
		<%
   	    	}
		%>	
       
    	 </ul>
    </div>
    <div class="bottom"></div>
    	
	</div>
  	</body>
</html>
