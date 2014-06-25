<%@page import="com.pxjg.module.user.entity.User"%>
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
<title>机构详情</title>
<jsp:include page="/frontJsp/js_inc.jsp"></jsp:include>
 	<link type="text/css" href="<%=basePath %>frontJsp/css/detail.css" rel="stylesheet" /> 

</head>

<body>
<div class="container">
  <!-- header start-->
  <div class="header">
   	<jsp:include page="/frontJsp/header.jsp"></jsp:include>
<!--     <div class="oper clearfix"> -->
<!--     	<a class="logo" href="#"><img alt="" src="images/logo/logo2.png" /><span>www.flsj.com</span></a> -->
<!--     	<div class="comb"> -->
<!-- 	    	<a href="#">教育宝总站</a>&gt; -->
<!-- 	    	<a href="#">北京教育宝</a>&gt; -->
<!-- 	    	<a href="#">北京英语培训</a>&gt; -->
<!-- 	    	<a href="#">北京小语种培训</a> -->
<!--     	</div> -->
<!--     </div> -->
<!--     <div class="navBg"> -->
<!--       <div class="navCon"> -->
<!--         <ul class="nav"> -->
<!--           <li class="current"><a href="" title="">学校简介</a></li> -->
<!--           <li><a href="" title="">课程导航</a></li> -->
<!--           <li><a href="" title="">校区环境</a></li> -->
<!--           <li><a href="" title="">学院评论</a></li> -->
<!--           <li><a href="" title="">联系我们</a></li> -->
<!--         </ul> -->
<!--       </div> -->
<!--     </div> -->
  </div>
  <!-- header end -->
  
  <input type="hidden" name="deptid" id="ditpid" value="<s:property value="dept.id"/>" />
  <!-- main start -->
  <div class="mainWrap clearfix">
    <div class="content_wrap">
	    <div class="banner"><img alt="" src="images/banner_img1.png" /></div>
	    <div class="detail_title">
		    <span class="icon1">学校简介</span> 
		    <%
		    	if(user!=null){
		    		%>
		    			 <span id="dept_collection_id" class="iconcollection" onclick="userCollection('<s:property value="dept.quxianid"/>','机构','<s:property value="dept.shangquanid"/>','<s:property value="dept.id"/>')"><s:property value="dept.quxianid"/></span>
		    		<%
		    	}
		     %>
		   
		</div>
	    <div class="detail_con">
		    <h3><s:property value="dept.deptname"/></h3>
		    <div class="info_con">
			    <s:property value="dept.content" escape="false" />
		    </div>
	    </div>
	    <div class="detail_title"><span class="icon2">课程导航</span></div>
	    <div class="detail_con clearfix">
	    	  <s:iterator value="dept.lsCourse" var="course">
	    	  		<div class="course_nav">
				    	<div class="course_title"><s:property value="#course.cname"/><a class="more" href="#">more</a></div>
				    	<div class="course_body">
					    	<div class="course_info"><s:property value="#course.content" escape="false" /></div>
					    	<div class="course_list"><a href="#"><s:property value="#course.cname"/></a></div>
				    	</div>
			    	</div>
	    	  </s:iterator>
	    </div>
	    <div class="detail_title"><span class="icon3">校区环境</span></div>
	    <div class="detail_con">
	    	<ul class="school_about clearfix">
	    	<s:iterator value="dept.lsNews" var="news">
	    		<li><a href="#"><img alt="" src="<%=basePath %><s:property value="#news.url"/>" /></a></li>
	    	</s:iterator>
	    	</ul>
	    </div>
	    <div class="detail_title"><span class="icon4">联系我们</span></div>
	    <div class="detail_con">
	    	<div class="contactus clearfix">
		    	<dl>
			    	<dd><s:property value="dept.address"/></dd>
		    	</dl>
		    	
		    	<img alt="" src="images/map.jpg" />
		    	<p><span class="phone"><s:property value="dept.tel"/></span><span class="computer">www.fksh.com</span></p>
	    	</div>
	    </div>
	    <div class="detail_title"><span class="icon5">学员评论</span>
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<%
	    		if(user!=null){
	    			%>
	    				<a href="<%=basePath + "remarkAction!goRemark.do?type=0&deptid="%><s:property value="dept.id" escape="false"/>" style="align:right;">评论</a>
	    			<%
	    		
	    		}
	    	%>
	   		
	    
	    </div>
	    <div class="detail_con">
	    	<div class="comment_wrap" id="remark">
	    		
	    	</div>
	    </div>
<!-- 	    <div class="left_menu"> -->
<!-- 	      <div class="menu_item"><a href="#">学校简介</a></div> -->
<!-- 	      <div class="menu_item"><a href="#">课程导航</a></div> -->
<!-- 	      <div class="menu_item"><a href="#">校区环境</a></div> -->
<!-- 	      <div class="menu_item"><a href="#">学员评论</a></div> -->
<!-- 	      <div class="menu_item"><a href="#">联系我们</a></div> -->
<!-- 	      <div class="gotop"><a href="#"></a></div> -->
<!-- 	    </div> -->
	    
	    <div class="share">
	       <div class="share_to">
         
			<jsp:include page="./share.jsp" />
      		</div>
      <div class="gotop"><a href="#"></a></div>
    
	    </div>
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
		
		$.ajax({
					type : "POST", // 设置请求类型为"POST"，默认为"GET"
					url : '<%=basePath%>/remarkAction!frontRemarkList.do?type=0&deptid='+ditpid,
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



	function userCollection(state,type,id,deptid){
		state = $("#dept_collection_id").html();
		if(state=="收藏"){
			//
				$.ajax({
					type : "POST", // 设置请求类型为"POST"，默认为"GET"
					url : '<%=basePath%>/collectionAction!insertCollection.do?type='+type+'&deptid='+deptid,
					cache : false,
					asyn : false,
					datatype : "json",
					success : function(data) {

						if(data == 1){
							alert("收藏成功");
							$("#dept_collection_id").html("取消收藏");
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
							$("#dept_collection_id").html("收藏");
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
