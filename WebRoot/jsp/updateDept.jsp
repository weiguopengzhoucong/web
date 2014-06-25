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
	<script type="text/javascript"
	src="<%=path%>/jsp/movie/js/setImagePreview.js"></script>
	<script type="text/javascript" src="<%=basePath%>ckeditor/ckeditor.js"></script>
<script type="text/javascript">
    window.onload = function()
    {
        CKEDITOR.replace( 'content' );
    };
    
</script>
<script type="text/javascript">
function subForm1() {
		if (document.getElementById("dept.deptname").value == null
				|| document.getElementById("dept.deptname").value == "")
				 {
			alert('请填写机构名称');

		} else {
			form1.submit(); //form1为form的id

		}

	}
</script>
</head>

<body>
	<div id="layout" class="fn-clear">
		<!--头部-->
		<div id="header"></div>
		<!--主体框-->
		<div id="contanier">
			<!--主体-->

			<div id="main">
				<form action="deptAction!updateDept.do" method="post" id="form1" enctype="multipart/form-data"
					name="form1">
					<s:hidden name="dept.id" value="%{dept.id}"/>
					<s:hidden name="dept.url" value="%{dept.url}"/>
					<s:hidden name="dept.shangquanid" value="%{dept.shangquanid}"/>
					<div class="column">

						<h3>公司动态</h3>

						<label> <span class="col_text">名称：</span> <input
							type="text" name="dept.deptname"
							id="dept.deptname" class="label_text_370" value="${dept.deptname}"/> <em> 注：限20字以内</em> 
						</label> 
						<label> <span class="col_text">地址：</span> <input
							type="text" name="dept.address"
							id="dept.address" class="label_text_370" value="${dept.address}"/> <em> 注：限20字以内</em> 
						</label> 
                        <label>
                        <span class="col_text">电话：</span>
                        <input
							type="text" name="dept.tel"
							id="dept.tel" class="label_text_370" value="${dept.tel}"/> <em> 注：限20字以内</em> 
                        </label>
                        <label>
                        <span class="col_text">类型：</span>
                        <s:select list="%{deptTypeList}" name="dept.depttype" listKey="id" listValue="typename" />
                        </label>
                        <label>
                        <span class="col_text">经度：</span>
                        <input
							type="text" name="dept.x"
							id="dept.x" class="label_text_370" value="${dept.x}"/> 
                        </label>
                        <label>
                        <span class="col_text">纬度：</span>
                        <input
							type="text" name="dept.y"
							id="dept.y" class="label_text_370" value="${dept.y}"/> 
                        </label>
						<label> <span class="col_text">简介：</span> 
						<textarea rows="15" cols="70" name="dept.content" id="dept.content">${dept.content}</textarea>
						</label> 
						<label>     
						<span class="col_text">上传图片：</span>
						<input type="file"
								id="upload" name="myFile" class="label_text_370"
								style="width:200px;"
								value="上传图片" /> 
					    </label>
						<label>
						<span class="col_text">内容：</span>
						</label>
					</div>
					<textarea rows="30" cols="50" name="fileUpload" id="content">${dept.description}</textarea>
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
