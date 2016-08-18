<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>导航</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="stylesheet" type="text/css" href="images/main_left.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/ddaccordion.js"></script>
		<script type="text/javascript" src="js/main_left.js"></script>
	</head>
	<body>
		<div class="arrowlistmenu">
			<c:forEach var="menu" items="${sessionScope.menuList}">
				<h3 class="menuheader expandable">${menu.topic} </h3>
				<ul class="categoryitems">
					<c:forEach var="link" items="${menu.links}">
						<li>
							<a href="${link.mHref}" target="right">${link.alt}</a>
						</li>
					</c:forEach>
				</ul>
			</c:forEach>
		</div>
	</body>
</html>
