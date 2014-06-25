<%@page import="com.pxjg.module.region.entity.Region"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String select_type = request.getParameter("select_type");
String select_value = request.getParameter("select_value");
String order = request.getParameter("order");
Region region = (Region)request.getAttribute("region");
if(region == null){
 region =  new Region();
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>首页</title>
	<jsp:include page="/frontJsp/js_inc.jsp"></jsp:include>
 	<link type="text/css" href="<%=basePath %>frontJsp/css/list.css" rel="stylesheet" /> 
</head>
<body>
<div class="container">
  <!-- header start-->
  <div class="header">
   	<jsp:include page="/frontJsp/header.jsp"></jsp:include>
  
  </div>
  <!-- header end -->
  <!-- main start -->
  <div class="mainWrap clearfix">
    <div class="mainLf">
     <div class="seltion_cont">
     	<a class="arr_down" href="#"></a>
     	<dl>
	     	<dt>类&nbsp;别：</dt>
	     	<dd id="dept_type">
	     	</dd>
     	</dl>
     	<dl>
	     	<dt>地&nbsp;区：</dt>
	     	<dd id="area_list">
	     	
	     	</dd>
     	</dl>
     	<dl>
	     	<dt>商&nbsp;圈：</dt>
	     	<dd id="shangquan_list">
	     	
	     	</dd>
     	</dl>
     </div>
	     	
     <iframe id="frame_dept" name="frame_dept" src="" scrolling="auto"  frameborder="0" width="100%" height="800">
     
     </iframe>
     		
    </div>
    <div class="mainRt">
      <div class="sidebar news">
         <div class="sideTitle">
           <span class="ttIco">帮选课</span>
           <a href="" class="more">more</a>
         </div>
         <div class="sideMenu selectcourse">
         	<div class="tag_con">
	           <a href="#">口语</a>
	           <a href="#">口语我</a>
	           <a href="#">口语口语</a>
	           <a href="#">口语口语</a>
	           <a href="#">口语口语</a>
	           <a href="#">口语口语</a>
	           <a href="#">口语</a>
	           <a href="#">口语我</a>
	           <a href="#">口语</a>
	           <a href="#">口语我</a>
	           <a href="#">口语口语</a>
	           <a href="#">口语口语</a>
	           <a href="#">口语口语</a>
	           <a href="#">口语口语</a>
	           <a href="#">口语</a>
	           <a href="#">口语我</a>
	           <a href="#">口语</a>
	           <a href="#">口语我</a>
	           <a href="#">口语口语</a>
	           <a href="#">口语口语</a>
         	</div>
           <a class="btn">开始选课</a>
         </div>
      </div>
      <div class="discount_wrap">
      
        <s:iterator value="nList">
        	<s:if test="!yh.name.isEmpty()">
                    <div class="discount_item">
			            <img alt="" src="<%=basePath%><s:property value="url"/>" />
			            <div class="title"><s:property value="yh.name"/></div>
			            <div class="info"><s:property value="yh.content"/></div>
			        </div>
            </s:if>
     	 </s:iterator>
      </div>
  
      <div class="advantage_wrap">
        <div class="advantage_title">选择优势</div>
        <div class="advantage_item">
            <img alt="" src="images/advantage_icon1.png" />
            <div class="title">资源丰富</div>
        </div>
        <div class="advantage_item">
            <img alt="" src="images/advantage_icon2.png" />
            <div class="title">审核严格</div>
        </div>
        <div class="advantage_item">
            <img alt="" src="images/advantage_icon3.png" />
            <div class="title">私人顾问</div>
        </div>
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
	   	<jsp:include page="/frontJsp/footer.jsp"></jsp:include>
	  </div>
  <!-- footer end -->
</div>

<script>

		$(function() {
			
						 $.ajax({
								type : "POST", // 设置请求类型为"POST"，默认为"GET"
								url : '<%=basePath%>IndexAction!getDeptType.do',
								cache : false,
								asyn : false,
								datatype : "json",
								success : function(data) {
									var d = $.parseJSON(data);
									var select_value = '<%=select_value%>';
									var txt = "";
									if(!select_value){
										txt = "<a class='cur' id='type_all' onclick=onType('type_all')>不限</a>";
									}else{
										txt = "<a  id='type_all' onclick=onType('type_all')>不限</a>";
									}
									
									
									for(var j=0;j<d.length;j++){
										var type = d[j];		
										 	for(var x=0;x<type.lsDeptType.length;x++){
										 		if(select_value==type.lsDeptType[x].typename){
										 			 txt+=" <a class='cur' onclick=onType('type"+type.lsDeptType[x].id+"') id='type"+type.lsDeptType[x].id+"' >"+type.lsDeptType[x].typename+"(<span>"+type.lsDeptType[x].lsDepartment.length+"</span>)</a>";
										 		}else{
										 			 txt+=" <a onclick=onType('type"+type.lsDeptType[x].id+"') id='type"+type.lsDeptType[x].id+"' >"+type.lsDeptType[x].typename+"(<span>"+type.lsDeptType[x].lsDepartment.length+"</span>)</a>";
										 		}
							             		
							             	}
							           
									}
									
									$("#dept_type").html(txt);
									
								}
							});
							
							loadDeptIFrame();
							
		});
		
		
		function loadDeptIFrame(){
		
			 $.ajax({
						type : "POST", // 设置请求类型为"POST"，默认为"GET"
						url : '<%=basePath%>IndexAction!childRegionByType.do',
						cache : false,
						asyn : false,
						datatype : "json",
						success : function(data) {
							d = $.parseJSON(data);
							var txt = "<a onclick=onArea('area_all') class='cur' id='area_all' href='#'>不限</a>";
							var shangquantxt = "<a onclick=onShangquan('shangquan_all')  class='cur' id='shangquan_all' href='#'>不限</a>";
							for(var j=0;j<d.lsArea.length;j++){
								var area = d.lsArea[j];
					            txt+=" <a  id='area"+area.re_id+"' onclick=onArea('area"+area.re_id+"')>"+area.region_name+"</a>";
					            
					            for(var x=0;x<area.lsShangquan.length;x++){
					            	var shangquan = area.lsShangquan[x];
					            	 shangquantxt+=" <a  id='shangquan"+shangquan.re_id+"' onclick=onShangquan('shangquan"+shangquan.re_id+"')>"+shangquan.region_name+"</a>";
					            }
					            
							}
							
							$("#area_list").html(txt);
							$("#shangquan_list").html(shangquantxt);
							
						}
				});
						
			$("#frame_dept").attr("src","<%=basePath%>IndexAction!onSearch.do?select_type=<%=select_type%>&select_value=<%=select_value%>");
		
		
		}
		

		
		var d = null;
		var type = "type_all";
		var areaid = "area_all";
		var shangquanid = "shangquan_all";
		
		function onType (id){
			
			$("#"+type).attr("class","");
			type = id;
			$("#"+type).attr("class","cur");
			
			queryList();
		}
		
		function onArea (id){
			
			$("#"+areaid).attr("class","");
			areaid = id;
			$("#"+areaid).attr("class","cur");
			
			//先重新设置商圈信息后在重置部门信息
			shangquanid = "shangquan_all";
			var shangquantxt = "<a onclick=onShangquan('shangquan_all')  class='cur' id='shangquan_all' href='#'>不限</a>";
			
			for(var j=0;j<d.lsArea.length;j++){
						var area = d.lsArea[j];
			          
			            for(var x=0;x<area.lsShangquan.length;x++){
			            	var shangquan = area.lsShangquan[x];
			            	
			            	if(areaid!="area_all"){
		            				if("area"+shangquan.parent_id!=areaid){
		            					continue;
		            				}
				            }
				             shangquantxt+=" <a  id='shangquan"+shangquan.re_id+"' onclick=onShangquan('shangquan"+shangquan.re_id+"')>"+shangquan.region_name+"</a>";
				            
				            
				            		
						}
			}
			
			$("#shangquan_list").html(shangquantxt);
			queryList();
		}
		
		function onShangquan (id){
			$("#"+shangquanid).attr("class","");
			shangquanid = id;
			$("#"+shangquanid).attr("class","cur");
			queryList();
		}
		
		
		function queryList(){
			if(d!=null){
					var url = "<%=basePath%>IndexAction!clickTopValue.do?order=<%=order%>";
					
					if(type!="type_all"){
						url +="&type="+type;
					}
					
					if(areaid!="area_all"){
						url +="&areaid="+areaid;
					}
					
					if(shangquanid!="shangquan_all"){
						url +="&shangquanid="+shangquanid;
					}
					
					$("#frame_dept").attr("src",url);
			
			}
		}
	

</script>
</body>
</html>
