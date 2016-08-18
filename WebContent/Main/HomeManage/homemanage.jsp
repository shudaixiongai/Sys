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
    
    <title>My JSP 'homeManage.jsp' starting page</title>
    
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
    	<form id="homeForm" method="post">
    		<div style="text-align:center;">
    			房间号:<input type="text" name="homeNo"/>
    			<input type="button" name="btnQuery" value="查询" onclick="GoServlet('servlet/HomeServlet?pn=0','homeForm')"/>
    			<span style="font-size: 10px; color: red;">注:查询条件为空时,获取所有会议室信息</span>
    		</div>
    		<div>
    			<table frame="box" rules="all" border="1px" width="100%">
    				<tr style="background-color: #000000; color:White;font-weight:bold; font-size: 15px;">
							<td width="25px"><input type="checkbox" name="line" id="chAll" onclick="SelectAll(this);"/></td>
							<td>房间号</td><td>地址</td><td>可容纳人数</td><td>负责人职工号</td><td>负责人姓名</td>
					</tr>
					<c:forEach var="home" items="${requestScope.homeList}">
							<tr>
								<td><input type="checkbox" name="line" id="${home.id}" /></td>
								<td>${home.homeNo}</td>
								<td>${home.address}</td>
								<td>${home.space}</td>
								<td>${home.hosterID}</td>
								<td>${home.hosterName}</td>
							</tr>
					</c:forEach>
    			</table>
    		</div>
			<div style="width: 100%;">
				<div style="float: left">
					共${page.pageCount}页|当前为第${page.pageNo}页
				</div>
				<div style="float: right">
					<a href="servlet/HomeServlet?pn=1">首页</a>
					<a href="servlet/HomeServlet?pn=2">上一页</a>
					<a href="servlet/HomeServlet?pn=3">下一页</a>
					<a href="servlet/HomeServlet?pn=4">末页</a>&nbsp; 第
					<input type="text" name="changPageNo" size="3" maxlength="5" />页
					<input type="button" name="btnGo" value="跳转"
						onclick="GoServlet('servlet/HomeServlet?pn=5','homeForm')" />
				</div>
			</div>
			<div style="text-align: center; width:100%;">
				<input type="button" name="btnAdd" value="增加会议室"
						onclick="Redirect('<%=basePath%>servlet/AUHomeServlet?ac=1')" />
				<input type="button" name="btnDel" value="删除会议室"
						onclick="BtnDel_Click('<%=basePath%>servlet/AUHomeServlet?pn=6')" />
				<input type="button" name="btnUpd" value="修改会议室信息"
						onclick="BtnUpd_Click('<%=basePath%>servlet/AUHomeServlet?ac=2')" />
			</div>
		</form>	
    </div>
  </body>
</html>
