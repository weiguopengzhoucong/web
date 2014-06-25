<%@page import="com.pxjg.module.region.entity.Region"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Region region = (Region)session.getAttribute("CURRENT_CITY");
String id = request.getParameter("id");
if(region == null){
	region = new Region();
	region.setRegion_name("北京");
	region.setRemark2("116.416773");
	region.setRemark3("39.928216");
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<style type="text/css">
    html,body{margin:0;padding:0;}
    .iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
    .iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>

</head>

<body>
<div class="container">
  <!-- header start-->
  <div class="header">
   	<jsp:include page="/frontJsp/header.jsp"></jsp:include>
  </div>
 
 
   <!-- main start -->
  <div class="mainWrap clearfix">
    <div class="leftBox">
    	<div class="sidebar">
         <div id="dept_count" class="sideTitle fontsize"></div>
         <div class="sideMenu">
           <ul class="menu" id="dept_list">
            
           </ul>
         </div>
       </div>
    </div>
    <div class="rightBox">
    	  <div style="width:697px;height:550px;border:#ccc solid 1px;" id="dituContent"></div>
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
    <jsp:include page="/frontJsp/footer.jsp" />
  </div>
  <!-- footer end -->
</div>
</body>
<script type="text/javascript">
    //创建和初始化地图函数：
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
        addMarker();//向地图中添加marker
    }
    
    //创建地图函数：
    function createMap(){
        var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
        var point = new BMap.Point(<%=region.getRemark2() %>,<%=region.getRemark3() %>);//定义一个中心点坐标
        map.centerAndZoom(point,12);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
    }
    
    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }
    
    //地图控件添加函数：
    function addMapControl(){
        //向地图中添加缩放控件
	var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
	map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
	var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
	map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
	map.addControl(ctrl_sca);
    }
    
    //标注点数组

    //创建marker
    function addMarker(){
        for(var i=0;i<markerArr.length;i++){
            var json = markerArr[i];
            var p0 = json.point.split("|")[0];
            var p1 = json.point.split("|")[1];
            var point = new BMap.Point(p0,p1);
			var iconImg = createIcon(json.icon);
            var marker = new BMap.Marker(point,{icon:iconImg});
			var iw = createInfoWindow(i);
			var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
			marker.setLabel(label);
            map.addOverlay(marker);
            label.setStyle({
                        borderColor:"#808080",
                        color:"#333",
                        cursor:"pointer"
            });
			
			(function(){
				var index = i;
				var _iw = createInfoWindow(i);
				var _marker = marker;
				_marker.addEventListener("click",function(){
				    this.openInfoWindow(_iw);
			    });
			    _iw.addEventListener("open",function(){
				    _marker.getLabel().hide();
			    })
			    _iw.addEventListener("close",function(){
				    _marker.getLabel().show();
			    })
				label.addEventListener("click",function(){
				    _marker.openInfoWindow(_iw);
			    })
				if(!!json.isOpen){
					label.hide();
					_marker.openInfoWindow(_iw);
				}
			})()
        }
    }
    //创建InfoWindow
    function createInfoWindow(i){
        var json = markerArr[i];
        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
        return iw;
    }
    //创建一个Icon
    function createIcon(json){
        var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
        return icon;
    }
    
    
        var markerArr ;//= [{title:'我的标记',content:'我的备注',point:'116.351664|39.960963',isOpen:0,icon:{w:21,h:21,l:0,t:0,x:6,lb:5}}];
    	
    $(function() {
    	
    	var id = "<%=id%>";
    	if(id==null||id=="null"){
    		$.ajax({
					type : "POST", // 设置请求类型为"POST"，默认为"GET"
					url : '<%=basePath%>/IndexAction!getDeptByRegion.do',
					cache : false,
					asyn : false,
					datatype : "json",
					success : function(data) {
						var d = $.parseJSON(data);
						$("#dept_count").html("共有"+d.length+"条结果");
						
						var txt = "";
						var index = 1;
						markerArr = new Array(d.length);
						for(var j=0;j<d.length;j++){
							  var dept = d[j];
							   txt+=" <li>";
					           txt+="   <div class='menuBox clearfix'>";
					           txt+="      <span class='map'><a href=''>地点"+(index)+"  >></a></span>";
					           txt+="      <dl>";
					           txt+="      	<dt>地址：</dt>";
					           txt+="         <dd>"+dept.address+"</dd>";
					           txt+="         <dt>电话：</dt>";
					           txt+="         <dd>"+dept.tel+"</dd>";
					           txt+="      </dl>";
					           txt+="    </div>";
					           txt+="  </li>";
					           
					           markerArr[j] ={title:dept.deptname,content:("地址"+dept.address+"<br>电话"+dept.tel),point:dept.x+'|'+dept.y,isOpen:0,icon:{w:21,h:21,l:0,t:0,x:6,lb:5}};
							   index++;
						}
						
						$("#dept_list").html(txt);
						initMap();//创建和初始化地图
    
					}
				});
    	}else{
    	
    		$.ajax({
					type : "POST", // 设置请求类型为"POST"，默认为"GET"
					url : '<%=basePath%>/IndexAction!DeptById.do?id='+id,
					cache : false,
					asyn : false,
					datatype : "json",
					success : function(data) {
						var dept = $.parseJSON(data);
						$("#dept_count").html("共有1条结果");
						
							  var txt = "";
							  var index = 1;
							  markerArr = new Array(1);
							  
							   txt+=" <li>";
					           txt+="   <div class='menuBox clearfix'>";
					           txt+="      <span class='map'><a href=''>地点"+(1)+"  >></a></span>";
					           txt+="      <dl>";
					           txt+="      	<dt>地址：</dt>";
					           txt+="         <dd>"+dept.address+"</dd>";
					           txt+="         <dt>电话：</dt>";
					           txt+="         <dd>"+dept.tel+"</dd>";
					           txt+="      </dl>";
					           txt+="    </div>";
					           txt+="  </li>";
					           
					           markerArr[0] ={title:dept.deptname,content:("地址"+dept.address+"<br>电话"+dept.tel),point:dept.x+'|'+dept.y,isOpen:0,icon:{w:21,h:21,l:0,t:0,x:6,lb:5}};
							  
						$("#dept_list").html(txt);
						initMap();//创建和初始化地图
    
					}
				});
    	
    	}
			
			

	});
    
    
</script>
</html>
