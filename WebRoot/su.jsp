<%@ page language="java" contentType="text/html; charset=Utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'su.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
    
    <table>
		<thead>
			<tr>

				<th width="100" align="center">用户名</th>
				<th width="100" align="center">密码</th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="u">
				<tr >

					<td><s:property value="username" /></td>
					<td><s:property value="password" /></td>

				</tr>
			</s:iterator>

		</tbody>
    </table>
  </body>
</html>
