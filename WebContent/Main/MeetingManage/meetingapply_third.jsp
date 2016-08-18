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
    
    <title>预约会议第三步</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="images/meetingapply.css">
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript">
	wIDs=new Array();
	//获取table选中行的一行数据并添加到另一个table中
	function AddDelRow(obj) {
		var check = obj.checked;
		if (check) { //添加
			wIDs.push(obj.id);
		} else { //删除
			for(var i=0;i<wIDs.length;i++){
				if(wIDs[i]==obj.id){
					wIDs.pop();
				}
			}
		}
	}
	function GoServlet2(action,obj){
		action+="&wIDs="+wIDs;
		var objForm = document.getElementById(obj);
		objForm.action = action;
		objForm.submit();
	}
	</script>
  </head>
  
  <body>
    <div>
    	<div style="width:100%;text-align:center;">
    		<img alt="步骤" src="images/third.jpg">
    	</div>
    	<div>
    		<form method="post" enctype="multipart/form-data" id="meetingThirdForm">
					<div class="divBox">
						<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
						<div class="boxContent">
							<h3>已选会议室</h3>
							<table width="90%" frame="box" rules="all" align="center">
								<tr><td>已选房间:</td><td>${requestScope.home.homeNo}</td>
									<td>负责人:</td><td>${requestScope.home.hosterID}</td>
									<td>负责人姓名:</td><td>${requestScope.home.hosterName}</td>
								</tr>
								<tr>
									<td>可容纳人数:</td><td>${requestScope.home.space}</td>
									<td>地址:</td><td colspan="3">${requestScope.home.address}</td>
								</tr>
							</table>
						</div>
						<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
					</div>
					
					<div class="divBox">
						<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
						<div class="boxContent">
							<h3>已选设备</h3>
							<table width="90%" frame="box" rules="all" align="center">
								<tr><td>设备编号</td><td>设备名</td><td>设备类型</td></tr>
								<c:forEach var="equip" items="${requestScope.equipList}">
    								<tr><td>${equip.equipmentID}</td><td>${equip.equipmentName}</td><td>${equip.typeName}</td></tr>
    							</c:forEach>
							</table>
						</div>
						<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
					</div><br/>
					<div>
						<div style="text-align:center;height:auto;max-height:400px;">
							<h3>选择与会人员</h3>
							<table frame="box" rules="all" border="1px" width="80%">
								<tr><td width="25px"><input type="checkbox" id="chAll" onclick="SelectAll(this);"/></td>
									<td>职工编号</td><td>姓名</td><td>所属部门</td><td>职位</td>
								</tr>
								<c:forEach var="worker" items="${requestScope.workerList}">
								<tr>
									<td><input type="checkbox" id="${worker.id}" onclick="AddDelRow(this)"/></td>
									<td>${worker.workerID}</td><td>${worker.name}</td>
									<td>${worker.departmentName}</td><td>${worker.positionName}</td>
								</tr>
								</c:forEach>
							</table>
						</div>
						<div style="text-align:center;height:auto;">
							<h3>填写会议信息</h3>
							<table>
							<tr><td>会议主题:</td><td><input type="text" name="topic"/></td></tr>
							<tr><td colspan="2"><span style="font-size: 13px; color: red;">支持的文件类型:doc,docx,ppt,txt,xls,xlsx,rar;且不得超过100M</span></td></tr>
							<tr><td>会议说明文档:</td><td><input type="file" name="documentLink"/></td></tr>
							</table>
						</div>
					</div><br/>
					<div style="text-align:center;clear:both;">
    					<input type="button" name="front" value="上一步" onclick="Redirect('<%=basePath%>servlet/MeetingServlet?step=6')"/>
    					<input type="button" name="next" value="提交预约" onclick="GoServlet2('<%=basePath%>servlet/MeetingServlet?step=7','meetingThirdForm')"/>
    				</div>
    				<c:if test="${!(empty sessionScope.flag)}"><jsp:forward page="success.jsp"/></c:if> 
			</form>
    	</div>
    </div>
  </body>
</html>
