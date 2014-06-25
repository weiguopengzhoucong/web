<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String departmentid = request.getAttribute("departmentid").toString();
String userid = request.getAttribute("userid").toString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>安全局</title>
<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/layout.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/main.css" type="text/css" />
</head>

<body>
<div class="layout">
    <div class="main" id="main">
    	<div class="user_list">
        	<div class="user_info">
            	<div class="title">
                	用户管理
                </div>
                <div class="adduser">
                	<div class="add_th">
                    	基本信息
                    </div>
                    <form action="#" method="post" id="userForm" name="userForm" onsubmit="return false;">
                    <div class="add_tb">
                    	<label><span></span><h2>修改用户</h2></label>
                    	<a href="<%=path%>/department!goUpdateUserDept.do?department.id=0&flag=0&userid=<%=userid %>" target="main">修改职位</a>
                    	<s:hidden  name="user.userid" value="%{user.userid}"  />
                    	<s:hidden  name="user.department" value="%{user.department}"  />
                        <label><span>用户帐号：</span><s:textfield name="user.username" value="%{user.username}" id="username" readonly="true" cssClass="add_text"/></label>
                        <label><span>真实姓名：</span><s:textfield name="user.realname" value="%{user.realname}" id="realname" readonly="true" cssClass="add_text"/></label>
                        <label><span>用户密码：</span><input name="user.password" type="password" value="${user.password}" id="password"/></label>
                        <label><span>重复密码：</span><input type="password" value="${user.password}" id="password2"/></label>
                        <label><span>电话号码：</span><s:textfield name="user.phone" value="%{user.phone}"  id="phone" cssClass="add_text"/></label>
                        <label><span>所属店面：</span>
                        <s:select list="%{shopList}" name="user.shopid" listKey="id" listValue="name" />
                        </label>
                        <label><span>浏览权限：</span>
                        	<select name="user.role">
                        		<s:if test="user.role==1">
                        			<option value="1">浏览全部</option>
                        			<option value="1">浏览自身</option>
                        		</s:if>
                        		<s:else>
                        			<option value="1">浏览自身</option>
                        			<option value="1">浏览全部</option>
                        		</s:else>
                        	</select>
                        </label>
                        <label><span>用户说明：</span><s:textfield name="user.remark" value="%{user.remark}" id="remark" cssClass="add_text"/></label>
                        <span></span><input type="image" src="<%=path %>/images/save_btn.jpg" onclick="saveUser()"/>
                        <br/><br/><br/>
                        <input type="button" value="返回" style="width:90px;height:25px;background:gray;color:white;font-weight:bold" onclick="history.go(-1)"/>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
    	欢迎登录安全局管理系统
     </div>
</div>
<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
  function saveUser(){
		var username = document.getElementById("username");
		var pwd = document.getElementById("password");
		var pwd2 = document.getElementById("password2");
		var phone = document.getElementById("phone");
		var remark = document.getElementById("remark");
		if(username.value==""){
			alert("用户名不能为空");
			return;
		}
		if(pwd.value==""){
			alert("密码不能为空");
			return;
		}
		if(pwd2.value==""){
			alert("确认密码不能为空");
			return;
		}
		if(pwd2.value!=pwd.value){
			alert("密码不一致");
			return;
		}
		$.ajax({
      	type: "POST",
     	url: "userAction!updateUser.do",
      	data: $('#userForm').serialize(),
      	success: function(msg){
      		 if(msg==0){
       		 	alert( "修改失败" );
       		 }else {
       		 	alert( "修改成功" );
       		 	 var url = "<%=path%>/userAction!uList.do?departmentid="+<%=departmentid%>;
        		 window.location.href = url;
       		 }
     		}
  		});
		
	}
	
	
</script>
</body>
</html>
