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

	
	
<script type="text/javascript">
function subForm1() {
		var username = document.getElementById("username").value;
		var password1 = document.getElementById("password1").value;
		var password2 = document.getElementById("password2").value;
		var message = document.getElementById("message");
		if(username==""){
			message.innerHTML = "请输入正确的邮箱";
			return false;
		}
		 var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		  if(username!=""&&!myreg.test(username)){
		  	message.innerHTML = "请输入正确的邮箱";
			return false;
		  }
		
		if(password1==""){
			message.innerHTML = "请输入密码";
			return false;
		}
		if(password2==""){
			message.innerHTML = "请输入确认密码";
			return false;
		}
		if(password1!=""&&password2!=""&&password1!=password2){
			message.innerHTML = "两次密码输入不相同";
			return false;
		}
		$.ajax({
      	type: "POST",
     	url: "frontUserAction!saveRegister.do",
      	data: $('#userForm').serialize(),
      	success: function(msg){
      		 if(msg==0){
       		 	alert( "注册失败" );
       		 }else if(msg==1) {
       		 	alert( "注册成功" );
       		 	var url = "<%=path%>/frontUserAction!forward.do?_value=jihuo_user&username="+username;
        		 window.location.href = url;
       		 }else{
       		 	message.innerHTML = "该邮箱已被注册";
				return false;
       		 }
     		}
  		});
	}
</script>
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
        
        <div class="newsWrap">
          <ul class="newsList">
               <form action="#" method="post" id="userForm" name="userForm" onsubmit="return false;">
          		 <li>
            	<label>邮箱:</label>
                <div class="column">
               	 <input type="text" name="user.user_name" id="username"  class="width150"/>
                </div>
            	</li>
          		 <li>
            	<label>密码:</label>
                <div class="column">
               	 <input type="password" name="user.password" id="password1" class="width150"/>
                </div>
            	</li>
            	<li>
            	<label>确认密码:</label>
                <div class="column">
               	 <input type="password" id = "password2"  class="width150"/>
                </div>
            	</li>
            	<li>
                <div class="column">
                  <label id="message"></label>
                  <br/>
               	 <input type="button" value="提交" onclick="subForm1()"/>
                </div>
            	</li>
            	</form>
          </ul>
        </div>
      </div>
    
    </div>
    <div class="mainRt">
      <div class="sidebar news">
      </div>
    </div>
    <div class="share">
      <div class="share_title">分享</div>
      <div class="share_to">
          <a class="wechat" href="#">wechat</a>
          <a class="sinaweibo" href="#">sinaweibo</a>
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
