<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'adduser.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
td.title {
	text-align: right;
}

h3 {
	font-size: 20px;
}
</style>
<script type="text/javascript" src="js/common.js"></script>

<script type="text/javascript">
	function Check() {
		var text = document.getElementById("workerID").value;
		var count = 0;
		if (text.length < 1) {
			count++;
		}
		text = document.getElementById("name").value;
		if (text.length < 1) {
			count++;
		}
		text = document.getElementById("telephone").value;
		if (text.length < 1) {
			count++;
		}
		text = document.getElementById("birthday").value;
		if (text.length < 1) {
			count++;
		}
		text = document.getElementById("eMail").value;
		if (text.length < 1) {
			count++;
		}
		if (count > 0) {
			alert("您有" + count + "条信息需要完善!");
			return false;
		}
		return true;
	}
</script>
<script type="text/javascript"
	src="<%=basePath%>js/getDate/WdatePicker.js"></script>
</head>

<body>
	<div style="width: 100%">
		<form method="post"
			action="servlet/AUWorkerServlet?info=${requestScope.info}&ac=3">
			<div style="width: 100%;">
				<table align="center">

					<tr>
						<td><input type="hidden" name="id"
							value="${requestScope.worker.id}" /></td>
						<td colspan="3"><h3>添加/修改用户</h3></td>
					</tr>
					<tr>
						<td class="title">职工编号:</td>
						<td><input type="text" id="workerID" name="workerID"
							size="10" maxlength="20" value="${requestScope.worker.workerID}" />
						</td>
						<td class="title">用户组:</td>
						<td><select name="userGroup" size="1">
								<c:choose>
									<c:when
										test='${!(empty requestScope.worker)&&(requestScope.worker.userGroup=="管理员")}'>
										<option value="普通用户">普通用户</option>
										<option value="管理员" selected="selected">管理员</option>
									</c:when>
									<c:otherwise>
										<option value="普通用户" selected="selected">普通用户</option>
										<option value="管理员">管理员</option>
									</c:otherwise>
								</c:choose>
						</select></td>
					</tr>
					<tr>
						<td class="title">姓&nbsp;名:</td>
						<td><input type="text" id="name" name="name" size="10"
							maxlength="20" value="${requestScope.worker.name}" /></td>
						<td class="title">电&nbsp;话:</td>
						<td><input type="text" id="telephone" name="telephone"
							size="10" maxlength="11" value="${requestScope.worker.telephone}" />
						</td>
					</tr>
					<tr>
						<td class="title">生&nbsp;日:</td>
						<td><input type="text" name="birthday" id="birthday"
							size="10" maxlength="10" value="${requestScope.worker.birthday}"
							onfocus="WdatePicker()" /> <span
							style="font-size: 11px; color: Red;">例:1990-01-02</span></td>
						<td class="title">性&nbsp;别:</td>
						<td><c:choose>
								<c:when
									test='${!(empty requestScope.worker)&&(requestScope.worker.sex=="女")}'>
									<input type="radio" name="sex" value="男" />男 
    									<input type="radio" name="sex" value="女" checked="checked" />女
    								</c:when>
								<c:otherwise>
									<input type="radio" name="sex" value="男" checked="checked" />男 
    									<input type="radio" name="sex" value="女" />女
    								</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<td class="title">部&nbsp;门:</td>
						<td><select id="department" name="department" size="1">
								<option value="0">---请选择---</option>
								<c:forEach var="depart" items="${requestScope.departList}">
									<c:choose>
										<c:when
											test="${!(empty requestScope.worker)&&(depart.id==requestScope.worker.departmentID)}">
											<option value="${depart.id}" selected="selected">${depart.departmentName}</option>
										</c:when>
										<c:otherwise>
											<option value="${depart.id}">${depart.departmentName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
						<td class="title">职&nbsp;位:</td>
						<td><select id="position" name="position" size="1">
								<option value="0">---请选择---</option>
								<c:forEach var="posi" items="${requestScope.positionList}">
									<c:choose>
										<c:when
											test="${!(empty requestScope.worker)&&(posi.id==requestScope.worker.positionID)}">
											<option value="${posi.id}" selected="selected">${posi.positionName}</option>
										</c:when>
										<c:otherwise>
											<option value="${posi.id}">${posi.positionName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>邮箱:</td>
						<td colspan="3"><input type="text" id="eMail" name="eMail"
							value="${requestScope.worker.eMail}" /> <span
							style="font-size: 11px; color: Red;">例:myemail@163.com</span></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" name="sub" value="保存"
							onclick="Check();" /></td>
						<td><input type="reset" name="re" value="重置" /></td>
						<td></td>
					</tr>
				</table>

			</div>
		</form>
	</div>
</body>
</html>
