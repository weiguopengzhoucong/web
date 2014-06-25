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
<script type="text/javascript" src="<%=path%>/js/time/WdatePicker.js"> </script>
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
					<input type="hidden" id="deptorcourse" name="yh.deptorcourse" value="${yh.deptorcourse}"/>
        			<input type="hidden" id="deptorcourseid" name="yh.deptorcourseid" value="${yh.deptorcourseid}"/>
        			<input type="hidden" id="id" name="yh.id" value="${yh.id}"/>
					<div class="column">

						<h3>优惠信息</h3>

						<label> <span class="col_text">名称：</span> <input
							type="text" name="yh.name"
							id="yh.name" class="label_text_370" value="${yh.name }"/> <em> 注：限20字以内</em> 
						</label> 
						<label>
						<span class="col_text">类型：</span>
						<select name="yh.type">
							<s:if test="yh.type==0">
								<option value="0" selected="selected">折扣</option>
								<option value="1">减免</option>
								<option value="2">赠送</option>
							</s:if>
							<s:if test="yh.type==1">
								<option value="0" >折扣</option>
								<option value="1" selected="selected">减免</option>
								<option value="2">赠送</option>
							</s:if>
							<s:if test="yh.type==2">
								<option value="0" >折扣</option>
								<option value="1">减免</option>
								<option value="2" selected="selected">赠送</option>
							</s:if>
						</select>
						</label>
						<label>
						<span class="col_text">起始日期：</span>
						<input value="${startdate }"
						id="mydate" name="yh.startdate" type="text" class="Wdate" style="width:150px;height:30px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></input> 
						</label>
						<label>
						<span class="col_text">截至日期：</span>
						<input value="${enddate}"
						id="mydate" name="yh.enddate" type="text" class="Wdate" style="width:150px;height:30px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></input> 
						</label>
						<label>
						<span class="col_text">内容：</span>
						<textarea rows="3" cols="50" name="yh.content" id="content">${yh.content}</textarea>
						</label>
					</div>
					
				</form>
				<input type="image"
					src="/web/jsp/movie/images/sure-btn.jpg"
					style="cursor: pointer;padding-left: 100px" onclick="saveYh();" />
				<input type="image"
					src="/web/jsp/movie/images/return-btn.jpg" value="取消"
					onclick="history.go(-1)" />

			</div>


			<!--mainOVer-->
		</div>

	</div>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
function saveYh(){
		var name = document.getElementById("yh.name");
		var deptorcourse = ${yh.deptorcourse};
		var deptorcourseid = '${yh.deptorcourseid}';
		if(name.value==""){
			alert("名称不能为空");
			return;
		}
		$.ajax({
      	type: "POST",
     	url: "yhAction!updateYh.do",
      	data: $('#form1').serialize(),
      	success: function(msg){
      		 if(msg==0){
       		 	alert( "修改失败" );
       		 }else{
       		 	alert( "修改成功" );
       		 	 var url = "<%=path%>/yhAction!yhList.do?deptorcourse="+deptorcourse+"&deptorcourseid="+deptorcourseid;
        		 window.location.href = url;
       		 }
     		}
  		});
		
	}
	</script>
</body>

</html>
