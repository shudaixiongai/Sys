<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<title>My JSP 'main_top.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="images/main_top.css">
		<script type="text/javascript">
			function closew(){
				window.parent.closeup();
			}
		</script>
	</head>
	<body>
		<table width="100%" height="64px" border="0" cellpadding="0"
			cellspacing="0" class="user_topbg">
			<tr>
				<td width="60%" height="64">
					<img src="images/logo.jpg" width="262px" height="64px">
				</td>
				<td width="40%">
					<table>
						<tr>
							<td width="74%" valign="top" class="wel_txt">
								欢迎 [${sessionScope.user.workersID}]登录...
							</td>
							<td width="22%">
								<a href="#" onClick="closew();"> 
								<img src="images/out.gif" alt="退出" width="46px" height="20px"
										border="0"> </a></td>
							<td width="4%">&nbsp;</td>
						</tr>
						<tr>
							<td height="19px" colspan="3">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
