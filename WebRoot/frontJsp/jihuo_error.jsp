<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>首页</title>
<jsp:include page="/frontJsp/js_inc.jsp"></jsp:include>
</head>
<body>
<div class="container">
  <!-- header start-->
  <dir class="header">
  		<jsp:include page="/frontJsp/header.jsp"/>
  </dir>
	
  <!-- main start -->
  <div class="mainWrap clearfix">
    <div class="mainLf">
    	对不起激活失败
  </div>
  <!-- main end -->
  <!-- footer start -->
  <div class="footer">
   	<jsp:include page="/frontJsp/footer.jsp"/>
  </div>
  <!-- footer end -->
</div>

</body>
</html>
