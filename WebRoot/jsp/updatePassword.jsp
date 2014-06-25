<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>哈得力网站后台管理系统</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jsp/movie/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jsp/movie/css/layout.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jsp/movie/css/main.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
function subForm1() {
		
		if (document.getElementById("oldPassword").value == null
				|| document.getElementById("oldPassword").value == ""
				||document.getElementById("password1").value == null
				|| document.getElementById("password1").value == ""
				||document.getElementById("password2").value == null
				|| document.getElementById("password2").value == "")
				 {
			alert('请检查表单完整性');

		} else {
			if(document.getElementById("oldPassword").value!=document.getElementById("password").value){
				alert("原密码不正确");
				return false;
			}
			if(document.getElementById("password1").value!=document.getElementById("password2").value){
				alert("密码不一致");
				return false;
			}
  			$.ajax({
	      	type: "POST",
	     	url: "userAction!savePassword.do",
	      	data: $('#form1').serialize(),
	      	success: function(msg){
	      		 if(msg==0){
	       		 	alert( "修改失败" );
	       		 }else {
	       		 	alert( "修改成功，请重新登录" );
       		 		var url = "<%=path%>/userAction!logout.do";
        			window.location.href = url;
	       		 }
	     		}
	  		});
			
		}

	}
</script>
</head>

<body>
	<div id="layout" class="fn-clear">
		<div id="header"></div>
		<div id="contanier">
			

			<div id="main">
				<form action="userAction!savePassword.do" method="post" id="form1" 
					name="form1">
					<div class="column">

						<h3>修改密码</h3>
						<input type="hidden" value="<s:property value="user.password"/>" id="password"/>
						<label> <span class="col_text">原密码：</span> <input
							type="password" 
							id="oldPassword"  class="label_text_370" /> 
						</label> 
						<label>
						<span class="col_text">新密码：</span>
						
						<input
							type="password" 
							id="password1" name="user.password" class="label_text_370" /> 
						</label>
						<label>
						<span class="col_text">新密码：</span>
						
						<input
							type="password" 
							id="password2" class="label_text_370" /> 
						</label>
					</div>
					
				</form>
				<input type="image"
					src="/web/jsp/movie/images/sure-btn.jpg"
					style="cursor: pointer;padding-left: 100px" onclick="subForm1();" />
				<input type="image"
					src="/web/jsp/movie/images/return-btn.jpg" value="取消"
					onclick="history.go(-1)" />

			</div>


			<!--mainOVer-->
		</div>

	</div>






	
</body>
</html>
