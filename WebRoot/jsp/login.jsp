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
<title>哈得力网站后台管理系统</title>
<link rel="stylesheet"  href="<%=path %>/css/base.css" type="text/css" />
<link rel="stylesheet" href="<%=path %>/css/layout.css" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

	document.onkeydown=function(event)
	{
		e = event ? event :(window.event ? window.event : null);
		if(e.keyCode==13)
		{
			login()
		}
	}
	
	
    
     
	function login(){
		var username = document.getElementById("username");
		var pwd = document.getElementById("password");
		if(username.value==""||pwd.value==""){
			alert("请输入账号和密码！");
			return;
		}
		$.ajax({
      	type: "POST",
     	url: "userAction!login.do",
      	data: $('#loginForm').serialize(),
      	success: function(msg){
      		 if(msg==0){
       		 	alert( "用户名或密码错误" );
       		 }else if(msg==3){
       		 	alert("普通用户无法登陆");
       		 }
       		 else{
       		 	 var url = "<%=path%>/indexAction.do";
        		 window.location.href = url;
       		 }
     		}
  		});
		
	}
</script>
</head>

<body>
<div class="login-back">
<form action="usersAction!login.do" method="post" id="loginForm" name="loginForm">
	<div class="login-box">
    	<h1>安全局移动办公</h1>
        <h2><img src="<%=path %>/images/logo.jpg" width="136" height="55" alt="安全局" /></h2>
        <div class="login">
        	<div class="login-text">
                <label>
                    <span></span>
                     <s:textfield id="username" name="user.user_name" cssClass="I-text"/>
                </label>
                <label>
                    <span></span>
                     <s:password id="password" name="user.password" cssClass="I-text"/>
                </label>
            </div>
            <div class="login-btn" onclick="login()">
            	<input type="button" class="login-in-btn"   />
            </div>
        </div>
    </div>
    </form>
</div>
</body>
</html>


