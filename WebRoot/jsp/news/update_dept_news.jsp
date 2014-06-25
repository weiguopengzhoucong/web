<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pxjg.module.news.entity.News"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
News news = (News)request.getAttribute("news");


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
		if (document.getElementById("news.Title").value == null
				|| document.getElementById("news.Title").value == "")
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
				<form action="newsAction!updateDeptNews.do" method="post" id="form1" enctype="multipart/form-data"
					name="form1">
					<div class="column">
						<input type="hidden" name="news.Id" id="news.Id" class="label_text_370" value="<%=news.getId() %>" />
						<input type="hidden" name="news.Deptid" id="news.Deptid" class="label_text_370" value="<%=news.getDeptid() %>" />
						<input type="hidden" name="news.Num" id="news.Num" class="label_text_370" value="<%=news.getNum() %>" />
						<input type="hidden" name="news.State" id="news.State" class="label_text_370" value="<%=news.getState() %>" />
						<input type="hidden" name="news.C1" id="news.C1" class="label_text_370" value="<%=news.getC1() %>" />
						<input type="hidden" name="news.C2" id="news.C2" class="label_text_370" value="<%=news.getC2() %>" />
						<input type="hidden" name="news.C3" id="news.C3" class="label_text_370" value="<%=news.getC3() %>" />
						<input type="hidden" name="news.Userid" id="news.Userid" class="label_text_370" value="<%=news.getUserid() %>" />
						<input type="hidden" name="news.Type" id="news.Type" class="label_text_370" value="<%=news.getType() %>" />
						<input type="hidden" name="news.url" id="news.url" class="label_text_370" value="<%=news.getUrl() %>" />
						<input type="hidden" name="news.Top" id="news.Top" class="label_text_370" value="<%=news.getTop() %>" />
						
						<label> <span class="col_text">标题：</span> <input
							type="text" name="news.Title"
							id="news.Title" class="label_text_370" value="<%=news.getTitle() %>" /> <em> 注：限20字以内</em> 
						</label> 
						
						<label> <span class="col_text">来源：</span> <input
							type="text" name="news.Tiele2"
							id="news.Tiele2" class="label_text_370" value="<%=news.getTitle2() %>"/> <em> 注：限20字以内</em> 
						</label> 
						<label> <span class="col_text">允许回复：</span>
							<select name="news.IsHF" style="width:30%;">
							<%
								if(news.getIsHF()==1){
									%>
										<option value="1">是</option>
							 			<option value="0">否</option>
									<%
								}else{
								%>
										<option value="0">否</option>
										<option value="1">是</option>
							 			
									<%
								}
							
							 %>
							 	
							</select>
						</label> 
						<label>
						<span class="col_text">排序</span>
						<input type="text"
								id="seq" name="news.Seq" class="label_text_370" style="width:200px;" value="<%=news.getSeq() %>" /> 
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
											if(!"".equals(news.getUrl())&&news.getUrl()!=null){
													%>
														<img id="preview" src="<%=basePath+news.getUrl()%>" width="90px" height="70" style="diplay:diplay" />
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
					<textarea rows="30" cols="50" name="news.Content" id="content"><%=news.getContent() %></textarea>
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

           var uploadUrl = "<%=request.getContextPath()%>/newsAction!uploadsFiles.do";
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
