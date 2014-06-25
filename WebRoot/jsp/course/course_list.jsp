<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机构新闻管理</title>
<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/layout.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/main.css" type="text/css" />
	<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
</head>

<body>
    
    <div class="layout">
    <div class="main" id="main">
    	<div class="manage">
    		
        	<div class="table_list">
        		
        		<table cellpadding="0" cellspacing="0" width="100%">
                	<tr>
	             		<td width="30%" align="left" >
	             			 <a href="courseAction!addCourse.do">添加</a>
	             		</td>
	             		<td align="right" colspan="5">
	             			培训内容查询<input type="text" name="search" id="search" /><input type="button" onclick="onsearch();" value="查询" />
	             		</td>
	             	</tr>
	             </table>
        	
                	<table cellpadding="0" cellspacing="0" width="100%">
                      <tr>
                        <th scope="col" width="296">分类</th>
                        <th scope="col" width="296">名称</th>
                        <th scope="col" width="296">发布日期</th>
                        <th scope="col" width="296">费用</th>
                         <th scope="col" width="296">适用年龄段</th>
                         <th scope="col" width="296">培训周期</th>
                        <th scope="col" width="296">审核</th>
                        <th scope="col" width="296">浏览数量</th>
                         <th scope="col" width="296">状态</th>
                        <th scope="col" width="296">排序</th>
                         <th scope="col" width="296">操作</th>
                      </tr>
                      <s:iterator value="nList">
							<tr >
							<td><s:property value="type"/></td>
							<td><s:property value="cname"/></td>
							<td><s:property value="happentime"/></td>
							<td><s:property value="money"/></td>
							<td><s:property value="sage"/></td>
							<td><s:property value="zhouqi"/></td>
							<td><s:property value="state"/></td>
							<td><s:property value="num"/></td>
							<td><s:property value="state"/></td>
							<td><s:property value="Seq"/></td>
							<td>
							<a href="#" onclick="goUpdate('<s:property value="id" />')">编辑</a>&nbsp;
							<a href="#" onclick="goDelete('<s:property value="id" />')">删除</a>&nbsp;
							<a href="#" onclick="goYh('<s:property value="id"/>')">优惠信息</a>
							</td>
						</tr>
					</s:iterator>
                    </table>
                </div>
                 <div >
                    	<jsp:include page="../page.jsp">
							<jsp:param name="url" value="<%=path+"/courseAction!courseList.do"%>"/>
						</jsp:include>
                    </div>

            
        </div>
    </div>
    <div class="footer">
    	欢迎登录后台管理系统
     </div>
</div>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	
	$(function(){
		$(".table_list tr:even").addClass("bg");
	});
	
	function goUpdate(id){
		var url = "<%=path %>/courseAction!updateCourseById.do?id="+id;
		window.location.href = url;
	}
	
	function goYh(id){
		var url = "<%=path %>/yhAction!yhList.do?deptorcourse=1&deptorcourseid="+id;
		window.location.href = url;
	}
	
	function onsearch(){
		var search = $("#search").val();
		var url = "<%=path %>/courseAction!courseList.do?search="+search;
		window.location.href = url;
	}
	
	
	function goDelete(id){
		if(confirm("确定删除吗？")){
			$.ajax({
	      	type: "POST",
	     	url: "courseAction!delete.do?id="+id,
	      	success: function(msg){
	      		 if(msg==0){
	      		 	alert("删除失败");
	      		 }else if(msg==1){
	      		 	alert("删除成功");
	      		 	var url = "<%=path%>/courseAction!courseList.do";
	        	 	window.location.href = url;
	      		 }
	     		}
	  		});
	}
	}
	
</script>
</body>
</html>
