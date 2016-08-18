 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>预约会议第一步</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/getDate/WdatePicker.js"></script>
  	<script type="text/javascript" src="js/common.js"></script>
  </head>
  
  <body>
    <div>
    	<div style="width:100%;text-align:center;">
    		<img alt="步骤" src="images/first.jpg">
    	</div>
    	<div>
    		<form id="meetingFirstForm" method="post">
    			<div style="text-align:center;">
    				会议时间:<input type="text" name="beginTime" onfocus="WdatePicker()" size="10" maxlength="10" value="${requestScope.meetingTime.meetingDate}"/>&nbsp; 
    				 <select name="beginHour" size="1">
    				 	<c:forEach var="bh" begin="00" end="23">
    				 		<c:choose>
    				 			<c:when test="${!(empty requestScope.meetingTime)&&(requestScope.meetingTime.startHour==bh)}">
    				 				<option value="${bh}" selected="selected">${bh}</option>
    				 			</c:when>
    				 			<c:otherwise>
    				 				<option value="${bh}">${bh}</option>
    				 			</c:otherwise>
    				 		</c:choose>
    				 	</c:forEach>
    				 </select>时
    				 <select name="beginMinute" size="1">
    				 	<c:forEach var="bm" begin="00" end="59">
    				 		<c:choose>
    				 			<c:when test="${!(empty requestScope.meetingTime)&&(requestScope.meetingTime.startMinute==bm)}">
    				 				<option value="${bm}" selected="selected">${bm}</option>
    				 			</c:when>
    				 			<c:otherwise>
    				 				<option value="${bm}">${bm}</option>
    				 			</c:otherwise>
    				 		</c:choose>
    				 	</c:forEach>
    				 </select>分--
    				 <select name="endHour" size="1">
    				 	<c:forEach var="eh" begin="00" end="23">
    				 		<c:choose>
    				 			<c:when test="${!(empty requestScope.meetingTime)&&(requestScope.meetingTime.endHour==eh)}">
    				 				<option value="${eh}" selected="selected">${eh}</option>
    				 			</c:when>
    				 			<c:otherwise>
    				 				<option value="${eh}">${eh}</option>
    				 			</c:otherwise>
    				 		</c:choose>
    				 	</c:forEach>
    				 </select>时
    				 <select name="endMinute" size="1">
    				 	<c:forEach var="em" begin="00" end="59">
    				 		<c:choose>
    				 			<c:when test="${!(empty requestScope.meetingTime)&&(requestScope.meetingTime.endMinute==em)}">
    				 				<option value="${em}" selected="selected">${em}</option>
    				 			</c:when>
    				 			<c:otherwise>
    				 				<option value="${em}">${em}</option>
    				 			</c:otherwise>
    				 		</c:choose>
    				 		
    				 	</c:forEach>
    				 </select>分<br/>
    				  会议人数:<input type="text" name="attendance" size="5" maxlength="5" value="${requestScope.attendance}"/>
    				 <input type="submit" name="btnQuery" value="查询" onclick="GoServlet('servlet/MeetingServlet?step=1&pn=0','meetingFirstForm')"/>
    			</div>
    			<div style="text-align:center;">
    				<table width="100%" frame="box" rules="all" align="center">
    					<tr style="background-color: #000000; color:White;font-weight:bold; font-size: 15px;">
    						<td width="25px" ><input type="checkbox" name="line" id="chAll" onclick="SelectAll(this);"/></td>
    						<td>房间号</td><td>可容纳人数</td><td>负责人</td><td>负责人姓名</td><td>地址</td>
    					</tr>
    					<c:forEach var="home" items="${requestScope.homeList}">
							<tr>
								<td><input type="checkbox" name="line" id="${home.id}" /></td>
								<td>${home.homeNo}</td>
								<td>${home.space}</td>
								<td>${home.hosterID}</td>
								<td>${home.hosterName}</td>
								<td>${home.address}</td>
							</tr>
						</c:forEach>
    				</table>
    			</div>
    			<div style="width: 100%;">
				<div style="float: left">
					共${page.pageCount}页|当前为第${page.pageNo}页
				</div>
				<div style="float: right">
					<a href="servlet/MeetingServlet?step=1&pn=1">首页</a>
					<a href="servlet/MeetingServlet?step=1&pn=2">上一页</a>
					<a href="servlet/MeetingServlet?step=1&pn=3">下一页</a>
					<a href="servlet/MeetingServlet?step=1&pn=4">末页</a>&nbsp; 第
					<input type="text" name="changPageNo" size="3" maxlength="5" />页
					<input type="button" name="btnGo" value="跳转"
						onclick="GoServlet('servlet/MeetingServlet?step=1&pn=5','meetingFirstForm')" />
				</div>
			</div>
    			<div style="text-align:center">
    				<input type="button" name="next" value="下一步" onclick="BtnUpd_Click('<%=basePath%>servlet/MeetingServlet?step=2')"/>
    			</div>
    		</form>
    	</div>
    </div>
  </body>
</html>
