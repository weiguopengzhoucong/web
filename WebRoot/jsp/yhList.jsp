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
<title>优惠</title>
<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/layout.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/main.css" type="text/css" />
	<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
</head>

<body>
<div class="layout">
    <div class="main H800" id="main">
    	<div class="user_list">
        	<form action="userAction!userList.do" method="post">
        	<input type="hidden" id="deptorcourse" name="deptorcourse" value="${deptorcourse}"/>
        	<input type="hidden" id="deptorcourseid" name="deptorcourseid" value="${deptorcourseid}"/>
        	<div class="table_list W961">
              <div class="tt">查询结果如下</div>
              <a href="#" onclick="goAdd()">添加</a>
              <table cellpadding="0" cellspacing="0" width="100%" class="th-white">
                <tr>
                  <th scope="col" width="116">名称</th>
                  <th scope="col" width="200">起始日期</th>
                  <th scope="col" width="200">截至日期</th>
                  <th scope="col" width="172">操作</th>
                </tr>
                <s:iterator value="yhList">
	                <tr>
	                  <td><s:property  value="name" /></td>
	                  <td><s:property value="startdate" /></td>
	                  <td><s:property value="enddate" /></td>
	                  <td>
	                 <s:if test="state==0">
						<a href="#" onclick="openOrClose('<s:property value="id"/>',1)">激活</a>
						</s:if>
						<s:else><a href="#" onclick="openOrClose('<s:property value="id"/>',0)">取消</a></s:else>
	                  	&nbsp;<a href="#" onclick="goUpdate('<s:property value="id"/>')">修改</a>
	                  	&nbsp;<a href="#" onclick="goDelete('<s:property value="id" />')">删除</a>
	                  </td>
	                </tr>
                </s:iterator>
              </table>
          </div>
          </form>
          <div >
                    	<jsp:include page="page.jsp">
							<jsp:param name="url" value="<%=path+"/yhAction!yhList.do"%>"/>
						</jsp:include>
                    </div>
        </div>
    </div>

</div>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

	function openOrClose(id,state){
		if(confirm("确定操作吗？")){
			var deptorcourse = ${deptorcourse};
			var deptorcourseid = '${deptorcourseid}';
			$.ajax({
	      	type: "POST",
	     	url: "yhAction!openOrClose.do?id="+id+"&state="+state+"&deptorcourseid="+deptorcourseid,
	      	success: function(msg){
	      		 if(msg==1){
	      		  	alert("操作成功");
	        		 var url = "<%=path%>/yhAction!yhList.do?deptorcourse="+deptorcourse+"&deptorcourseid="+deptorcourseid;
	        		 window.location.href = url;
	      		 }
	      		 if(msg==0){
	      		 	alert("操作失败");
	      		 }
	      		if(msg==2){
	      		 	alert("已有激活优惠，无法重复激活");
	      		 }
	     		}
	  		});
			}
	}
	
	function goUpdate(id){
		var deptorcourse = ${deptorcourse};
		var deptorcourseid = '${deptorcourseid}';
		var url = "<%=path %>/yhAction!goUpdateYh.do?id="+id+"&deptorcourse="+deptorcourse+"&deptorcourseid="+deptorcourseid;
		window.location.href = url;
	}
	
	function goAdd(){
		var deptorcourse = ${deptorcourse};
		var deptorcourseid = '${deptorcourseid}';
		var url = "<%=path %>/yhAction!goAddYh.do?&deptorcourse="+deptorcourse+"&deptorcourseid="+deptorcourseid;
		window.location.href = url;
	}
	
	
	function goDelete(id){
		if(confirm("确定删除吗？")){
		var deptorcourse = ${deptorcourse};
		var deptorcourseid = '${deptorcourseid}';
		$.ajax({
	      	type: "POST",
	     	url: "yhAction!deleteYh.do?id="+id,
	      	success: function(msg){
	      		 if(msg==1){
	      		  	alert("操作成功");
	        		 var url = "<%=path%>/yhAction!yhList.do?deptorcourse="+deptorcourse+"&deptorcourseid="+deptorcourseid;
	        		 window.location.href = url;
	      		 }
	      		 if(msg==0){
	      		 	alert("操作失败");
	      		 }
	     		}
	  		});
			}
	}
</script>
</body>
</html>
