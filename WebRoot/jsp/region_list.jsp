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
		<title>组织机构管理</title>
		<link rel="stylesheet" href="<%=path%>/css/base.css" type="text/css" />
		<link rel="stylesheet" href="<%=path%>/css/layout.css" type="text/css" />
		<link rel="stylesheet" href="<%=path%>/css/main.css" type="text/css" />
		<link rel="StyleSheet" href="<%=path%>/js/dtree/dtree.css"
			type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path%>/js/dtree/dtree.js"></script>
		<script type="text/javascript">    
	function forwardUrl(url) {
		window.location.href = url;
	}
	function getFor(url) {
	var myid=${region.re_id};
		if(myid==0||myid==null||myid==""){
			alert('请选择一个子组织');
		}else{
			window.location.href= url;
		}
		
			
	}
	
	function getFor1(url) {
		var myid=${region.re_id};
		var region_type = document.getElementById('newregion.region_type').value;
		var i = 1;
		if(myid==0||myid==null||myid==""){
			alert('请选择一个子组织');
			i=0;
		}
		if(region_type!=3){
			alert('请选择商圈区域');
			i=0;
		}
		if(i==1){
			window.location.href= url;
		}
	}
	function addDep(){
		if(document.getElementById("newregion.region_name").value==null||document.getElementById("newregion.region_name").value==""){
		
		alert('请输入组织名称');
		}else{
		addForm.submit();
		}
	}
	
	function updateDep(){
		if(document.getElementById("newregion.region_name").value==null||document.getElementById("newregion.region_name").value==""){
		
		alert('请输入组织名称');
		}else{
		updateForm.submit();
		}
		
	}
$(document).ready(function() {
	var myid=${region.re_id};
	
	$('.ask-plain').click(function(e) {

		e.preventDefault();
		thisHref = $(this).attr('href');

		if (confirm('确认要删除吗？')) {
		if(myid==0||myid==null||myid==""){
		alert('请选择一个子组织');
		}else{
		window.location = thisHref;
		}
			
		}

	});

});
</script>
	</head>

	<body>
		<div class="layout">



			<div class="main" id="main">
				<div class="dtree"
					style="width: 20%; margin-top: 30px; margin-left: 20px;">

					<p>
						<a href="javascript: d.openAll();">展开子节点</a> |
						<a href="javascript: d.closeAll();">收起子节点</a> |
						<a style="cursor: pointer;"
							onclick="forwardUrl('<%=request.getContextPath()%>/regionAction!addRegionById.do?region.re_id=${region.re_id}&flag=1')">新建</a>
						|
						<a style="cursor: pointer;"
							onclick="getFor('<%=request.getContextPath()%>/regionAction!queryRegionById.do?region.re_id=${region.re_id}&flag=2')">修改</a>
						|
						<a
							href="regionAction!deleteRegionById.do?region.re_id=${region.re_id}&flag=0"
							class="ask-plain" style="cursor: pointer;">删除</a>|
						<a style="cursor: pointer;"
							onclick="getFor1('<%=request.getContextPath()%>/deptAction!queryDeptByShangQuan.do?re_id=${region.re_id}')">机构管理</a>
					</p>

					<script type="text/javascript">
		d = new dTree('d');
		d.add(0,-1,'组织结构管理','regionAction!queryRegionById.do?region.re_id=0&flag=0');
		<s:iterator value="regionListTree">
	      d.add(<s:property value="re_id" />,<s:property value="parent_id" />,'<s:property value="region_name" />','regionAction!queryRegionById.do?region.re_id=<s:property value="re_id" />&flag=0');
	    </s:iterator>
		document.write(d);
	</script>
<input type="hidden" name="newregion.region_type" id="newregion.region_type"
	value="${newregion.region_type}" />
				</div>
				<s:if test="flag!=0">

					<div style="width: 80%; margin-top: 30px; margin-left: 20px;">
						<div class="user_list">
							<div class="user_info">
								<div class="title">
									<s:if test="region.re_id==0||newregion.parent_id==0">
						上级组织：根组织（已经是最高组织级别）
						</s:if>
									<s:else>
						上级组织：${oldregion.region_name}						
						</s:else>

								</div>
								<div style="height: 360px;" class="adduser">
									<div class="add_th" style="height: 350px; text-align: center;"></div>


									<div style="height: 350px;" class="add_tb">

										<s:if test="newregion==null">
											<label>
												<span></span>
												<h2 style="width: 100px;">
													新建区域
												</h2>
											</label>
											<form action="regionAction!saveRegion.do" method="post"
												id="addForm" name="addForm">
												<input type="hidden" name="region.re_id" id="region.re_id"
													value="${region.re_id}" />
													
												<input type="hidden" name="flag" id="flag" value="0" />
												<input type="hidden" name="newregion.parent_id"
													id="newregion.parent_id" value="${region.re_id}" />
												<label class="bt">
													<span>区域名称：</span>
													<input type="text" class="add_text"
														name="newregion.region_name" id="newregion.region_name" />
												</label>
												<label class="bt">
													<span>经度：</span>
													<input type="text" class="add_text"
														name="newregion.remark2" id="newregion.remark2" />
												</label>
												<label class="bt">
													<span>纬度：</span>
													<input type="text" class="add_text"
														name="newregion.remark3" id="newregion.remark3" />
												</label>
												<label class="zw">
													<span>备注：</span>
													<textarea style="height: 100px;" name="newregion.remark1"
														id="newregion.remark1" cols="30" rows="20">请输入内容...</textarea>
												</label>
											</form>
											<span></span>
											<input type="image" src="<%=path%>/images/save_btn.jpg"
												onclick="addDep();" style="margin-top: 100px;" />
										</s:if>
										<s:else>
											<label>
												<span></span>
												<h2 style="width: 100px;">
													修改组织
												</h2>
											</label>
											<form action="regionAction!updateRegion.do" method="post"
												id="updateForm" name="updateForm">
												<input type="hidden" name="flag" id="flag" value="1" />
												<input type="hidden" name="region.re_id" id="region.re_id"
													value="${region.re_id}" />
												<input type="hidden" name="newregion.re_id"
													id="newregion.re_id" value="${region.re_id}" />
												<label class="bt">
													<span>区域名称：</span>
													<input type="text" class="add_text"
														name="newregion.region_name" id="newregion.region_name"
														value="${newregion.region_name}" />
												</label>
												<label class="bt">
													<span>经度：</span>
													<input type="text" class="add_text"
														name="newregion.remark2" id="newregion.remark2"
														value="${newregion.remark2}" />
												</label>
												<label class="bt">
													<span>纬度：</span>
													<input type="text" class="add_text"
														name="newregion.remark3" id="newregion.remark3"
														value="${newregion.remark3}" />
												</label>
												<label class="zw">
													<span>备注：</span>
													<textarea style="height: 100px; width: 673px;"
														name="newregion.remark1" id="newregion.remark1" cols="30"
														rows="20" cols="30" rows="20">${newregion.remark1}</textarea>
												</label>
											</form>
											<span></span>
											<s:if test="flag==2">
												<input type="image" src="<%=path%>/images/save_btn.jpg"
													onclick="updateDep();" style="margin-top: 100px;" />
											</s:if>
										</s:else>
									</div>



								</div>
							</div>
						</div>
					</div>
				</s:if>
			</div>

		</div>

	</body>
</html>
