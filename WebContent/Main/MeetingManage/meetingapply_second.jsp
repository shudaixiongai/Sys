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
    
    <title>预约会议第二步</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="images/meetingapply.css">
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript">
	IDs=new Array();
	//获取table选中行的一行数据并添加到另一个table中
	function AddDelRow(obj) {
		var table = document.getElementById("equipTable");
		var check = obj.checked;
		if (check) { //添加
			IDs.push(obj.id);
			//alert("添加成功");
			var tr = obj.parentNode.parentNode;
			var tds = tr.cells;
			var tbody = document.getElementById("selectedTBody");
			var tr = document.createElement("tr");
			//赋值
			var td = document.createElement("td");
			var checkbox = document.createElement("input");
			checkbox.setAttribute("type", "checkbox");
			checkbox.setAttribute("id", "c" + obj.id);
			td.appendChild(checkbox);
			for ( var i = 1; i < tds.length; i++) {
				var text = document.createTextNode(tds[i].innerHTML + " ");
				td.appendChild(text);
				tr.appendChild(td);
			}
			tbody.appendChild(tr);
		} else { //删除
			for(var i=0;i<IDs.length;i++){
				if(IDs[i]==obj.id){
					IDs.pop();
					//alert("删除成功");
				}
			}
			var text = document.getElementById("c" + obj.id);
			var tr = text.parentNode.parentNode;
			var tbody = tr.parentNode;
			tbody.removeChild(tr);
		}
	}
	function GoServlet2(action,obj){
		action+="&IDs="+IDs;
		var objForm = document.getElementById(obj);
		objForm.action = action;
		objForm.submit();
	}
	</script>
	</head>
  
  <body>
    <div>
    	<div style="width:100%;text-align:center;">
    		<img alt="步骤" src="images/second.jpg">
    	</div>
    	<div>
    		<form method="post" id="meetingTwoForm">
    			<div class="divBox">
					<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
					<div class="boxContent">
						<h3>已选会议室</h3>
    					<table width="90%" frame="box" rules="all" align="center">
    						<tr><td><input type="hidden" name="homeID" value="${requestScope.home.id}"/>已选房间:</td>
    							<td>${requestScope.home.homeNo}</td><td>负责人:</td><td>${requestScope.home.hosterID}</td>
    							<td>负责人姓名:</td><td>${requestScope.home.hosterName}</td>
    						</tr>
    						<tr><td>可容纳人数:</td><td>${requestScope.home.space}</td>
    							<td>地址:</td><td  colspan="3">${requestScope.home.address}</td>
    						</tr>
    						</table>
    				</div>
					<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
				</div>
    			<div style="text-align:center; height:35px;margin:[10px 1px 10px 1px];">
    				<span style="font-size: 14px; color: red;">若需要其他设备,请查询并选择可用设备</span>
    				<input type="button" name="btnQuery" value="查询可用设备" onclick="GoServlet('servlet/MeetingServlet?step=3','meetingTwoForm')"/>
    			</div>
    			<div>
    				<div style="float:left;width:50%;text-align:center;">
    				<h3>可用设备列表</h3>
    				<table frame="box" rules="all" border="1px" width="450px" id="equipTable">
    					<tr style="background-color: #000000; color:White;font-weight:bold; font-size: 15px;">
							<td>点击选择</td>
    						<td>设备编号</td><td>设备名</td><td>设备类型</td>
    					</tr>
    					<c:forEach var="equip" items="${requestScope.equipList}">
    						<tr>
    							<td><input type="checkbox" name="line" id="${equip.id}" onclick="AddDelRow(this)"/></td>
    							<td>${equip.equipmentID}</td>
    							<td>${equip.equipmentName}</td>
    							<td>${equip.typeName}</td>
    						</tr>
    					</c:forEach>
    				</table>
    				</div>
    				<div style="float:right; width:50%; text-align:center;">
    					<h3>您选择了以下设备</h3>
    					<table frame="box" rules="all" border="1px" width="400px" id="selectedTable">
    						<tbody id="selectedTBody">
    						</tbody>
    					</table>
    				</div>
    			</div>
    			<div style="text-align:center;clear:both;">
    				<input type="button" name="front" value="上一步" onclick="GoServlet('<%=basePath%>servlet/MeetingServlet?step=5','meetingTwoForm')"/>
    				<input type="button" name="next" value="下一步" onclick="GoServlet2('<%=basePath%>servlet/MeetingServlet?step=4','meetingTwoForm')"/>
    			</div>
    		</form>
    	</div>
    </div>
  </body>
</html>
