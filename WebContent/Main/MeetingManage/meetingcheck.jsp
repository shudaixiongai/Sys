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
    
    <title>预约审核</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="images/meetingapply.css">
	<script type="text/javascript" src="js/common.js"></script>

	</head>
  
  <body>
  	<div>
  		<form method="post" id="checkForm">
  			<div style="text-align:center;"> 
    			<input type="button" name="btnQuery" value="查询预约" onclick="GoServlet('servlet/MeetingCheckServlet?pn=0','checkForm')"/>
    		</div>
    		<div>
    			<table frame="box" rules="all" border="1px" width="100%">
    				<tr style="background-color: #000000; color:White;font-weight:bold; font-size: 15px;">
							<td width="25px"><input type="checkbox" name="line" id="chAll" onclick="SelectAll(this);"/></td>
							<td>会议室</td><td>参加人数</td><td>开始时间</td><td>结束时间</td>
							<td>会议主题</td><td>申请时间</td><td>申请人</td><td>申请人编号</td>
					</tr>
					<c:forEach var="meeting" items="${requestScope.meetingList}">
							<tr>
								<td><input type="checkbox" name="line" id="${meeting.id}" /></td>
								<td>${meeting.homeNo}</td>
								<td>${meeting.attendance}</td>
								<td>${meeting.beginTime}</td>
								<td>${meeting.endTime}</td>
								<td>${meeting.topic}</td>
								<td>${meeting.applyTime}</td>
								<td>${meeting.workerName}</td>
								<td>${meeting.workerID}</td>
							</tr>
					</c:forEach>
    			</table>
    		</div>
    		<div style="width: 100%;">
				<div style="float: left">
					共${page.pageCount}页|当前为第${page.pageNo}页
				</div>
				<div style="float: right">
					<a href="servlet/MeetingCheckServlet?pn=1">首页</a>
					<a href="servlet/MeetingCheckServlet?pn=2">上一页</a>
					<a href="servlet/MeetingCheckServlet?pn=3">下一页</a>
					<a href="servlet/MeetingCheckServlet?pn=4">末页</a>&nbsp; 第
					<input type="text" name="changPageNo" size="3" maxlength="5" />页
					<input type="button" name="btnGo" value="跳转"
						onclick="GoServlet('servlet/MeetingCheckServlet?pn=5','checkForm')" />
				</div>
			</div>
			<div style="text-align:center; width:100%;">
				<input type="button" name="btnCheckEquip" value="查看所需设备" onclick="BtnUpd_Click('<%=basePath%>servlet/MeetingCheckServlet?pn=7')"/>
				<input type="button" name="btnPass" value="通过审核" onclick="BtnUpd_Click('<%=basePath%>servlet/MeetingCheckServlet?pn=6')"/>
			</div>
			
			<div class="divBox" id="divEquip">
				<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
				<div class="boxContent">
					<h3>申请设备详情</h3>
					<table frame="box" rules="all" border="1px" align="center" width="70%">
						<tr><td>设备编号</td><td>设备名称</td><td>设备类型</td></tr>
						<c:forEach  var="equip" items="${requestScope.equipList}">
							<tr><td>${equip.equipmentID}</td><td>${equip.equipmentName}</td><td>${equip.typeName}</td></tr>
						</c:forEach>
					</table>
					<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
				</div>
			</div>
  		</form>
    </div>
  </body>
</html>
