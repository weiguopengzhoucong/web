<%@page import="com.pxjg.module.news.entity.News"%>
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
<title></title>
<jsp:include page="/frontJsp/js_inc.jsp"></jsp:include>
<link type="text/css" href="<%=basePath %>frontJsp/css/list.css" rel="stylesheet" />
<link type="text/css" href="<%=basePath %>frontJsp/css/detail.css" rel="stylesheet" />  
</head>

<body>
<div class="container">
  <!-- header start-->
  <div class="header">
    	   <jsp:include page="/frontJsp/header.jsp" />
    	   
  </div>
  <!-- header end -->
  <!-- main start -->
  <div class="mainWrap clearfix">
    <div class="mainLf">
     <div class="detail_con">
     	    <s:iterator value="lsNew">
     	    		<h3 class="newsTitle">
			          <s:property value="title"/>
			          <p class="newsInfo">
			          	<s:property value="title2"/>
			            <span class="date"><s:property value="ftime"/></span>
			          </p>
			     </h3>
			        <div class="info_con">
			           <s:property value="content" escape="false" />
			        </div>
     	    </s:iterator>
        
	  </div>
    </div>
    <div class="mainRt">
      <div class="sidebar news">
         <jsp:include page="/frontJsp/top_news.jsp" />
      </div>
    </div>
    <div class="share">
        <div class="share_to">
         
			<jsp:include page="./share.jsp" />
      </div>
      <div class="gotop"><a href="#"></a></div>
    
    </div>
  </div>
  <!-- main end -->
  <!-- footer start -->
  <div class="footer">
   <jsp:include page="/frontJsp/footer.jsp" />
  </div>
  <!-- footer end -->
</div>
</body>
</html>
