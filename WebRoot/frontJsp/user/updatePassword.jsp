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
<script type="text/javascript" src="<%=path%>/js/time/WdatePicker.js"> </script>
<script type="text/javascript">
function saveUser() {
		var password1 = document.getElementById("password1").value;
		var password2 = document.getElementById("password2").value;
		var oldpassword = document.getElementById("oldpassword").value;
		if(oldpassword==""){
			alert("请输入旧密码");
			return false;
		}
	    if(oldpassword!=${user.password}){
	    	alert("旧密码不正确");
			return false;
	    }
		if(password1==""){
			alert("请输入密码");
			return false;
		}
		if(password2==""){
			alert("请输入确认密码");
			return false;
		}
		if(password1!=""&&password2!=""&&password1!=password2){
			alert("两次密码输入不相同");
			return false;
		}
		userForm.submit();
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
      	<h2>修改密码</h2>
        <form action="frontUserAction!savePassword.do" method="post" id="userForm" name="userForm" onsubmit="return false;">
        	<input type="hidden" name="user_id" value="${user.user_id}"/>
         <ul>
            <li>
                <div class="column">
                旧密码：
               	 <input type="password" id="oldpassword"  class="width150"/>
                </div>
            </li>
            <li>
                <div class="column">
                新密码：
               	 <input type="password" name="password" id="password1" class="width150"/>
                </div>
            </li>
            <li>
                <div class="column">
                确认密码：
               	 <input type="password" id="password2" class="width150"/>
                </div>
            </li>

         </ul>
         <div class="btn_submit" >
        
         <span class="save" onclick="saveUser()">保存</span> 
         </div>
        </form>
      </div></div>
    </div>
    <div class="mainRt">
      
     </div>
    <div class="share">
      <div class="share_title">分享</div>
      <div class="share_to">
          <a class="wechat" href="#">wechat</a>
          <a class="sinaweibo" href="#">sinaweibo</a>
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

</body>

</html>
