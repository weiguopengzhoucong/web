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
        	<div class="table_list">
                	<table cellpadding="0" cellspacing="0" width="100%">
                      <tr>
                  	    <th scope="col" width="296">名称</th>
                        <th scope="col" width="296">电话</th>
                        <th scope="col" width="296">地址</th>
                         <th scope="col" width="296">访问量</th>
                         <th scope="col" width="296">操作</th>
                      </tr>
                      <s:iterator value="deptList">
						<tr >
						<td><s:property value="deptname"/></td>
						<td><s:property value="tel"/></td>
						<td><s:property value="address"/></td>
						<td><s:property value="num"/></td>
						<td>
						<s:if test="c2==1">
							<a href="#" onclick="toTop('<s:property value="id"/>',0)">取消推荐</a>
						</s:if>
						<s:else>
							<a href="#" onclick="toTop('<s:property value="id"/>',1)">推荐</a>
						</s:else>
						&nbsp;
						
						</td>
					</tr>
					</s:iterator>
                    </table>
                </div>
                <div >
                    	<jsp:include page="rankpage.jsp">
							<jsp:param name="url" value="<%=path+"/deptAction!rank.do"%>"/>
						</jsp:include>
                    </div>
            
        </div>
    </div>
    
</div>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	
	$(function(){
		$(".table_list tr:even").addClass("bg");
	});
	
	function toTop(id,top){
		if(confirm("确定修改吗？")){
		$.ajax({
	      	type: "POST",
	     	url: "deptAction!toTop.do?id="+id+"&top="+top,
	      	success: function(msg){
	      		 if(msg==1){
	      		  	alert("操作成功");
	      		  	location.reload() ;
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
