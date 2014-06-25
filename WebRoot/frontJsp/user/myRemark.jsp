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
<title>个人中心</title>
<link type="text/css" href="<%=basePath%>frontJsp/css/common.css" rel="stylesheet" />
<link type="text/css" href="<%=basePath%>frontJsp/css/MySpace.css" rel="stylesheet" /> 
<!--<link type="text/css" href="css/index.css" rel="stylesheet" /> -->
<script type="text/javascript" src="<%=basePath%>frontJsp/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=basePath%>frontJsp/js/jquery-ui-1.10.0.custom.js"></script>
<script type="text/javascript" src="<%=basePath%>frontJsp/js/jquery.cycle.all.js"></script>
<script type="text/javascript" src="<%=basePath%>frontJsp/js/index.js"></script>
<script type="text/javascript">
function deleteRemark(id) {
		if(confirm("确定删除吗？")){
		$.ajax({
      	type: "POST",
     	url: "remarkAction!deleteRemark.do?id="+id,
      	success: function(msg){
      		 if(msg==0){
       		 	alert( "操作失败" );
       		 }else if(msg==1) {
       		 	alert( "操作成功" );
       		 	location.reload() ;
       		 }
     		}
  		});
  		}
	}
	function updateRemark(id) {
		var url = "<%=path%>/remarkAction!goUpdateMyRemark.do?id="+id;
	    window.location.href = url;
	}
</script>
</head>
<jsp:include page="/frontJsp/js_inc.jsp"></jsp:include>
<body>
<div class="container">
  <!-- header start-->
  <div class="header">
    	   <jsp:include page="/frontJsp/header.jsp" />
  </div>
  <!-- header end -->
  <!-- main start -->
  <div class="mainWrap clearfix">
    <div class="mainLf">
     <div class="mainSort">
       <div class="sidebar">
         <div class="sideTitle">
           <span class="userIco">个人中心</span>
         </div>
         <div class="sideMenu border">
           <ul class="menulist">
             <li>
               <div class="menutitle"><span>个人信息  >></span></div>
               <div class="subnav">
                	<span><a href="frontUserAction!mySpace.do">基本信息</a></span>	
                    <span><a href="frontUserAction!updatePassword.do">账号安全</a></span>	            
               </div>
             </li> 
             <li>
               <div class="menutitle"><span class="house">我的收藏  >></span></div>
               <div class="subnav">
                	<span><a href="collectionAction!ucList.do?type=机构">机构</a></span>	
                    <span><a href="collectionAction!ucList.do?type=课程">课程</a></span>	        
               </div>
             </li> 
             <li>
               <div class="menutitle"><span class="house"><a href="remarkAction!myRemark.do">我的评论  >></a></span></div>
             </li>  
            <!--  <li>
               <div class="menutitle"><span class="class"><a href="">我的课程  >></a></span></div>
               <div class="subnav hide">
                	         
               </div>
             </li>  
              -->           
           </ul>
         </div>
       </div>
      <div class="main_cen">
      <span>机构收藏</span>
      	 <div class="AllNews">
        <div class="newsWrap">
          <ul class="newsList">
          
          	     <s:iterator value="remarkList">
	      
			       <li>
			       		 <span>机构：<s:property value="dept.getDeptname()"/></span>
			       		 <s:if test="course!=null">
			       		 <span>课程：<s:property value="course.getCname()"/></span>
			       		 </s:if>
			       		 <br />
			       		 <span>我的评论:<s:property value="content"/></span> <br />
			       		  <s:if test="childRemark!=null">
			       		  	<span>收到的回复：<s:property value="childRemark.user.getUser_name()"/></span><br />
			       		  	<span>内容：<s:property value="childRemark.content"/></span><br />
			       		  </s:if>
		                 <s:if test="childRemark==null">
		                 	<span><a href="#" onclick="updateRemark('<s:property value="id"/>')">修改评论</a></span>&nbsp;&nbsp;&nbsp;&nbsp;
		                 	<span><a href="#" onclick="deleteRemark('<s:property value="id"/>')">删除评论</a></span>
		                 </s:if>
		                 
		            </li>
								
				</s:iterator>
          
          </ul>
        </div>
      </div>
      <div class="page_wrap">
        <jsp:include page="/frontJsp/page.jsp">
			<jsp:param name="url" value="<%=basePath+"collectionAction!ucList.do"%>"/>
		</jsp:include>
      </div>
      </div></div>
    </div>
    <div class="mainRt">
      
     </div>
    
  </div>
  <!-- main end -->
  <!-- footer start -->
   <div class="footer">
   <jsp:include page="/frontJsp/footer.jsp" />
  </div>
  <!-- footer end -->
</div>

</body>

</html>
