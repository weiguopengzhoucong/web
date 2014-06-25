<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
			String select_type = request.getParameter("select_type");
			String select_value = request.getParameter("select_value");
			
			String order = request.getParameter("order");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

	<jsp:include page="/frontJsp/js_inc.jsp"></jsp:include>
 	<link type="text/css" href="<%=basePath %>frontJsp/css/list.css" rel="stylesheet" /> 
<div class="container" style="height:800px;">
				<div class="btn_list_wrap">
			     <a class="cur" href=""><span id="course_type">面授品牌及课程(<s:property value="nList.size"/>)</span></a>
					<!-- 	     <a href=""><span>网校及课程(3)</span></a> -->
		     </div>
		     <div class="orderBy">
			     <a id="order1" class="cur" onclick="onOrderBy(1)">品牌默认排序</a>
			     <a id="order2" onclick="onOrderBy(2)">按评价数排序</a>
			     <a id="order3" onclick="onOrderBy(3)">按关注度排序</a>
		     </div>
		     <div class="list_wrap">
				<s:iterator value="nList">
					<ul class="course_list">
						<li class="course_item">
							<div class="title num1">
								<span>no.1</span>
								<s:property value="deptname" />
							</div>
							<div class="course_info">
								课程：<a target="parent" href="<%=basePath + "IndexAction!CourseDetail.do?id="%><s:property value="course.id" />"><s:property value="course.cname" />
								</a>
								<s:property value="course.content" />
								<a href="#">更多&gt;&gt;</a>
							</div>
							<div class="address">
								网店（1）：
								<s:property value="address" />
								<a class="gotomap" target="_parent" href="<%=basePath + "IndexAction!forward.do?_value=map&id="%><s:property value="id"/>">地图</a>
								<a class="gotomap" onclick="userCollection('<s:property value="course.zhouqi"/>','课程','<s:property value="course.seq"/>')"><s:property value="course.zhouqi" /></a>
							</div>
							<div class="hot_line">
								<span class="hot_line_icon"></span>
								<s:property value="tel" />
							</div>
							<div class="pic_area">
		
								<img alt="" src="<%=basePath%><s:property value="url"  escape="false" />" width="140px;" height="110px;" /> <a
									target="_parent" href="<%=basePath + "IndexAction!DeptDetail.do?id="%><s:property value="id"/>">
									<s:property value="deptname" />
								</a>
							</div>
							<div class="free_area">
								<p class="fontSize22">
									<s:property value="yh.name" />
								</p>
								<!-- 					     <p class="fontSize16"><s:property value="yh.content"/></p> -->
								<!-- 					     <p>***额外再省</p> -->
								<!-- 					     <p>最高<span class="fontSize28">120</span>元</p> -->
								<a class="pingjia" href="#"><span>2 </span>条真实评价</a> <span
									class="zixun"><s:property value="num" />人浏览咨询</span>
							</div></li>
						</ul>
				</s:iterator>
				<div class="page_wrap">
					<!-- 				 -->
					<jsp:include page="/frontJsp/page.jsp">
						<jsp:param name="url"
							value="<%=basePath+"IndexAction!onSearch.do"%>" />
						<jsp:param name="select_type"
							value="<%=request.getParameter("select_type")%>" />
						<jsp:param name="select_value"
							value="<%=request.getParameter("select_value")%>" />
					</jsp:include>
				</div>
			</div>
</div>

<script type="text/javascript">
<!--

	
	function userCollection(state,type,id){

		if(state=="收藏"){
			//
				$.ajax({
					type : "POST", // 设置请求类型为"POST"，默认为"GET"
					url : '<%=basePath%>/collectionAction!insertCollection.do?type='+type+'&cid='+id,
					cache : false,
					asyn : false,
					datatype : "json",
					success : function(data) {

						if(data == 1){
							alert("收藏成功");
							location.href="<%=basePath%>IndexAction!onSearch.do?order="+order+"&select_type=<%=select_type%>&select_value=<%=select_value%>";
			
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
							location.href="<%=basePath%>IndexAction!onSearch.do?order="+order+"&select_type=<%=select_type%>&select_value=<%=select_value%>";
			
						}
						

					}
						
				});
			
		
		}
	
	}
	

	$(function() {
		var order = "<%=order%>";
			if(order !=null){
					$("#order"+order).attr("class","cur");
					if(order==1){
						$("#order2").attr("class","");
						$("#order3").attr("class","");
					}	
					
				   if(order==2){
						$("#order1").attr("class","");
						$("#order3").attr("class","");
					}	
					
					if(order==3){
						$("#order1").attr("class","");
						$("#order2").attr("class","");
					}	
			
			}
		});
		
		
		function onOrderBy(order){
		
			$("#order"+order).attr("class","cur");
			if(order==1){
				$("#order2").attr("class","");
				$("#order3").attr("class","");
			}	
			
		   if(order==2){
				$("#order1").attr("class","");
				$("#order3").attr("class","");
			}	
			
			if(order==3){
				$("#order1").attr("class","");
				$("#order2").attr("class","");
			}	
			
			
			
			location.href="<%=basePath%>IndexAction!onSearch.do?order="+order+"&select_type=<%=select_type%>&select_value=<%=select_value%>";
			
		
		}
//-->
</script>
	
