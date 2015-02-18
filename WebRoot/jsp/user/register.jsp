<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">
</script>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
      
  	<link rel="stylesheet" href="css/homepage/jqumaiery-ui-1.9.2.custom.min.css" />
	<link rel="stylesheet" href="css/homepage/jquery-ui-1.9.2.custom.css" />
    <script type="text/javascript" src="js/homepage/jquery-1.8.1.js"></script>
    <script type="text/javascript" src="js/homepage/jquery-ui.js"></script>
    <script type="text/javascript" src="js/homepage/jbar.js"></script>
<script type="text/javascript" src="js/homepage.js"></script>
<script type="text/javascript" src="js/user/register.js"></script>
<title>Register</title>
<link type="text/css" href="css/user/register.css" rel="stylesheet" />
</head>
<%
String code=(String)request.getSession().getAttribute("code");
 %>
<body>
	<div id="regist">
	<img src="img/user/regist.png"/>
	</div>
	<br/>
	<form action="user?action=register" method="post">
		<table>
			<tr>
				<td class="text">用户名：</td>
				<td><input class="text2" type="text" id="username" name="username" /> * 
				<img id="checkinguser" src="img/user/checking.gif" /> 6-8位非数字开头<div id="checkuser"></div>
				</td>
			</tr>
			<tr>
				<td class="text">密码：</td>
				<td><input class="text2" type="password" id="password" name="password" /> *
				<img id="checkingpassword" src="img/user/checking.gif" />8-16位数字与字母组合<div id="checkpassword"></div>
				</td>
			</tr>
			<tr>
				<td class="text">确认密码：</td>
				<td><input class="text2" type="password" id="passwordConfirm" /> *
				<img id="checkingpassword2" src="img/user/checking.gif" /><div id="checkpassword2"></div></td>
			</tr>
			<tr>
				<td class="text">性别：</td>
				<td><input class="text2" type="radio" name="sex" value="male" checked="true"/>男<input
					type="radio" name="sex" value="female" />女</td>
			</tr>
			<tr>
				<td class="text">邮箱地址：</td>
				<td><input class="text2" type="text" id="email" name="email" /> *<img
					id="checkingemail" src="img/user/checking.gif" />
				<div id="checkemail"></div>
				</td>
			</tr>
			<tr>
				<td class="text">生日：</td>
				<td><input class="text2" type="text" id="birthday" name="birthday" />
				<script>
                $( "#birthday" ).datepicker({  changeYear: true, minDate: new Date(1900, 1 , 1), dateFormat: 'yy-mm-dd'});
                </script>
				 *</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="button" name="moreinfo" value="更多信息" /></td>
			</tr>
			<tr>
				<td class="text">QQ：</td>
				<td><input class="text2" type="text" id="qq" name="qq" />
				</td>
			</tr>
			<tr>
				<td class="text">电话号码：</td>
				<td><input class="text2" type="text" id="phone" name="phone" />
				</td>
			</tr>
			<tr>
				<td class="text">地址：</td>
				<td><input class="text2" type="text" id="address" name="address" />
				</td>
			</tr>
			<tr>
				<td class="text">邮编：</td>
				<td><input class="text2" type="text" id="zipCode" name="zipCode" /><img
					id="checkingzipcode" src="img/user/checking.gif" />
					<div id="checkzipcode"></div>
				</td>
			</tr>
			<tr>
				<td class="text">学校：</td>
				<td><input class="text2" type="text" id="school" name="school" />
				</td>
			</tr>
			<tr>
				<td class="text">专业：</td>
				<td><input class="text2" type="text" id="major" name="major" />
				</td>
			</tr>
			<tr>
				<td class="text">年级：</td>
				<td><input class="text2" type="text" id="grade" name="grade" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="button" name="lessinfo" value="收起信息" /></td>
			</tr>
			<tr>
				<td class="text">验证码：</td>
				<input id="code" type="hidden" value="<%=code%>">
				<td><input class="text2" id="icode" type="text" /> <img border=0
					src="jsp/template/image.jsp" /><img
					id="checkingicode" src="img/user/checking.gif" />
					<div id="checkicode"></div>
				</td>
			</tr>
		</table>
		<br />
		<div id="buttondiv">
			<input id="submit" type="submit" name="submit" value="注册" /> 
			<!--  <input id="reset" type="reset" name="reset" value="重置" /> -->
			<input id="back"  type="button" name="back" value="返回" onclick="javascript:history.go(-1)" />
		</div>
	</form>
</body>
</html>