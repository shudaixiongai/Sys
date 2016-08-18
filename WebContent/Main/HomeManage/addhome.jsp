userpage<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加/修改会议室</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div style="width:100%">
    	<form action="servlet/AUHomeServlet?info=${requestScope.info}&ac=3" method="post">
    		<div style="width:100%">
    			<table align="center">
    				<tr><td><input type="hidden" name="id" value="${requestScope.home.id}"/></td><td colspan="3"><h3>添加/修改会议室信息</h3></td></tr>
    				<tr>
    					<td>房间号:</td><td><input type="text" name="homeNo" value="${requestScope.home.homeNo}" maxlength="10" size="20"/></td>
    				</tr>
    				<tr>
    					<td>地址:</td><td><input type="text" name="address" value="${requestScope.home.address}" maxlength="100" size="20"/></td>
    				</tr>
    				<tr>
    					<td>可容纳人数:</td><td><input type="text" name="space" value="${requestScope.home.space}" maxlength="11" size="20"/></td>
    				</tr>
    				<tr>
    					<td>负责人职工号:</td><td><input type="text" name="hosterID" value="${requestScope.home.hosterID}" size="20" maxlength="20"/></td>
    				</tr>
    				<tr>
    					<td><input type="submit" name="sub" value="保存"/></td>
    					<td><input type="reset" name="reset" value="重置"/></td>
    				</tr>
    			</table>
    		</div>
    	</form>
    </div>
  </body>
</html>
