<%@page import="com.pxjg.module.region.entity.Region"%>
<%@page import="com.pxjg.module.user.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Region region = (Region)session.getAttribute("CURRENT_CITY");
User user = (User)session.getAttribute("userBean");

if(region == null){
	region = new Region();
	region.setRegion_name("北京");
}

String _value = request.getParameter("_value");

%>
<jsp:include page="/frontJsp/js_inc.jsp"></jsp:include>
<script type="text/javascript">

	function changeCity(city){
		var to_url = '<%=basePath%>IndexAction!replaceCity.do?city='+city;
							$.ajax({
								type : "POST", // 设置请求类型为"POST"，默认为"GET"
								url : to_url,
								cache : false,
								asyn : false,
								datatype : "json",
								success : function(data) {
									var d = $.parseJSON(data);
									
									if(d.success){
										location.reload() ;
									}
									
								}
							});
		
	}

	
	$(function() {
	
		var cur = "<%=_value%>";
		if(cur==null){
			$("#li_index").attr("class","current");
		}else if(cur == "map"){
			$("#li_map").attr("class","current");
		}else if(cur == "area"){
			$("#li_area").attr("class","current");
		}else if(cur == "map"){
			$("#li_course").attr("class","current");
		}else{
			$("#li_index").attr("class","current");
		}
			
						$.ajax({
								type : "POST", // 设置请求类型为"POST"，默认为"GET"
								url : '<%=basePath%>/IndexAction!getCity.do',
								cache : false,
								asyn : false,
								datatype : "json",
								success : function(data) {
									var d = $.parseJSON(data);
									
									var txt = "";
									txt += "<img alt='' src='<%=basePath%>frontJsp/images/img/tine_img.png' />";
									txt += "<ul>";
									for(var j=0;j<d.length;j++){
											txt += "<li>";
											txt+="<a onclick=changeCity('"+d[j].region_name+"');>"+d[j].region_name+"</a>";
											txt+=" </li>";
									}
									txt+=" </ul>";
									$("#cityList").html(txt);
									
								}
							});

	});
	
	
	function headFroward(str){
		location.href="<%=basePath%>/IndexAction!forward.do?_value="+str;
	}

	</script>

    <div class="oper">
    	<div class="oper_con">
         
         <%
         	if(user!=null){
         %>
         <span style="position:relative">
         	 <a href="frontUserAction!mySpace.do">用户中心</a>
         	 &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
         	 <a href="frontUserAction!logout.do">注销</a>
          </span>
         <%
         	}else{
         %>
         <span style="position:relative">
          <a  onclick="hiddenOrShow(this, 'siblings');">登陆</a>
            <div class="login_wrap" style="display:none" onmouseout="hiddenOrShow(this, this);">
            	<form  action="frontUserAction!login.do" method="post" id="form1">
                <input class="username" type="text" name="user.user_name" value="" />
                <input class="password" type="password" name="user.password" value="" />
                <input class="btn" type="submit" name="login" value="登录" />
                </form>
                <a id="forgot" href="#">忘记密码</a>
            </div>
           </span> 
            <span>
          &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
          <a href="frontUserAction!register.do">注册</a>
        </span>
             <%
         	}
         %>
       
       
        </div>
    </div>
    <div class="navBg">
      <div class="navCon">
        <h1 class="logo"><img alt="" src="<%=basePath%>frontJsp/images/logo/logo.gif" /></h1>
        <dl class="site">
          <dt>
            <span><%=region.getRegion_name() %></span>
            <a id="qiehuan" onclick="hiddenOrShow(this,cityList);">切换<em></em></a>
          </dt>
          <dd style="display:none" id="cityList" onmouseout="hiddenOrShow(this, this);">
          </dd>
        </dl>
        <ul class="nav">
          <li id="li_index"><a onclick="headFroward('index')"  title="首页">首页</a></li>
          <li id="li_area"><a href="http://www.weiquanbaike.com/wsq/forum.php" title="社区">社区</a></li>
          <li id="li_map"><a onclick="headFroward('map')" title="地图选课">地图选课</a></li>
          <li id="li_course"><a onclick="headFroward('course')" title="首页">帮选课</a></li>
        </ul>
      </div>
    </div>