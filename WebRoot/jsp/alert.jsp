<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String message=(String)request.getAttribute("message");
String url=(String)request.getAttribute("url");
 %>
<html>
  <head>
  </head>
  <body>
  <%=message%>
  <a href="<%=url %>"/>返回上级界面
  </body>
</html>
