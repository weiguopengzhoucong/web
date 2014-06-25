<%@page import="com.pxjg.module.course.entity.Course"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Course course = (Course)request.getAttribute("course");
if(course==null){
course = new Course();
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>网站后台管理系统</title>
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
		if (document.getElementById("course.Cname").value == null
				|| document.getElementById("course.Cname").value == "")
				 {
			alert('请检查表单完整性');

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
				<form action="courseAction!updateCourse.do" method="post" id="form1" enctype="multipart/form-data"
					name="form1">
					<div class="column">
						<input type="hidden" name="course.id" id="course.Id" class="label_text_370" value="<%=course.getId() %>" />
						<input type="hidden" name="course.deptid" id="course.Deptid" class="label_text_370" value="<%=course.getDeptid() %>" />
						<input type="hidden" name="course.num" id="course.Num" class="label_text_370" value="<%=course.getNum() %>" />
						<input type="hidden" name="course.c1" id="course.C1" class="label_text_370" value="<%=course.getC1() %>" />
						<input type="hidden" name="course.c2" id="course.C2" class="label_text_370" value="<%=course.getC2() %>" />
						<input type="hidden" name="course.c3" id="course.C3" class="label_text_370" value="<%=course.getC3() %>" />
						<input type="hidden" name="course.url" id="course.url" class="label_text_370" value="<%=course.getUrl() %>" />
						<label> <span class="col_text">课程分类：</span> <input
							type="text" name="course.type"
							id="course" class="label_text_370" value="<%=course.getType() %>" /> <em> 注：限20字以内</em> 
						</label> 
						
						<label> <span class="col_text">课程名称：</span> <input
							type="text" name="course.cname"
							id="course.Cname" class="label_text_370"  value="<%=course.getCname() %>"  /> <em> 注：限20字以内</em> 
						</label> 
						<label> <span class="col_text">费用：</span> <input
							type="text" name="course.money"
							id="course.Money" class="label_text_370" value="<%=course.getMoney() %>" /> <em> 注：限20字以内</em> 
						</label> 
						<label> <span class="col_text">使用年龄段：</span> <input
							type="text" name="course.sage"
							id="course.Sage" class="label_text_370" value="<%=course.getSage() %>" /> <em> 注：限20字以内</em> 
						</label> 
						
						<label> <span class="col_text">培训周期：</span> <input
							type="text" name="course.zhouqi"
							id="course.Zhouqi" class="label_text_370" value="<%=course.getZhouqi() %>" /> <em> 注：限20字以内</em> 
						</label> 
						
						<label> <span class="col_text">培训周期：</span> <input
							type="text" name="course.skey"
							id="course.Skey" class="label_text_370" value="<%=course.getSkey() %>" />
						</label> 
						
						<label>
						<span class="col_text">排序</span>
						<input type="text"
								id="seq" name="course.seq" class="label_text_370" style="width:200px;" value="<%=course.getSeq() %>" /> 
					    </label>
						<label>
						<span class="col_text">上传图片：</span>
							
							<input type="file" id="upload" name="myFile" class="upload_img_btn"
								style="width:200px;"
								onchange="setImagePreview(this.form, this.form.upload.value);"
								value="上传图片" />
								<em>注：200K以内，jpg格式,宽90×高70像素</em>
							<div class="img">
								<div id="localImag">
										<%
											if(!"".equals(course.getUrl())&&course.getUrl()!=null){
													%>
														<img id="preview" src="<%=basePath+course.getUrl()%>" width="90px" height="70" style="diplay:diplay" />
													<%
												}else{
													%>
														<img id="preview" width=-1 height=-1 style="diplay:none" />
													<%
												}
											
											 %>
										
								</div>
							</div>
					    </label>
						<label>
						<span class="col_text">内容：</span>
						</label>
					</div>
					<textarea rows="30" cols="50" name="course.content" id="content"><%=course.getContent() %> </textarea>
				</form>
				<input type="image"
					src="<%=basePath%>/images/sure-btn.jpg"
					style="cursor: pointer;padding-left: 100px" onclick="subForm1();" />
				<input type="image"
					src="<%=basePath%>/images/false-btn.jpg" value="取消"
					onclick="history.go(-1)" />

			</div>

		</div>

	</div>


	<script type="text/javascript"> 

        CKEDITOR.replace('content',addUploadButton(this));

        function addUploadButton(editor){

           CKEDITOR.on('dialogDefinition', function( ev ){

               var dialogName = ev.data.name;

               var dialogDefinition = ev.data.definition;

               if ( dialogName == 'image' ){

                   var infoTab = dialogDefinition.getContents( 'info' );

                   infoTab.add({

                       type : 'button',

                       id : 'upload_image',

                       align : 'center',

                       label : '上传',

                       onClick : function( evt ){

                           var thisDialog = this.getDialog();

                           var txtUrlObj = thisDialog.getContentElement('info', 'txtUrl');

                           var txtUrlId = txtUrlObj.getInputElement().$.id;

                           addUploadImage(txtUrlId);

                       }

                   }, 'browse'); //place front of the browser button

               }

           });

       }

        function addUploadImage(theURLElementId){

           var uploadUrl = "<%=request.getContextPath()%>/courseAction!uploadsFiles.do";
            //这是我自己的处理文件/图片上传的页面URL

           var imgUrl = window.showModalDialog(uploadUrl);

       //在upload结束后通过js代码window.returnValue=...可以将图片url返回给imgUrl变量。

       //更多window.showModalDialog的使用方法参考

           var urlObj = document.getElementById(theURLElementId);

           urlObj.value = imgUrl;

           urlObj.fireEvent("onchange"); //触发url文本框的onchange事件，以便预览图片

       }

       

</script>
</body>
</html>
