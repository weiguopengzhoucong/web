<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>



		<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

body {
	color: #666;
	font: 12px/ 1.5 Arial;
}

/* star */
#star {
	position: relative;
	width: 600px;
	margin: 20px auto;
	height: 24px;
}

#star ul,#star span {
	float: left;
	display: inline;
	height: 19px;
	line-height: 19px;
}

#star ul {
	margin: 0 10px;
}

#star li {
	float: left;
	width: 24px;
	cursor: pointer;
	text-indent: -9999px;
	background: url(<%=path%>/frontJsp/images/star.png) no-repeat;
}

#star strong {
	color: #f60;
	padding-left: 10px;
}

#star li.on {
	background-position: 0 -28px;
}

#star p {
	position: absolute;
	top: 20px;
	width: 159px;
	height: 60px;
	display: none;
	background: url(<%=path%>/frontJsp/images/icon.gif) no-repeat;
	padding: 7px 10px 0;
}

#star p em {
	color: #f60;
	display: block;
	font-style: normal;
}
</style>
<script type="text/javascript" src="<%=basePath %>frontJsp/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
function subForm1() {
		var score = document.getElementById("score").value;
		var content = document.getElementById("content").value;
		if(score==0){
			alert("请打分");
			return false;
		}
		if(content==""){
			alert("请输入评论内容");
			return false;
		}
		$.ajax({
      	type: "POST",
     	url: "remarkAction!updateRemark.do",
      	data: $('#userForm').serialize(),
      	success: function(msg){
      		 if(msg==0){
       		 	alert( "评论失败" );
       		 }else if(msg==1) {
       		 	alert( "评论成功" );
       		 	var url = <%=path%>/remarkAction!myRemark.do;
        	    window.location.href = url;
       		 }
     		}
  		});
	}
</script>
		<script type="text/javascript"> 
window.onload = function (){

	var oStar = document.getElementById("star");
	var aLi = oStar.getElementsByTagName("li");
	var oUl = oStar.getElementsByTagName("ul")[0];
	var oSpan = oStar.getElementsByTagName("span")[1];
	var oP = oStar.getElementsByTagName("p")[0];
	var score = document.getElementById("score");
	var i = iScore = iStar = 0;
	var aMsg = [
				" |很不满意",
				" |不满意",
				" |一般",
				" |满意",
				" |非常满意"
				]
	
	for (i = 1; i <= aLi.length; i++){
		aLi[i - 1].index = i;
		
		//鼠标移过显示分数
		aLi[i - 1].onmouseover = function (){
			fnPoint(this.index);
			//浮动层显示
			oP.style.display = "block";
			//计算浮动层位置
			oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 104 + "px";
			//匹配浮动层文字内容
			oP.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1]
		};
		
		//鼠标离开后恢复上次评分
		aLi[i - 1].onmouseout = function (){
			fnPoint();
			//关闭浮动层
			oP.style.display = "none"
		};
		
		//点击后进行评分处理
		aLi[i - 1].onclick = function (){
			iStar = this.index;
			oP.style.display = "none";
			oSpan.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")"
			score.value=this.index;
		}
	}
	
	//评分处理
	function fnPoint(iArg){
		//分数赋值
		iScore = iArg || iStar;
		for (i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on" : "";	
	}
	
};
</script>
	</head>

	<body>
		  <form action="#" method="post" id="userForm" name="userForm" onsubmit="return false;">
		<div id="star">
			<span>打分:</span>
			<ul>
				<li>
					<a href="javascript:;">1</a>
				</li>
				<li>
					<a href="javascript:;">2</a>
				</li>
				<li>
					<a href="javascript:;">3</a>
				</li>
				<li>
					<a href="javascript:;">4</a>
				</li>
				<li>
					<a href="javascript:;">5</a>
				</li>
			</ul>
			
			<span>
			</span>
			<p>
				
			</p>
			
		</div>
		<br /><br />
		<div id="star">
			<span>评论:</span>
			<textarea rows="5"  cols="75" name="remark.content" id="content"> 
			  ${remark.content}
			</textarea>
			
		</div>
		<br /><br /><br /><br />
		<div id="star">
			<input type="button" value="提交点评" onclick="subForm1()"/>
			&nbsp;
			<input type="button" value="取消" onclick="history.go(-1)"/>
		</div>
		<input type="hidden" name="score" id="score" value="${remark.score}"/>
		<input type="hidden" name="remark.id" id="score" value="${remark.id}"/>
		</form>
	</body>
</html>
