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
    
    <title>添加设备</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/getDate/WdatePicker.js"></script>
  </head>
  
  <body>
    <div style="width:100%">
    	<form action="servlet/AUEquipServlet?info=${requestScope.info}&ac=3" method="post">
    		<div style="width:100%">
    			<table align="center">
    				<tr><td><input type="hidden" name="id" value="${requestScope.equip.id}"/></td><td colspan="3"><h3>添加/修改设备信息</h3></td></tr>
    				<tr><td>设备编号:</td><td><input type="text" name="equipmentID" value="${requestScope.equip.equipmentID}"/></td></tr>
    				<tr><td>设备名:</td><td><input type="text" name="equipmentName" value="${requestScope.equip.equipmentName}"/></td></tr>
    				<tr><td>设备类型:</td>
    					<td><select id="equiptype" name="equiptype" size="1">
    								<option value="0">---请选择---</option>
    								<c:forEach var="type" items="${requestScope.typeList}">
    									<c:choose>
    										<c:when test="${!(empty requestScope.equip)&&(type.id==requestScope.equip.equipType)}">
    											<option value="${type.id}" selected="selected">${type.typeName}</option>
    										</c:when>
    										<c:otherwise>
    											<option value="${type.id}">${type.typeName}</option>
    										</c:otherwise>
    									</c:choose>
    								</c:forEach>
    						</select></td>
    				</tr>
    				<tr>
    					<td>入库时间:</td><td><input type="text" name="storeTime" value="${requestScope.equip.storeTime}" onfocus="WdatePicker()"/></td>
    				</tr>
    				<tr><td>所属:</td><td><input type="text" name="belongTo" value="${requestScope.equip.belongTo}"/>
    					<span style="font-size: 11px; color: red;">若属于某会议室,请输入会议室号,否则输入"仓库"</span></td></tr>
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
