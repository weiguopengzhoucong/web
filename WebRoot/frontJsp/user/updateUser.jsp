<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import = "com.pxjg.module.user.entity.User,java.util.List" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("userBean");
List<String> xzList = (List)request.getAttribute("xzList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心</title>
<link type="text/css" href="<%=basePath%>frontJsp/css/MySpace.css" rel="stylesheet" /> 
<!--<link type="text/css" href="css/index.css" rel="stylesheet" /> -->
<script type="text/javascript" src="<%=path%>/js/time/WdatePicker.js"> </script>
</head>
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
               <div class="menutitle"><span class="house"><a href="">我的收藏  >></a></span></div>
               <div class="subnav">
                	<span><a href="collectionAction!ucList.do?type=机构">机构</a></span>	
                    <span><a href="collectionAction!ucList.do?type=课程">课程</a></span>		        
               </div>
             </li>  
             <li>
               <div class="menutitle"><span class="house"><a href="remarkAction!myRemark.do">我的评论  >></a></span></div>
             </li>          
           </ul>
         </div>
       </div>
      <div class="main_cen">
      	<h2>基本信息</h2>
        <form action="frontUserAction!updateUser.do" method="post" id="userForm" name="userForm" >
        	<input type="hidden" name="user.user_id" value="${user.user_id}"/>
         <ul>
        	<li><div class="column">
        	体型：<select name="user.figure">
        		<s:if test="user.figure==0">
        			<option value="0" selected="selected">保密</option>
        			<option value="1">高</option>
        			<option value="2">矮</option>
        		</s:if>
        		<s:if test="user.figure==1">
        			<option value="0" >保密</option>
        			<option value="1" selected="selected">高</option>
        			<option value="2">矮</option>
        		</s:if>
        		<s:if test="user.figure==2">
        			<option value="0" >保密</option>
        			<option value="1">高</option>
        			<option value="2" selected="selected">矮</option>
        		</s:if>
        		<s:if test="user.figure==null">
        			<option value="0" >保密</option>
        			<option value="1">高</option>
        			<option value="2">矮</option>
        		</s:if>
        	</select>
        	</div></li>
            <li>
            	<div class="column">
            	个人状况：<s:if test="user.marriage==null">
        			<span><input type="radio" name="user.marriage" value="单身"/>单身</span>
                	<span><input type="radio" name="user.marriage" value="热恋"/>热恋</span>
                	<span><input type="radio" name="user.marriage" value="备婚"/>备婚</span>
                	<span><input type="radio" name="user.marriage" value="已婚"/>已婚</span>
                	<span><input type="radio" name="user.marriage" value="保密"/>保密</span>
        		</s:if>
        		<s:if test="user.marriage=='单身'">
        			<span><input type="radio" name="user.marriage" value="单身" checked="checked"/>单身</span>
                	<span><input type="radio" name="user.marriage" value="热恋"/>热恋</span>
                	<span><input type="radio" name="user.marriage" value="备婚"/>备婚</span>
                	<span><input type="radio" name="user.marriage" value="已婚"/>已婚</span>
                	<span><input type="radio" name="user.marriage" value="保密"/>保密</span>
        		</s:if>
        		<s:if test="user.marriage=='热恋'">
        			<span><input type="radio" name="user.marriage" value="单身"/>单身</span>
                	<span><input type="radio" name="user.marriage" value="热恋" checked="checked"/>热恋</span>
                	<span><input type="radio" name="user.marriage" value="备婚"/>备婚</span>
                	<span><input type="radio" name="user.marriage" value="已婚"/>已婚</span>
                	<span><input type="radio" name="user.marriage" value="保密"/>保密</span>
        		</s:if>
        		<s:if test="user.marriage=='备婚'">
        			<span><input type="radio" name="user.marriage" value="单身"/>单身</span>
                	<span><input type="radio" name="user.marriage" value="热恋"/>热恋</span>
                	<span><input type="radio" name="user.marriage" value="备婚" checked="checked"/>备婚</span>
                	<span><input type="radio" name="user.marriage" value="已婚"/>已婚</span>
                	<span><input type="radio" name="user.marriage" value="保密"/>保密</span>
        		</s:if>
        		<s:if test="user.marriage=='已婚'">
        			<span><input type="radio" name="user.marriage" value="单身"/>单身</span>
                	<span><input type="radio" name="user.marriage" value="热恋"/>热恋</span>
                	<span><input type="radio" name="user.marriage" value="备婚"/>备婚</span>
                	<span><input type="radio" name="user.marriage" value="已婚" checked="checked"/>已婚</span>
                	<span><input type="radio" name="user.marriage" value="保密"/>保密</span>
        		</s:if>
        		<s:if test="user.marriage=='保密'">
        			<span><input type="radio" name="user.marriage" value="单身"/>单身</span>
                	<span><input type="radio" name="user.marriage" value="热恋"/>热恋</span>
                	<span><input type="radio" name="user.marriage" value="备婚"/>备婚</span>
                	<span><input type="radio" name="user.marriage" value="已婚"/>已婚</span>
                	<span><input type="radio" name="user.marriage" value="保密" checked="checked"/>保密</span>
        		</s:if>
            		
            	</div>
            </li>
            <li>
                <div class="column">
               	 生日：<span>
               	 <input value="${birthday}"
						id="mydate" name="user.birthday" type="text" class="Wdate" style="width:150px;height:30px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></input> 
               	 </span>
                </div>
            </li>
            <li>
                <div class="column">
                星座：<select name="user.xingzuo">
                <%
                	for(int i=0;i<xzList.size();i++){
                		String xz = xzList.get(i);
                %>
                	<option value="<%=xz%>" 
                	<%
                		if(xz.equals(user.getXingzuo())){
                	%>
                		selected="selected"
                	<%		 
                		}
                	%>
                	><%=xz%></option>
                <%		
                	}
                %>
                </select>
                </div>
            </li>
            <li>
                <div class="column">
               	QQ： <span><input type="text" name="user.qq" value="${user.qq}" class="width150" /></span>
               	 <s:if test="user.qq_open==1">
               	 	<span><input type="radio" name="user.qq_open" value ="1" checked="checked"/>公开</span>
                 	<span><input type="radio" name="user.qq_open" value ="0" />保密</span>
               	 </s:if>
               	 <s:else>
               		 <span><input type="radio" name="user.qq_open" value ="1" />公开</span>
               		  <span><input type="radio" name="user.qq_open" value ="0"  checked="checked"/>保密</span>
               	 </s:else>
                </div>
            </li>
            <li>
              <div class="column">
               MSN： 
               	  <span><input type="text" name="user.msn" value="${user.msn}" class="width150" /></span>
               	 <s:if test="user.msn_open==1">
               	 	<span><input type="radio" name="user.msn_open" value ="1" checked="checked"/>公开</span>
                 	<span><input type="radio" name="user.msn_open"   value="0" />保密</span>
               	 </s:if>
               	 <s:else>
               		 <span><input type="radio" name="user.msn_open" value ="1" />公开</span>
               		  <span><input type="radio" name="user.msn_open" value="0"  checked="checked"/>保密</span>
               	 </s:else>
                </div>
            </li>
            <li>
                <div class="column">
                行业/职业：
               	 <input type="text" name="user.job" value="${user.job}" class="width150" />
                </div>
            </li>
            <li>
                <div class="column">
                大学：
               	 <input type="text" name="user.university" value="${user.university}" class="width150"/>
                </div>
            </li>
            <li>
                <div class="column">
                高中：
               	 <input type="text" name="user.highschool" value="${user.highschool }" class="width150"/>
                </div>
            </li>
            <li>
                <div class="column">
                个人域名：
               	 <input type="text" class="width150" name="user.domain_name" value=""/>
                </div>
            </li>
            <li>
                <div class="column">
                个人主页：
               	 <input type="text" class="width100" name="user.home_page"  value="${user.home_page}"/>
                </div>
            </li>
            <li>
                <div class="column">
               爱好：
               	 <textarea rows="" class="width100" name="user.hobby">
               	 ${user.hobby}
               	 </textarea>
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
<script type="text/javascript">
	function saveUser(){
      
		$.ajax({
      	type: "POST",
     	url: "frontUserAction!updateUser.do",
      	data: $('#userForm').serialize(),
      	success: function(msg){
      		 if(msg==0){
       		 	alert( "修改失败" );
       		 }else if(msg==1) {
       		 	alert( "修改成功" );
       		 }
     		}
  		});

	}
</script>
</body>

</html>
