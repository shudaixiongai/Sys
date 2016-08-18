<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/common.js"></script>
  </head>
  
  <body>
		<div style="width: 100%;">
			<form method="post" id="userForm">
				<div style="text-align: center;">
					<input type="radio" name="condition" value="1" checked="checked" />
					按姓名查询
					<input type="radio" name="condition" value="2" />按部门查询
					<input type="text" name="txtQeury" />
					<input type="button" name="btnQuery" value="查询"
						onclick="GoServlet('servlet/WorkerServlet?pn=0','userForm');"/>
					<span style="font-size: 10px; color: red;">注:查询条件为空时,获取所有用户信息</span>
				</div>
				<div>
					<table frame="box" rules="all" border="1px" width="100%">
						<tr style="background-color: #000000; color:White;font-weight:bold; font-size: 15px;">
							<td width="25px"><input type="checkbox" name="line" id="chAll" onclick="SelectAll(this);"/></td>
							<td>员工号</td><td>姓名	</td><td>所属部门</td>
							<td>职位	</td><td>电话</td><td>性别</td>
							<td>生日</td><td>用户组</td><td>邮箱</td>
						</tr>
						<c:forEach var="worker" items="${requestScope.workerList}">
							<tr>
								<td>
									<input type="checkbox" name="line" id="${worker.id}" />
								</td>
								<td>${worker.workerID}</td><td>${worker.name}</td>
								<td>${worker.departmentName}</td><td>${worker.positionName}</td>
								<td>${worker.telephone}</td><td>${worker.sex}</td>
								<td>${worker.birthday}</td><td>${worker.userGroup}</td>
								<td>${worker.eMail}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div style="width:100%;">
					<div style="float: left">共${page.pageCount}页|当前为第${page.pageNo}页</div>
					<div style="float: right">
						<a href="servlet/WorkerServlet?pn=1">首页</a>
						<a href="servlet/WorkerServlet?pn=2">上一页</a>
						<a href="servlet/WorkerServlet?pn=3">下一页</a>
						<a href="servlet/WorkerServlet?pn=4">末页</a>&nbsp;
						第<input type="text" name="changPageNo" size="3" maxlength="5"/>页
						<input type="button" name="btnGo" value="跳转" onclick="GoServlet('servlet/WorkerServlet?pn=5','userForm')"/>
					</div>
				</div>
				<div style="text-align: center; width:100%;">
					<input type="button" name="btnAdd" value="增加用户"
						onclick="Redirect('<%=basePath%>servlet/AUWorkerServlet?ac=1')" />
					<input type="button" name="btnDel" value="删除用户"
						onclick="BtnDel_Click('WorkerServlet?pn=6')" />
					<input type="button" name="btnUpd" value="修改用户信息"
						onclick="BtnUpd_Click('AUWorkerServlet?ac=2')" />
				</div>
			</form>
		</div>
	</body>
</html>
