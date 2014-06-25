<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<div class="sideTitle">
           <span class="ttIco">站内新闻</span>
           <a href="<%=basePath%>IndexAction!getTopNewsMore.do" class="more">more</a>
         </div>
         <div class="sideMenu">
         	<ul class="menu" id="topnews"></ul>
	</div>
	

	<script>
				$(function() {
			
							$.ajax({
								type : "POST", // 设置请求类型为"POST"，默认为"GET"
								url : '<%=basePath%>IndexAction!getTopNews.do',
								cache : false,
								asyn : false,
								datatype : "json",
								success : function(data) {
									var d = $.parseJSON(data);
									
									var txt = "";
									
									for(var j=0;j<d.length;j++){
										var url = '<%=basePath+"IndexAction!getNewsById.do?id="%>'+d[j].Id;
										txt+="<li><a href='"+url+"'>"+d[j].Title.substr(0 ,13)+"....</a></li>"; 
									}
									
									$("#topnews").html(txt);
									
								}
							});
			});		
	</script>