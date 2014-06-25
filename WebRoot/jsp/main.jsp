<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
System.out.println(path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站后台</title>
<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/layout.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/main.css" type="text/css" />
</head>

<frameset rows="10%,*" border="0" > 
  	<frame src="<%=path%>/jsp/top.jsp" name="top" /> 
  	<frameset cols="16%,*">  
  	<frame src="<%=path%>/jsp/sider.jsp" name="left" /> 
  	 <frame src="<%=path%>/jsp/index.jsp" name="main"/>
  	 </frameset>
  	 </frameset>
</html>
