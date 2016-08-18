<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="images/skin.css">
	<script type="text/javascript" src="js/index.js">
	</script>
	</head>
  
  <body>
    <table width="100%" height="166px" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="42px" valign="top">
    			<table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      			<tr>
        			<td width="1%" height="21">&nbsp;</td>
        			<td height="42">&nbsp;</td>
        			<td width="17%">&nbsp;</td>
      			</tr>
    			</table>
    		</td>
  		</tr>
    	<tr>
    		<td valign="top">
    			<table width="100%" height="532px" border="0" cellpadding="0" cellspacing="0" class="login_bg">
    				<tr>
    					<td width="49%" align="right"></td>
    					<td width="1%"></td>
    					<td width="50%" valign="bottom">
    						<table width="100%" height="59px" border="0" align="center" cellpadding="0" cellspacing="0">
    							<tr>
    								<td width="4%">&nbsp;</td>
              						<td width="96%" height="38px">
              							<span class="login_txt_bt">登录会议管理系统</span>
              						</td>
            					</tr>
    							<tr>
    								<td>&nbsp;</td>
    								<td>
    									<table cellSpacing="0" cellPadding="0" width="100%" border="0" id="tabLogin" height="328px">
    										<tr>
    											<td height="179px" colspan="2" align="center">
														<form name="myform" action="servlet/UserServlet" method="post">
															<table cellSpacing="0" cellPadding="0" width="100%"
																border="0" height="143px" id="tbForm">
																<tr>
																	<td height="36px" >
																		<span class="login_txt">帐 &nbsp;&nbsp;号：</span>
																	</td>
																	<td height="36px" class="td_text">
																		<input id="workerID" name="workerID" value="" maxlength="20" size="20">
																	</td>
																	<td>&nbsp;</td>
																</tr>
																<tr>
																	<td height="36px">
																		<span class="login_txt">密  &nbsp;&nbsp;码：</span>
																	</td>
																	<td height="36px" class="td_text">
																		<input id="password" type="password" name="password" maxlength="30" size="20"/>
																	</td>
																	<td>
																		<img src="images/lock.gif" width="19px" height="18px"/>
																	</td>
																</tr>
																<tr>
																	<td height="36px" class="td_text">
																		<span class="login_txt">角  &nbsp;&nbsp;色：</span></td>
																	<td colspan="2" height="36px" class="td_text">
																		<input type="radio" name="roleID" value="0" checked="checked"/>普通用户
																		<input type="radio" name="roleID" value="1"/>管理员
																	</td>
																</tr>
																<tr>
																	<td height="36px">
																		<span class="login_txt">验证码：</span>
																	</td>
																	<td height="36px" colspan="2" class="td_text">
																		<input type="text" name="checkcode"
																			value="" maxLength="5" size="5"/>
																		<img id="imgservlet" src="CheckCodeServlet" onclick="reloadImage()" width="60px" height="20x"/>
																	</td>
																</tr>
																<tr>
																	<td height="36px">
																		&nbsp;
																	</td>
																	<td width="20%" height="36px">
																		<input name="submit" type="submit" id="submit" value="登 陆" onclick="Check();"/>
																	</td>
																	<td width="67%" class="top_hui_text" height="36px">
																		<input name="cs" type="button" value="取 消"/>
																	</td>
																</tr>
															</table>
														</form>
													</td>
    										</tr>
    										<tr>
                    							<td width="433px" height="164px" align="right" valign="bottom">
                    								<img src="images/login-wel.gif" width="242px" height="138px">
                    							</td>
                    							<td width="57px" align="right" valign="bottom">&nbsp;</td>
                  							</tr>
    									</table>
    								</td>
    							</tr>
    						</table>
    					</td>
    				</tr>
    			</table>
    		</td>
    	</tr>
    	<tr>
    		<td height="20px">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      			<tr>
        			<td align="center"><span class="login-buttom-txt">Copyright &copy; 2013-2014 </span></td>
      			</tr>
    			</table>
   			</td>
    	</tr>
    </table>
  </body>
</html>
