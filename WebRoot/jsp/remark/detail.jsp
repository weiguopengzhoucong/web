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
<title>安全局</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jsp/movie/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jsp/movie/css/layout.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jsp/movie/css/main.css" />
</head>

<body>
	<div id="layout" class="fn-clear">
		<!--头部-->
		<div id="header"></div>
		<!--主体框-->
		<div id="contanier">

			<div id="main">
				<form action="#" method="post" id="form1" 
					name="form1">
					<div class="column">

						<h3>评论信息</h3>

						<label> <span class="col_text">评论用户：</span>
						<input
							type="text"
							class="label_text_370" value="<s:property value="%{remark.user.getUser_name()}"/>"
							readonly="readonly"/>
						</label> 
						<label> <span class="col_text">评论日期：
						</span>
						<input
							type="text"
							class="label_text_370" value="<s:property value="%{remark.happentime}"/>"
							readonly="readonly"/>
						</label> 
						<label> <span class="col_text">分数：
						</span>
						<input
							type="text"
							class="label_text_370" value="<s:property value="%{remark.score}"/>"
							readonly="readonly"/>
						</label>
						<label>
						<span class="col_text">课程：</span>
						<s:if test="remark.course!=null">
							<input
							type="text"
							class="label_text_370" value="<s:property value="%{remark.course.getCname()}"/>"
							readonly="readonly"/>
						</s:if>
						</label>
						<label>
						<span class="col_text">评论：</span>
						<textarea rows="5" cols="80" readonly="readonly"><s:property value="%{remark.content}"/></textarea>
						</label>
						<s:if test="remark.childRemark!=null">
							<label>
							<span class="col_text">机构回复：</span>
							<textarea rows="5" cols="80" readonly="readonly"><s:property value="%{remark.childRemark.content}"/></textarea>
							</label>
						</s:if>
						
					</div>
					
				</form>
				
					<input type="image"
					src="/web/jsp/movie/images/return-btn.jpg" value="返回"
					onclick="history.go(-1)" />
			</div>


			<!--mainOVer-->
		</div>

	</div>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
	function saveYh(){
		var content = document.getElementById("content");
		if(content.value==""){
			alert("内容不能为空");
			return;
		}
		$.ajax({
      	type: "POST",
     	url: "remarkAction!saveRemark.do",
      	data: $('#form1').serialize(),
      	success: function(msg){
      		 if(msg==0){
       		 	alert( "评论失败" );
       		 }else{
       		 	alert( "评论成功" );
       		 	location.reload() ;
       		 }
     		}
  		});
		
	}
	</script>
</body>

</html>
