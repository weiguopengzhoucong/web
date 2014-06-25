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
      <div class="AllNews">
        <ul class="tabTitle">
          <li class="current"><a href="" >热点资讯</a></li>
          <li><a href="" >报考指南</a></li>
          <li><a href="" >政策视点</a></li>
          <li><a href="" >经验技巧</a></li>
          <li><a class="last" href="" >品牌资讯</a></li>
        </ul>
        <div class="newsWrap">
          <ul class="newsList">
          
          	    <s:iterator value="lsNew">
	      
			       <li>
		              <div class="newsBox">
		                <a href="<%=basePath+"IndexAction!getNewsById.do?id="%><s:property value="id"/>"><s:property value="title"/></a> 
		                <span><s:property value="title2"/></span>
		              </div>
		              <p>
		                <span class="fl"><s:property value="content" escape="false"/></span>
		                <span class="fr"><s:property value="ftime"/></span>
		              </p>
		            </li>
								
				</s:iterator>
          
          </ul>
        </div>
      </div>
      <div class="page_wrap">
        <jsp:include page="/frontJsp/page.jsp">
			<jsp:param name="url" value="<%=basePath+"IndexAction!onSearch.do"%>"/>
		</jsp:include>
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
