//全选
function SelectAll(obj) {
	var allInput = document.getElementsByTagName("input");
	var size = allInput.length;
	for (i = 0; i < size; i++) {
		if (allInput[i].type == "checkbox") {
			allInput[i].checked = obj.checked;
		}
	}
}
//删除按钮点击
function BtnDel_Click(action) {
	var allInput = document.getElementsByTagName("input");
	var size = allInput.length;
	var count = 0;
	var id;
	for ( var i = 0; i < size; i++) {
		if (allInput[i].type == "checkbox" && allInput[i].id != "chAll") {
			if (allInput[i].checked) {
				count++;
				id = allInput[i].id;
			}
		}
	}
	if (count < 1){	
		alert("请选择要删除的行.");
		return false;
	}
	else if (count > 1){
		alert("一次只能删除一行.");
		return false;
	}
	else {
		confirm("确认删除吗?");
		action = action + "&id=" + id;
		Redirect(action);
	}
}
//更新按钮点击
function BtnUpd_Click(action) {
	var allInput = document.getElementsByTagName("input");
	var size = allInput.length;
	var count = 0;
	var id;
	for ( var i = 0; i < size; i++) {
		if (allInput[i].type == "checkbox" && allInput[i].id != "chAll") {
			if (allInput[i].checked) {
				count++;
				id = allInput[i].id;
			}
		}
	}
	if (count < 1){
		alert("请选择一行.");
		return false;
	}
	else if (count > 1){
		alert("一次只能选择一行.");
		return false;
	}
	else {
		action = action + "&id=" + id;
		Redirect(action);
	}
}
//执行相应的servlet
function GoServlet(action,obj) {
	var objForm = document.getElementById(obj);
	objForm.action = action;
	objForm.submit();
}
//跳转页面
function Redirect(url) {
	window.location.href = url;
}
