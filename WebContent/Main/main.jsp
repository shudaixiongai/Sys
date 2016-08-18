<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function closeup(){
			//window.close();
			window.location.href="../index.jsp";
		}
	</script>
  </head>
    <frameset rows="64,*" frameborder="0" border="0">
		<frame src="Main/main_top.jsp" name="top" scrolling="no" />
		<frameset cols="185,*" frameborder="0" border="0">
			<frame src="Main/main_left.jsp" name="left" target="right" scrolling="no"/>
			<frame src="" name="right" target="_self"/>
		</frameset>
	</frameset>
	<noframes>
		<body>
			很抱歉，馈下使用的浏览器不支援框架功能，请转用新的浏览器。
		</body>
	</noframes>
  
</html>
