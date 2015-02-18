<%@page import="domain.CoUserInfo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.VCoForumTopicList"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/head.jsp">
	<jsp:param name="application-title" value="Modify" />
	<jsp:param name="application-left-jsp" value="/jsp/pmanage/menu.jsp" />
</jsp:include>
<script type="text/javascript" src="js/user/modify.js"></script>
<link type="text/css" href="css/user/modify.css" rel="stylesheet" />
<%
  CoUserInfo cui=(CoUserInfo)session.getAttribute("user");
 %>
<form action="pmanage?action=modify" method="post">
<div id="modify">
	<table>
		<tr>
			<td class="text">用户名：</td>
			<td><%=cui.getName() %></td>
		</tr>
		<tr>
			<td class="text">密码：</td>
			<td><input class="text2" type="password" id="password"
				name="password" /> * <img id="checkingpassword"
				src="img/user/checking.gif" />HINT
				<div id="checkpassword"></div></td>
		</tr>
		<tr>
			<td class="text">确认密码：</td>
			<td><input class="text2" type="password" id="passwordConfirm" />
				* <img id="checkingpassword2" src="img/user/checking.gif" />
				<div id="checkpassword2"></div>
			</td>
		</tr>
		<tr>
			<td class="text">邮箱地址：</td>
			<td><input class="text2" type="text" id="email" name="email" value="<%=cui.getEmail()%>"/>
				*<img id="checkingemail" src="img/user/checking.gif" />
				<div id="checkemail"></div></td>
		</tr>
		<tr>
			<td class="text">QQ：</td>
			<td><input class="text2" type="text" id="qq" name="qq" value="<%=cui.getQq()%>"/></td>
		</tr>
		<tr>
			<td class="text">电话号码：</td>
			<td><input class="text2" type="text" id="phone" name="phone" value="<%=cui.getPhone()%>"/>
			</td>
		</tr>
		<tr>
			<td class="text">地址：</td>
			<td><input class="text2" type="text" id="address" name="address" value="<%=cui.getAddress()%>"/>
			</td>
		</tr>
		<tr>
			<td class="text">邮编：</td>
			<td><input class="text2" type="text" id="zipCode" name="zipCode" value="<%=cui.getZipCode()%>"/>
			<img id="checkingzipcode" src="img/user/checking.gif" />
					<div id="checkzipcode"></div>
			</td>
		</tr>
		<tr>
			<td class="text">验证码：</td>
			<td><input class="text2" id="icode" type="text" /> <img src="jsp/template/image.jsp" /></td>
		</tr>
	</table>
	<br />
	<div id="buttondiv">
		<input id="submit" type="submit" name="submit" value="确认" />
		<input id="reset" type="reset" name="reset" value="重置" />
	</div>
	</div>
</form>
<jsp:include page="/jsp/template/foot.jsp"></jsp:include>