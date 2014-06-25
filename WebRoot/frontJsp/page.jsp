<%@page import="com.pxjg.util.PageBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String url = request.getParameter("url");
PageBean pageBean = (PageBean)request.getAttribute("pageBean");
String param = "";
if(pageBean.getParam()!=null){
	param = pageBean.getParam();
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    
    <title>My JSP 'home-page.jsp' starting page</title>
    
	<title>登录页</title>
	<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<body>
  	共<s:property value="pageBean.total"/>记录&nbsp;&nbsp;
			第<s:property value="pageBean.currentPage"/>页&nbsp;&nbsp;
         <s:url id="url_pre" value="%{url}">   
         <s:param name="pageBean.currentPage" value="pageBean.currentPage-1"></s:param>  
         <s:param name="pageBean.param" value="pageBean.param"></s:param>    
          <s:param name="pageBean.pageSize" value="pageBean.pageSize"></s:param>    
     </s:url>  
     <s:url id="url_next" value="%{url}">   
         <s:param name="pageBean.currentPage" value="pageBean.currentPage+1"></s:param> 
         <s:param name="pageBean.param" value="pageBean.param"></s:param>   
         <s:param name="pageBean.pageSize" value="pageBean.pageSize"></s:param>    
     </s:url>   
      
     <s:if test="pageBean.currentPage==1">
    <s:a href="#">最前一页</s:a>
     </s:if>
     <s:else>
     <s:a href="%{url_pre}">上一页</s:a>
     </s:else>
     <s:if test="pageBean.currentPage+5<pageBean.totalPage">
	     	 <s:bean name="org.apache.struts2.util.Counter" id="counter"> 
	  		<s:param name="first" value="pageBean.currentPage" />
	  		<s:param name="last"  value="pageBean.totalPage<7?pageBean.totalPage:pageBean.currentPage+5" />
	  		 <s:iterator>
	  	 		<s:if test="pageBean.currentPage!=current-1">
			  	 	<a href="#" onclick="jump(<s:property/>)"> 
						[<s:property/>]
						</a> 
	  	 		</s:if>
	  	    	<s:else>
	  	 			<s:property/>
	  		    </s:else>
	         </s:iterator> 
	        </s:bean> 
     </s:if>
	 <s:else>
	 		<s:bean name="org.apache.struts2.util.Counter" id="counter"> 
	  		<s:param name="first" value="pageBean.currentPage>5?pageBean.totalPage-5:1" />
	  		<s:param name="last"  value="pageBean.totalPage" />
	  		 <s:iterator>
	  	 		<s:if test="pageBean.currentPage!=current-1">
			  	 	 <a href="#" onclick="jump(<s:property/>)"> 
						[<s:property/>]
						</a> 
	  	 		</s:if>
	  	    	<s:else>
	  	 			<s:property/>
	  		    </s:else>
	         </s:iterator> 
	        </s:bean> 
	 </s:else>
     
     <s:if test="pageBean.currentPage==pageBean.totalPage||pageBean.totalPage==0">
     <s:a href="#">最后一页</s:a>
       </s:if>
       <s:else>
       <s:a href="%{url_next}">下一页</s:a>
       </s:else> 
  	</body>
</html>
<script>
	function jump(i){
		var url = "<%=url%>";    
		var pageParam = "<%=param%>";   
   	 	window.location.href = url+"?pageBean.currentPage="+i+"&pageBean.param="+pageParam+"&pageBean.pageSize="+'<s:property value="pageBean.pageSize"/>';
	}
	
	function check(v){
		var url = "<%=url%>";
		var pageParam = "<%=param%>";   
   	 	window.location.href = url+"?pageBean.param="+pageParam+"&pageBean.pageSize="+v;
		
	}  
</script>