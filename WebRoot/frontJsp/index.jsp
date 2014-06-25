<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>首页</title>
<jsp:include page="/frontJsp/js_inc.jsp"></jsp:include>
</head>
<body>
<div class="container">
  <!-- header start-->
  <dir class="header">
  		<jsp:include page="/frontJsp/header.jsp"/>
  </dir>
	
  <!-- main start -->
  <div class="mainWrap clearfix">
    <div class="mainLf">
     <div class="banner_con">
     	
     	  <div class="img_con">
     	  	
     	  	 <s:iterator value="nList">
     	  	 	 <a href="<%=basePath+"IndexAction!DeptDetail.do?id="%><s:property value="id"/>">
	                <img alt="" src="<%=basePath %><s:property value="url"/>" />
	            </a>
     	  	 </s:iterator>
           
        </div>
        <div class="page_con"></div>
     </div>
     <div class="mainSort">
       <div class="sidebar">
         <div class="sideTitle">
           <span class="ttIco">机构分类</span>
           <a class="more" href="<%=basePath%>IndexAction!forward.do?_value=dept_list&select_type=课程&select_value=">more</a>
         </div>
         <div class="sideMenu">
           <ul class="menu" id="menu_depttype">
           </ul>
         </div>
       </div>
       <div class="sortList">
         <div class="search">
	         <form action="<%=basePath%>IndexAction!forward.do" id="form_search">
			           <div class="selectWrap" onmouseover="hiddenOrShow(this,selectBox),changeStyle(this,'hover');">
			             <div class="trigger">
			               <span id="select_txt">课程</span>
			               <a class="btnTrigger" href="#" ></a> 
			             </div>
			             <div class="selectBox" style="display:none" id="selectBox" onmouseover="hiddenOrShow(this,selectBox),changeStyle(this,'hover');"  onmouseout="hiddenOrShow(this,selectBox),changeStyle(this,'hover');">
			               <ul class="listUl">
			                 <li><a title="机构" href="#" onclick="setSelected('机构');">机构</a></li>
			               </ul>
			             </div>
			           </div>
			           <div class="searchText">
			            <input id="_value" name="_value" type="hidden" value="dept_list"/>
			           <input id="select_type" name="select_type" type="hidden" value="课程"/>
			           <input type="text" name="select_value" value="" /></div>
			           <div class="searchBtn"><a href="#" onclick="onSearch();"><img src="<%=basePath%>frontJsp/images/btn/bt_search.png" /></a></div>
            </form>
         </div>
         <div class="listCon" id="course_list">
         </div>
       </div>
     </div>
    </div>
    
    
    <div class="mainRt">
      <div class="sidebar news">
         <jsp:include page="/frontJsp/top_news.jsp" />
      </div>
      <div class="discount_wrap" id="yh_news">
        <s:iterator value="nList">
        	<s:if test="!yh.name.isEmpty()">
                    <div class="discount_item">
			            <img alt="" src="<%=basePath%><s:property value="url" escape="false"/>" width="70px;" height="60px;"/>
			            <div class="title"><s:property value="yh.name"/></div>
			            <div class="info"><s:property value="yh.content"/></div>
			        </div>
            </s:if>
     	 </s:iterator>
      </div>
      <div class="advantage_wrap">
        <div class="advantage_title">选择优势</div>
        <div class="advantage_item">
            <img alt="" src="<%=basePath%>frontJsp/images/advantage_icon1.png" />
            <div class="title">资源丰富</div>
        </div>
        <div class="advantage_item">
            <img alt="" src="<%=basePath%>frontJsp/images/advantage_icon2.png" />
            <div class="title">审核严格</div>
        </div>
        <div class="advantage_item">
            <img alt="" src="<%=basePath%>frontJsp/images/advantage_icon3.png" />
            <div class="title">私人顾问</div>
        </div>
      </div>
    </div>
    <div class="share">
    <div class="share_title">分享</div>
      <div class="share_to">
         
			<jsp:include page="./share.jsp" />
      </div>
      <div class="gotop"><a href="#"></a></div>
    
    </div>
  </div>
  <!-- main end -->
  <!-- footer start -->
  <div class="footer">
   	<jsp:include page="/frontJsp/footer.jsp"/>
  </div>
  <!-- footer end -->
