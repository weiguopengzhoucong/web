<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
*{margin:0;padding:0;}
/****订单弹出框*****/
.orderMsg{ position:fixed;_position: absolute;_top: expression(documentElement.scrollTop + 340 + "px"); background:#fff; border:1px solid #939395; width:400px; height:460px;top: 50%;left: 50%;margin: -200px 0 0 -250px; overflow:hidden; z-index:99999; font-size:14px; color:#000; display:none;}
.orderMsg dl{ overflow:hidden; padding:20px; margin:0;}
.orderMsg dl dt{ line-height:30px;}
.orderMsg dl dd{ line-height:2px; height:350px; padding-left:10px; margin:0;}
.orderMsg dl dd label{ line-height:2px; height:350px; padding-left:10px; margin:0;}
</style>
<title></title>
<script src="<%=basePath %>frontJsp/js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="<%=basePath %>frontJsp/js/qrcode.js" type="text/javascript"></script>
<script src="<%=basePath %>frontJsp/js/jquery.qrcode.js" type="text/javascript"></script>
<script type="text/javascript">
	
	function showerweima(){
	
		var text = location.href;
		$("#div_div").attr("style","display:true");
		$("#div_div").qrcode(utf16to8(text));
	}
	
	function utf16to8(str) { //转码
	var out, i, len, c;
	out = "";
	len = str.length;
	for (i = 0; i < len; i++) {
	c = str.charCodeAt(i);
	if ((c >= 0x0001) && (c <= 0x007F)) {
	out += str.charAt(i);
	} else if (c > 0x07FF) {
	out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
	out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
	out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
	} else {
	out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
	out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
	}
	}
	return out;
	}
	

</script>
</head>
<body>

		<a class="wechat"  id="bt"></a>
		<script type="text/javascript">
		document.write('<a class="sinaweibo" href="http://v.t.sina.com.cn/share/share.php?url='+encodeURIComponent(location.href)+'&title='+encodeURIComponent('{$title}')+'"&appkey=433903842&pic" class="t1" target="_blank"></a>');
		</script> 
		<a class="tencentweibo" href="javascript:void(0)" onclick="{ var _t = encodeURI(document.title);  var _url = encodeURI(window.location); var _appkey = '333cf198acc94876a684d043a6b48e14'; var _site = encodeURI; var _pic = ''; var _u = 'http://v.t.qq.com/share/share.php?title='+_t+'&url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic; window.open( _u,'转播到腾讯微博', 'width=700, height=580, top=180, left=320, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' );  };" ></a>
		
		
		<div id="orderMsg" class="orderMsg" style="display: none;">
			<dl>
			<dt>分享到微信朋友圈</dt>
			<dd>
				<div id="div_div" style="display: none;" style="width:400px;height:350px;border:1px solid #000;"></div>
				<label>打开微信，点击底部的“发现”，使用 “扫一扫”即可将网页分享到我的朋友圈。</label>
			</dd>
			</dl>
			
			
		</div>
		
		
		
</body>

<script type="text/javascript">
/*
openID=显示按钮,conID=需要显示的div,closeID=关闭按钮
解决了:
1.可以遮挡ie6下的select元素 但是在ie6下div没有透明度
2.弹出的div可以一直在浏览器屏幕中间显示
问题:
1.目前不支持.class 只支持#id
2.需要显示的div需要自己设置css
3.在ie6下需要设置css
例如div {_position: absolute;_top: expression(documentElement.scrollTop + 340 + "px"); }
4.ie6下背景div没有透明度 这里我上网搜到的结果都不能解决 如果您有方法请给我留言
*/
var _CalF = { //便捷方法
$ : function(id){return document.getElementById(id)},
create : function(id){return document.createElement(id)},
append : function(id){return document.body.appendChild(id)},
remove : function(id){return document.body.removeChild(id)}
}
function popup(openID,conID,closeID){
this.onclick(openID,conID,closeID);
}
popup.prototype = {
cssStyle : "width:100%;position:absolute;left:0;top:0;filter:alpha(opacity = 50);opacity:0.5;border:0;overflow:auto;",
createShadowDiv : function(){ //背景遮罩
var shadowDiv = _CalF.create("div");
shadowDiv.id = "shadowDiv";
shadowDiv.style.cssText = this.cssStyle;
shadowDiv.style.height = document.body.scrollHeight + "px";
shadowDiv.style.backgroundColor = "#000";
shadowDiv.style.zindex = 100;
_CalF.append(shadowDiv);
},
createIframe : function(){ //iframe
var iframe = _CalF.create("iframe");
iframe.id = "shadowIframe";
iframe.style.cssText = this.cssStyle;
iframe.style.height = document.body.scrollHeight + "px";
iframe.style.zindex = 99;
showerweima();
_CalF.append(iframe);
},
removeDiv : function(){
_CalF.remove(_CalF.$("shadowDiv"));
_CalF.remove(_CalF.$("shadowIframe"));
},
onclick : function(openID,conID,closeID){
var that = this;
_CalF.$(openID).onclick = function(){
if(window.ActiveXObject){ //ie6
if(!window.XMLHttpRequest){
document.body.style.cssText = "_background-image: url(about:blank);_background-attachment: fixed;";
}
}
that.createIframe();
that.createShadowDiv();
_CalF.$(conID).style.display = "block";
}
document.getElementById(closeID).onclick = function(){
_CalF.$(conID).style.display = "none";
that.removeDiv();
}

}
}
var bt = new popup("bt","orderMsg","close");
</script>
</html>
