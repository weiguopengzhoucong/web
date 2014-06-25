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
<title>动态管理</title>
<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/layout.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/main.css" type="text/css" />
	<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
</head>

<body>
    
    <div class="layout">
    <div class="main" id="main">
    	<div class="manage">
    		<a href="deptAction!goAddDept.do?shangquanid=${shangquanid}">添加</a>
        	<div class="table_list">
        			<input type="hidden" id="shangquanid" value="${shangquanid}" />
                	<table cellpadding="0" cellspacing="0" width="100%">
                      <tr>
                  	    <th scope="col" width="296">名称</th>
                        <th scope="col" width="296">电话</th>
                        <th scope="col" width="296">地址</th>
                         <th scope="col" width="296">操作</th>
                      </tr>
                      <s:iterator value="deptList">
						<tr >
						<td><s:property value="deptname"/></td>
						<td><s:property value="tel"/></td>
						<td><s:property value="address"/></td>
						<td><a href="#" onclick="goUpdate('<s:property value="id" />')">编辑</a>&nbsp;
						<s:if test="state==0">
						<a href="#" onclick="goDelete('<s:property value="id"/>',1)">冻结</a>
						</s:if>
						<s:else><a href="#" onclick="goDelete('<s:property value="id"/>',0)">激活</a></s:else>
						&nbsp;
						<a href="#" onclick="deptUserList('<s:property value="id" />')">管理用户</a>
						</td>
					</tr>
					</s:iterator>
                    </table>
                </div>
            
        </div>
    </div>
    
</div>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	
	$(function(){
		$(".table_list tr:even").addClass("bg");
	});
	
	function reset(){
		var title = document.getElementById("title");
		title.value="";
	}
	function goUpdate(id){
		var url = "<%=path %>/deptAction!goUpdateDept.do?id="+id;
		window.location.href = url;
	}
	
	function deptUserList(id){
		var url = "<%=path %>/userAction!queryDeptUserList.do?deptid="+id;
		window.location.href = url;
	}
	
	function goDelete(id,state){
		var shangquanid = document.getElementById('shangquanid').value;
		if(confirm("确定操作吗？")){
			$.ajax({
	      	type: "POST",
	     	url: "deptAction!updateDeptState.do?id="+id+"&state="+state,
	      	success: function(msg){
	      		 if(msg==0){
	      		 	alert("操作失败");
	      		 }else if(msg==1){
	      		 	alert("操作成功");
	      		 	var url = "<%=path%>/deptAction!queryDeptByShangQuan.do?re_id="+shangquanid;
	        	 	window.location.href = url;
	      		 }
	     		}
	  		});
	}
	}
	
</script>
</body>
</html>
