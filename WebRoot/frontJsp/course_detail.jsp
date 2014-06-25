<%@page import="com.pxjg.module.user.entity.User"%>
<%@page import="com.pxjg.module.news.entity.News"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

User user = (User) session.getAttribute("userBean");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<jsp:include page="/frontJsp/js_inc.jsp"></jsp:include>
<link type="text/css" href="<%=basePath %>frontJsp/css/list.css" rel="stylesheet" />
<link type="text/css" href="<%=basePath %>frontJsp/css/detail.css" rel="stylesheet" />  
</head>

<body>
<div class="container">
  <!-- header start-->
  <div class="header">
    	   <jsp:include page="/frontJsp/header.jsp" />
    	   
  </div>
  <!-- header end -->
  
  	<input type="hidden" name="deptid" id="ditpid" value="<s:property value="oneCourse.deptid"/>" />
  	<input type="hidden" name="courseid" id="courseid" value="<s:property value="oneCourse.id"/>" />
  
  <!-- main start -->
  <div class="mainWrap clearfix">
    <div class="mainLf">
     <div class="detail_con">
     	   <h3 class="newsTitle">
			          <s:property value="oneCourse.cname"/>
			          <p class="newsInfo">
			          	所属分类:<s:property value="oneCourse.type"/>
			            <span class="date">浏览数:<s:property value="oneCourse.num"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            	<%
			            		if(user!=null){
			            			%>
			            				<a id="course_collection_id" onclick="userCollection('<s:property value="oneCourse.c3"/>','课程','<s:property value="oneCourse.c2"/>','<s:property value="oneCourse.id"/>')"><s:property value="oneCourse.c3"/></a>
			            			<%
			            		}
			            	 %>
			            </span>
			          </p>
			     </h3>
			        <div class="info_con">
			        	所需费用: <s:property value="oneCourse.money" escape="false" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        	使用年龄段:<s:property value="oneCourse.sage" escape="false" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        	培训周期: <s:property value="oneCourse.zhouqi" escape="false" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			           		    <s:property value="oneCourse.content" escape="false" />
			        </div>
	  </div>
	   <div class="detail_title"><span class="icon5">学员评论</span>
	   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		<%
	    		if(user!=null){
	    			%>
	   		<a href="<%=basePath + "remarkAction!goRemark.do?type=1&newsid="%><s:property value="oneCourse.id"/>&deptid=<s:property value="oneCourse.deptid"/>" style="align:right;">评论</a>
		   	<%
		    		
		    		}
		    	%>
	   
	   </div>
	    <div class="detail_con">
	    	<div class="comment_wrap" id="remark">
	    		
	    	</div>
	    </div>
	  
    </div>
   
    
    <div class="mainRt">
      <div class="sidebar news">
         <jsp:include page="/frontJsp/top_news.jsp" />
      </div>
    </div>
    <div class="share">
       <div class="share_to">
			<jsp:include page="./share.jsp" />
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


	
<script type="text/javascript">

	$(function(){
	
	var ditpid = $("#ditpid").val();
	var courseid =  $("#courseid").val();
		
		$.ajax({
					type : "POST", // 设置请求类型为"POST"，默认为"GET"
					url : '<%=basePath%>/remarkAction!frontRemarkList.do?type=1&deptid='+ditpid+'&newsid='+courseid,
					cache : false,
					asyn : false,
					datatype : "json",
					success : function(data) {
						
							var d = $.parseJSON(data);
							var txt = "";
							for(var i=0;i<d.length;i++){
								var remark = d[i];
								
								txt+="<div class='comment_item'>";
					    		txt+="	<div class='comment_title'><span>"+remark.user.user_name+"</span>说：<span>"+remark.content+"</span></div>";
					    		if(remark.childRemark!=null){
					    			txt+="<p>"+remark.childRemark.user.user_name+"回复：<span>"+remark.childRemark.content+"</span></p>";
					    		}
					    		
					    		txt+="</div>";
							
							
							
							}

						$("#remark").html(txt);

					}
						
				});
	
	});


	function userCollection(state,type,id,courseid){
		state = $("#course_collection_id").html();
		if(state=="收藏"){
			//
				$.ajax({
					type : "POST", // 设置请求类型为"POST"，默认为"GET"
					url : '<%=basePath%>/collectionAction!insertCollection.do?type='+type+'&cid='+courseid,
					cache : false,
					asyn : false,
					datatype : "json",
					success : function(data) {

						if(data == 1){
							alert("收藏成功");
							$("#course_collection_id").html("取消收藏");
						}else{
							alert("收藏失败,请重新登陆后在操作");
						}
						

					}
						
				});
			
		}else{
			
			$.ajax({
					type : "POST", // 设置请求类型为"POST"，默认为"GET"
					url : '<%=basePath%>/collectionAction!deleteCollection.do?id='+id,
					cache : false,
					asyn : false,
					datatype : "json",
					success : function(data) {

						if(data == 1){
							alert("取消收藏成功");
							$("#course_collection_id").html("收藏");
						}else{
							alert("收藏失败,请重新登陆后在操作");
						}
						

					}
						
				});
			
		
		}
	
	}

</script>

</body>
</html>
