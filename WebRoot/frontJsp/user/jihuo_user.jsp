<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String username = (String)request.getAttribute("username");

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
  <center>
  		您的账号未激活,请到邮箱激活后在操作,如重新发送激活邮件
    	<input type="button" value="重新发送激活邮件" id="btn_re_send" onclick="reSenEmail();" />
  </center>
  		
  <!-- main end -->
  <!-- footer start -->
  <div class="footer">
   	<jsp:include page="/frontJsp/footer.jsp"/>
  </div>
  <!-- footer end -->
</div>
<script type="text/javascript">
function reSenEmail() {
		var username = '<%=username%>';
	
		$.ajax({
      	type: "POST",
     	url: "frontUserAction!reSendEmail.do?username="+username,
      	data: $('#userForm').serialize(),
      	success: function(msg){
      		 if(msg==0){
       		 	alert( "重发失败" );
       		 }else if(msg==1) {
       		 	alert( "重发成功" );
       		 	$("#btn_re_send").attr("enable","true");
       		 
       		 }
     		}
  		});
	}
</script>
</body>
</html>
