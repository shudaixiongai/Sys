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
    
    <title>设备管理</title>
    
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
    <div style="width:100%;">
    	<form id="equipForm" method="post">
    		<div style="text-align:center;">
    			<input type="radio" name="condition" value="1" checked="checked" />按设备名查询
				<input type="radio" name="condition" value="2" />按设备类型查询
				<input type="radio" name="condition" value="3" />按会议室查询
    			<input type="text" name="txtQuery"/>
    			<input type="button" name="btnQuery" value="查询" onclick="GoServlet('servlet/EquipServlet?pn=0','equipForm')"/>
    			<span style="font-size: 10px; color: red;">注:查询条件为空时,获取所有设备信息</span>
    		</div>
    		<div>
    			<table frame="box" rules="all" border="1px" width="100%">
    				<tr style="background-color: #000000; color:White;font-weight:bold; font-size: 15px;">
							<td width="25px"><input type="checkbox" name="line" id="chAll" onclick="SelectAll(this);"/></td>
							<td>设备编号</td><td>设备名</td><td>设备类型</td><td>所属</td><td>入库时间</td>
					</tr>
					<c:forEach var="equip" items="${requestScope.equipList}">
						<tr>
							<td><input type="checkbox" name="line" id="${equip.id}" /></td>
							<td>${equip.equipmentID}</td>
							<td>${equip.equipmentName}</td>
							<td>${equip.typeName}</td>
							<td>${equip.belongTo}</td>
							<td>${equip.storeTime}</td>
						</tr>
					</c:forEach>
				</table>
    		</div>
    		<div style="width: 100%;">
				<div style="float: left">
					共${page.pageCount}页|当前为第${page.pageNo}页
				</div>
				<div style="float: right">
					<a href="servlet/EquipServlet?pn=1">首页</a>
					<a href="servlet/EquipServlet?pn=2">上一页</a>
					<a href="servlet/EquipServlet?pn=3">下一页</a>
					<a href="servlet/EquipServlet?pn=4">末页</a>&nbsp; 第
					<input type="text" name="changPageNo" size="3" maxlength="5" />页
					<input type="button" name="btnGo" value="跳转"
						onclick="GoServlet('servlet/EquipServlet?pn=5','equipForm')" />
				</div>
			</div>
			<div style="text-align:center; width:100%;">
				<input type="button" name="btnAdd" value="添加设备"
						onclick="Redirect('<%=basePath%>servlet/AUEquipServlet?ac=1')" />
				<input type="button" name="btnDel" value="删除设备"
						onclick="BtnDel_Click('<%=basePath%>servlet/EquipServlet?pn=6')" />
				<input type="button" name="btnUpd" value="修改设备信息"
						onclick="BtnUpd_Click('<%=basePath%>servlet/AUEquipServlet?ac=2')" />
			</div>
    	</form>
    </div>
  </body>
</html>
