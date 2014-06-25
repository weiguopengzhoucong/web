<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<link type="text/css" href="<%=basePath %>frontJsp/css/common.css" rel="stylesheet" /> 
<link type="text/css" href="<%=basePath %>frontJsp/css/index.css" rel="stylesheet" /> 


<!-- <link type="text/css" href="<%=basePath %>frontJsp/css/detail.css" rel="stylesheet" /> -->
<!-- <link type="text/css" href="<%=basePath %>frontJsp/css/MySpace.css" rel="stylesheet" /> -->



<script type="text/javascript" src="<%=basePath %>frontJsp/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=basePath %>frontJsp/js/jquery-ui-1.10.0.custom.js"></script>
<script type="text/javascript" src="<%=basePath %>frontJsp/js/jquery.cycle.all.js"></script>
<script type="text/javascript" src="<%=basePath %>frontJsp/js/index.js"></script>
<script type="text/javascript" src="<%=basePath %>frontJsp/js/node.js"></script>
<script type="text/javascript" src="<%=basePath %>frontJsp/js/max.js"></script>