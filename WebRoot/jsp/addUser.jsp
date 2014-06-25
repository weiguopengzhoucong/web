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
                    <input type="hidden" id="deptid"  name="user.deptid" value="${deptid}"/>
                    <div class="add_tb">
                    	<label><span></span><h2>新增用户</h2></label>
                        <label><span>用户帐号：</span><s:textfield name="user.user_name"  id="username" cssClass="add_text"/></label>
                         <label><span>真实姓名：</span><s:textfield name="user.real_name"  id="realname" cssClass="add_text"/></label>
                        <label><span>电话号码：</span><s:textfield name="user.mobile"  id="phone" cssClass="add_text"/></label>
                        <label><span>用户说明：</span><s:textfield name="user.remark1"  id="remark" cssClass="add_text"/></label>
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
    	
     </div>
</div>
<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
  function saveUser(){
		var username = document.getElementById("username");
		var realname = document.getElementById("realname");
		var deptid = document.getElementById("deptid").value;
		if(username.value==""){
			alert("用户名不能为空");
			return;
		}
		if(realname.value==""){
			alert("真实名不能为空");
			return;
		}
		$.ajax({
      	type: "POST",
     	url: "userAction!addDeptUser.do",
      	data: $('#userForm').serialize(),
      	success: function(msg){
      		 if(msg==0){
       		 	alert( "添加失败" );
       		 }else if(msg==1) {
       		 	alert( "添加成功" );
       		 	 var url = "<%=path%>/userAction!queryDeptUserList.do?deptid="+deptid;
        		 window.location.href = url;
       		 }else{
       		 	alert( "用户已存在" );
       		 }
     		}
  		});
		
	}
</script>
</body>
</html>
