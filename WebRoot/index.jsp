<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery
/1.2.6/jquery.min.js"></script>

<script type="text/javascript" src="js/user/index.js"></script>
</head>
<body>
	<form action="user?action=login" method="post">
		<table id="user">
			<tr>
				<div id="banner">
					<h2>用户登录</h2>
					<br />
				</div>
			</tr id="usertr">
			<tr>
				<td>
					<div class="user">用户名</div>
				</td>
				<td><input type="text" name="username" id="username"
					maxlength="11" tabindex='1' class="left" />
				</td>
			</tr>
			<tr>
				<td>
					<div class="user">密码</div>
				</td>
				<td><input type="password" name="password" id="password"
					maxlength="11" tabindex='1' class="left" />
				</td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><div id="register">
						<a href="user?action=toregisterpage">新用户注册</a>
					</div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input id="loginbutton" type="submit" name="submit"
					value="登录" /></td>
				<td><a id="forgetpassword"
					href="user?action=toforgetpasswordpage">忘记密码?</a></td>
			</tr>
		</table>
	</form>
	<div id="broadcast"><h3>公告</h3><div id="broadtext"><hr/>我们的工程就要完成啦！  2013.06.19<br/><br/>今天解决了非常多问题。  2013.06.13</div></div>
</body>
</html>
