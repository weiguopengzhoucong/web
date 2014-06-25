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
<title>优惠</title>
<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/layout.css" type="text/css" />
	<link href="<%=path%>/jsp/wxSchool/css/tip.css" rel="stylesheet" />
	<link rel="stylesheet" href="<%=path %>/css/main.css" type="text/css" />
	<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/js/time/WdatePicker.js"> </script>
</head>

<body>
<div class="layout">
    <div class="main H800" id="main">
    	<div class="user_list">
        	
        	<input type="hidden" id="deptorcourse" name="deptorcourse" value="${deptorcourse}"/>
        	<input type="hidden" id="deptorcourseid" name="deptorcourseid" value="${deptorcourseid}"/>
        	<div class="table_list W961">
              <div class="tt">
              <form action="remarkAction!remarkList.do" method="post">
              <input type="hidden" name="type" value="${type }"/> 
              <input id="mydate" name="dateSearch" type="text" class="Wdate" style="width:150px;height:30px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></input>
              <input type="submit" value="查询"/> 
                </form>
              查询结果如下
              </div>
              <table cellpadding="0" cellspacing="0" width="100%" class="th-white">
                <tr>
                  <th scope="col" width="80">评论</th>
                  <th scope="col" width="80">分数</th>
                  <th scope="col" width="80">评论日期</th>
                  <th scope="col" width="80">评论用户</th>
                  <th scope="col" width="80">课程</th>
                  <th scope="col" width="80">状态</th>
                   <th scope="col" width="80">操作</th>
                </tr>
                <s:iterator value="remarkList">
	                <tr>
	                  <td>
	                  <s:if test="content!=null&&content.length()>20">
								<s:property value="content.substring(0,15)"/>...<a class="nameInfo" tooltip="<s:property value="content"/>" href="#">详细</a>
							</s:if>
							<s:else>
								<s:property value="content"/>
							</s:else>
					  </td>
					   <td><s:property value="score" /></td>
	                  <td><s:property value="happentime" /></td>
	                   <td><s:property value="user.getUser_name()" /></td>
	                   <td>
	                   <s:if test="course!=null">
	                   <s:property value="course.getCname()" />
	                   </s:if>
	                   </td>
	                   <td>
	                   <s:if test="childRemark!=null">
	                   	已回复
	                   </s:if>
	                   <s:else>未回复</s:else>
	                   </td>
	                    <td>
	                    <a href="#" onclick="reply('<s:property value="id"/>')">查看并回复</a>
	                    </td>
	                </tr>
                </s:iterator>
              </table>
          </div>
        
          <div >
                    	<jsp:include page="page.jsp">
							<jsp:param name="url" value="<%=path+"/remarkAction!remarkList.do"%>"/>
						</jsp:include>
                    </div>
        </div>
    </div>

</div>

</body>
<script type="text/javascript">
	
	function reply(id){
		var url = "<%=path %>/remarkAction!reply.do?id="+id;
		window.location.href = url;
	}
</script>
</html>
