<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String deptid = request.getAttribute("deptid").toString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户列表</title>
<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/layout.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/main.css" type="text/css" />
	<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
</head>

<body>
<div class="layout">
    <div class="main H800" id="main">
    	<div class="user_list">
        	
            <a href="#" onclick="addUser('<%=deptid %>')">添加</a>
        	<div class="table_list W961">
              <div class="tt">查询结果如下</div>
              <table cellpadding="0" cellspacing="0" width="100%" class="th-white">
                <tr>
                  <th scope="col" width="116">用户名</th>
                  <th scope="col" width="200">电话</th>
                  <th scope="col" width="172">操作</th>
                </tr>
                <s:iterator value="uList">
	                <tr>
	                  <td><s:property  value="user_name" /></td>
	                  <td><s:property value="mobile" /></td>
	                  <td>
	                 <s:if test="status==0">
						<a href="#" onclick="goDelete('<s:property value="user_id"/>',1)">冻结</a>
						</s:if>
						<s:else><a href="#" onclick="goDelete('<s:property value="user_id"/>',0)">激活</a></s:else>
	                  
	                  </td>
	                </tr>
                </s:iterator>
              </table>
          </div>
        </div>
    </div>

</div>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
function addUser(departmentid){
		var url = "<%=path %>/userAction!goAddDeptUser.do?deptid="+departmentid;
		window.location.href = url;
	}
function goUpdate(id){
		var url = "<%=path %>/userAction!goUpdate.do?id="+id;
		window.location.href = url;
	}

	function goDelete(id,status){
		if(confirm("确定操作吗？")){
			$.ajax({
	      	type: "POST",
	     	url: "userAction!updateUserState.do?id="+id+"&status="+status,
	      	success: function(msg){
	        	 var url = "<%=path%>/userAction!queryDeptUserList.do?deptid="+'<%=deptid%>';
	        	 window.location.href = url;
	     		}
	  		});
			}
	}
</script>
</body>
</html>
