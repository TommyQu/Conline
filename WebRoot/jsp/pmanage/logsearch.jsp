<%@page import="domain.VCoUserLog"%>
<%@page import="domain.CoUserInfo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.VCoForumTopicList"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/head.jsp">
	<jsp:param name="application-title" value="Log Search" />
	<jsp:param name="application-left-jsp" value="/jsp/pmanage/menu.jsp" />
</jsp:include>
<script type="text/javascript" src="js/user/modify.js"></script>
<link type="text/css" href="css/user/modify.css" rel="stylesheet" />
<%
  List<VCoUserLog> lvcul=(List<VCoUserLog>)request.getAttribute("lvcul");
  int i=0;
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
<table id="loginsearch">
  <tr>
    <td>操作人</td>
    <td>日志类型</td>
    <td>日志内容</td>
    <td>日志时间</td>
  </tr>
  <%
    for(i=0;i<lvcul.size();i++)
    {
   %>
  <tr>
    <td><%=lvcul.get(i).getName() %></td>
    <td><%=lvcul.get(i).getLogType() %></td>
    <td><%=lvcul.get(i).getLogContent() %></td>
    <td><%=df.format(lvcul.get(i).getCreateTime()) %></td>
  </tr>
  <%
    }
   %>
</table>
<div class="pagenumber">当前第 <%=request.getAttribute("page")%> 页<br/>
<br />
<a href="pmanage?action=tologsearch&flag=up&page=<%=request.getAttribute("page") %>">上一页</a>
<a href="pmanage?action=tologsearch&flag=down&page=<%=request.getAttribute("page") %>">下一页</a>
</div> 

<jsp:include page="/jsp/template/foot.jsp"></jsp:include>