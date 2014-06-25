<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.pxjg.module.user.entity.User"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("userBean");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    

    
	<title></title>
	<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/layout.css" type="text/css" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<body>
  		<div class="header">
		<div class="header_con W960">
    	<div class="operate">
        	<span>操作员：<%=user.getReal_name() %></span> | <a href="<%=path%>/userAction!updatePassword.do" target="main"><span>密码修改</span></a> | <a href="#" onclick="logout()"><span>退出</span></a>
        </div>
    </div>
</div>
  	</body>
</html>
<script type="text/javascript">
	function logout() {
      if (!confirm('您确实想注销掉自己吗？')) {
         return;
      }
      window.location='<%=path%>/userAction!logout.do';
   }
   

</script>
