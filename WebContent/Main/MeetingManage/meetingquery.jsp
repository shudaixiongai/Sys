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
    
    <title>查询会议</title>
    
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
    <div>
    	<form method="post" id="meetingForm">
    		<div style="text-align:center;"> 
    			会议主题:<input type="text" name="topic" />
    			<input type="button" name="btnQuery" value="查询" onclick="GoServlet('servlet/MeetingQueryServlet?pn=0','meetingForm')"/>
    			<span style="font-size: 10px; color: red;">注:查询条件为空时,获取所有预约信息</span>
    		</div>
    		<div>
    			<table frame="box" rules="all" border="1px" width="100%">
    				<tr style="background-color: #000000; color:White;font-weight:bold; font-size: 15px;">
							<td width="25px"><input type="checkbox" name="line" id="chAll" onclick="SelectAll(this);"/></td>
							<td>会议主题</td><td>会议室</td><td>地址</td><td>会议说明文档</td><td>参加人数</td><td>开始时间</td><td>结束时间</td><td>发起人</td>
					</tr>
					<c:forEach var="meeting" items="${requestScope.meetingList}">
							<tr>
								<td><input type="checkbox" name="line" id="${meeting.id}" /></td>
								<td>${meeting.topic}</td>
								<td>${meeting.homeNo}</td>
								<td>${meeting.address}</td>
								<td><a href="upload/meetingdoc/${meeting.documentLink}">${meeting.documentLink}</a></td>
								<td>${meeting.attendance}</td>
								<td>${meeting.beginTime}</td>
								<td>${meeting.endTime}</td>
								<td>${meeting.workerName}</td>
							</tr>
					</c:forEach>
    			</table>
    		</div>
    		<div style="width: 100%;">
				<div style="float: left">
					共${page.pageCount}页|当前为第${page.pageNo}页
				</div>
				<div style="float: right">
					<a href="servlet/MeetingQueryServlet?pn=1">首页</a>
					<a href="servlet/MeetingQueryServlet?pn=2">上一页</a>
					<a href="servlet/MeetingQueryServlet?pn=3">下一页</a>
					<a href="servlet/MeetingQueryServlet?pn=4">末页</a>&nbsp; 第
					<input type="text" name="changPageNo" size="3" maxlength="5" />页
					<input type="button" name="btnGo" value="跳转"
						onclick="GoServlet('servlet/MeetingQueryServlet?pn=5','meetingForm')" />
				</div>
			</div>
    	</form>
    </div>
  </body>
</html>