</div>

<script>


	function onSearch(){
	
		var txt = $("#select_value").val();
		
		if(txt==""){
			alert("请输入查询内容");
			return ;
		}
	
	
		 $('#form_search').submit(); // 从表单中获取数据
	
	}

	function setSelected (txt){
		$("#select_txt").html(txt);
		$("#select_type").val(txt);
	}
		
		$(function() {
            			 $.ajax({
								type : "POST", // 设置请求类型为"POST"，默认为"GET"
								url : '<%=basePath%>IndexAction!getDeptType.do',
								cache : false,
								asyn : false,
								datatype : "json",
								success : function(data) {
									var d = $.parseJSON(data);
									
									var txt = "";
									
									for(var j=0;j<d.length;j++){
										var type = d[j];						   
										 txt+="  <li id='"+type.id+"' onmouseover=hiddenOrShow(this,subitem"+type.id+"),changeStyle(this,'hover');  onmouseout=hiddenOrShow(this,subitem"+type.id+"),changeStyle(this,'hover');>";
								             txt+="  <div class='menuBox' >";
								            		 txt+="  <span><a href=''>"+type.typename+"  >></a></span>";
										             txt+="<p>";
										             	for(var x=0;x<type.lsDeptType.length;x++){
										             		if(x>2){
										             			break;
										             		}
										             		var url = "<%=basePath%>IndexAction!forward.do?_value=dept_list&select_type=机构&select_value="+type.lsDeptType[x].typename;
										             		txt+=" <a href='"+url+"'>"+type.lsDeptType[x].typename+"</a>";
										             	}
										             txt+="</p>";
								             txt+="  </div>";
							             
								             txt+="  <div class='subitem' id='subitem"+type.id+"'>";
						             			txt +="<div class='fore'>";
						             		
						             			for(var x=3;x<type.lsDeptType.length;x++){
								             		var url = "<%=basePath%>IndexAction!forward.do?_value=dept_list&select_type=机构&select_value="+type.lsDeptType[x].typename;
								             		txt+=" |<a href='"+url+"'>"+type.lsDeptType[x].typename+"</a>";
								             	}
						             		
												txt +=" </div>";
								           	 txt+="  </div>";
								           	 
							             txt+="  </li>	";
										
									}

									$("#menu_depttype").html(txt);
									
								}
							});
							
							 $.ajax({
								type : "POST", // 设置请求类型为"POST"，默认为"GET"
								url : '<%=basePath%>IndexAction!getCourse.do',
								cache : false,
								asyn : false,
								datatype : "json",
								success : function(data) {
									var map = $.parseJSON(data);
									
									var txt = "";
									
									for(var key in map){   
										
										var lsCourse = map[key];
										
										txt += "         <div class='listLine'>";
									    txt += "         <div class='listTitle'>";
									    txt += "            <h3>"+key+"</h3>";
									    txt += "           <a class='more' href='<%=basePath%>IndexAction!forward.do?_value=dept_list&select_type=课程&select_value="+key+"'>more</a>";
									    txt += "         </div>";
									    txt += "         <div class='listWrap'>";
									    for(var i=0;i<lsCourse.length;i++){
									    
									    	var course = lsCourse[i];
									    	var url = '<%=basePath %>'+course.url;
										    txt += "           <div class='picShow'>";
										    txt += "             <p class='pic'>";
										    txt += "               <img src='"+url+"' height='120px;' />";
										    txt += "               <a href='<%=basePath%>IndexAction!forward.do?_value=dept_list&select_type=课程&select_value="+key+"'>"+course.cname+"</a>";
										    txt += "            </p>";
										    if(i==0){
										      txt += "             <span class='hot01'>No.1</span>";
										    }
										  
										    txt += "           </div>";
									    
									    }
									    
									    	txt += "       </div>";
									    txt += "       </div>";
										
									}

						$("#course_list").html(txt);

					}
				});
				
							
			});				
				
	

</script>
</body>
</html>
