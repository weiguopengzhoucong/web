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
	var myid=${depttype.id};
		if(myid==0||myid==null||myid==""){
			alert('请选择一个子组织');
			i=0;
		}else{
			window.location.href= url;
		}
	}
	function addDep(){
		if(document.getElementById("newdepttype.typename").value==null||document.getElementById("newdepttype.typename").value==""){
		
		alert('请输入类型名称');
		}else{
		addForm.submit();
		}
		
	}
function updateDep(){
		if(document.getElementById("newdepttype.typename").value==null||document.getElementById("newdepttype.typename").value==""){
		
		alert('请输入类型名称');
		}else{
		updateForm.submit();
		}
		
	}
$(document).ready(function() {
	var myid=${depttype.id};
	
	$('.ask-plain').click(function(e) {

		e.preventDefault();
		thisHref = $(this).attr('href');

		if (confirm('确认要删除吗？')) {
		if(myid==0||myid==null||myid==""){
		alert('请选择一个子类型');
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
			<div class="dtree" style="width: 20%;margin-top: 30px;margin-left: 20px;">

				<p>
					<a href="javascript: d.openAll();">展开子节点</a> | <a
						href="javascript: d.closeAll();">收起子节点</a> | <a style="cursor: pointer;" onclick="forwardUrl('<%=request.getContextPath()%>/deptTypeAction!addDeptTypeById.do?depttype.id=${depttype.id}&flag=1')" >新建</a> | 
						<a style="cursor: pointer;" onclick="getFor('<%=request.getContextPath()%>/deptTypeAction!queryDeptTypeById.do?depttype.id=${depttype.id}&flag=2')" >修改</a> | 
						<a href="deptTypeAction!deleteDeptTypeById.do?depttype.id=${depttype.id}&flag=0"
								class="ask-plain" style="cursor: pointer;">删除</a>
				</p>

				<script type="text/javascript">
		d = new dTree('d');
		d.add(0,-1,'组织结构管理','deptTypeAction!queryDeptTypeById.do?depttype.id=0&flag=0');
		<s:iterator value="depttypeListTree">
	      d.add(<s:property value="id" />,<s:property value="parentid" />,'<s:property value="typename" />','deptTypeAction!queryDeptTypeById.do?depttype.id=<s:property value="id" />&flag=0');
	    </s:iterator>
		document.write(d);
	</script>

			</div>
			<s:if test="flag!=0">
			
			<div style="width:80%;margin-top: 30px;margin-left: 20px;">
				<div class="user_list">
					<div class="user_info">
						<div class="title">
						<s:if test="depttype.id==0||newdepttype.parent_id==0">
						上级组织：根组织（已经是最高组织级别）
						</s:if><s:else>
						上级组织：${olddepttype.typename}						
						</s:else>					
						
						</div>
						<div style="height: 360px;" class="adduser">
							<div class="add_th" style="height: 350px;text-align: center;"></div>


							<div style="height: 350px;" class="add_tb">
								
								<s:if test="newdepttype==null">
								<label><span></span> <h2
											style="width: 100px;">新建组织</h2> </label>
									<form action="deptTypeAction!saveDeptType.do" method="post" id="addForm" name="addForm"
										>
										<input type="hidden" name="depttype.id"
											id="depttype.id" value="${depttype.id}" /> 
											<input type="hidden" name="flag"
											id="flag" value="0" /> 
										<input type="hidden" name="newdepttype.parentid"
											id="newdepttype.parentid" value="${depttype.id}" />
											<label class="bt">
                							<span>类型名称：</span>
                   							 <input type="text" class="add_text" name="newdepttype.typename"
											id="newdepttype.typename" />
               							    </label>
           								    <label class="zw">
                							<span>备注：</span>
                  							<textarea style="height: 100px;"  name="newdepttype.content" id="newdepttype.content" cols="30" rows="20">请输入内容...</textarea>
             								</label>
											
									</form>
									<span></span>
									<input type="image" src="<%=path%>/images/save_btn.jpg"
										onclick="addDep();" style="margin-top: 100px;" />
								</s:if>
								<s:else>
								<label><span></span> <h2
											style="width: 100px;">修改组织</h2> </label>
									<form action="deptTypeAction!updateDeptType.do" method="post" id="updateForm" name="updateForm"
										>
										<input type="hidden" name="flag"
											id="flag" value="1" /> 
										<input type="hidden" name="depttype.id"
											id="depttype.id" value="${depttype.id}" /> 
										<input type="hidden" name="newdepttype.id"
											id="newdepttype.id" value="${depttype.id}" /> 
											<label class="bt">
                							<span>组织名称：</span>
                   							 <input type="text" class="add_text" name="newdepttype.typename"
											id="newdepttype.typename" value="${newdepttype.typename}" />
               							    </label>
           								    <label class="zw">
                							<span>备注：</span>
                  							<textarea style="height: 100px;width: 673px;"  name="newdepttype.content" id="newdepttype.content" cols="30" rows="20"cols="30" rows="20">${newdepttype.content}</textarea>
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
