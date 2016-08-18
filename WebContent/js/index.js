function reloadImage() {
	document.getElementById("imgservlet").src = 'CheckCodeServlet?ts=' + new Date()
			.getTime();
}
function Check(){
	var text=document.getElementById("workerID").value;
	var msg='';
	if(text.length<1)
		msg+='请输入用户名; \n ';
	text=document.getElementById("password").value;
	if(text.length<1)
		msg+='请输入密码;';
	if(msg!=''){
		alert(msg);
		return false;
	}
	return true;
}