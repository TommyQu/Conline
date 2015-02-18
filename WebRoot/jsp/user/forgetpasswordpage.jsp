<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ForgetPassword</title>
	<link rel="stylesheet" type="text/css" href="css/user/forgetpasswordpage.css">
  </head>
  <body>
    <div id="banner">
       <h1>找回密码</h1>
    </div>
    <form action="mail" method="post">
    <div id="name">
     <label class="textname">用户名</label>
     <input class="text" type="text" name="username"	id="" maxlength="11" tabindex='1' class="left" />
     </div>
     <br>
     <div id="email">
     <label class="textname">邮箱</label>
     <input class="text" type="text" name="email"	id="" maxlength="21" tabindex='1' class="left" />
     </div>
      	<br>
      	<div align="center"> 
      	<input id="submit" type="submit" name="submit" value="确认" />	  
      	<input id="back" type="button" name="back" value="返回" onclick="javascript:history.go(-1)"/>	
	    </div>  
	</form>
  </body>
</html>
