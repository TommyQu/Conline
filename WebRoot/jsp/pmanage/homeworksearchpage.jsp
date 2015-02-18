<%@page import="domain.CoCourseAssignmentSubmit"%>
<%@page import="domain.VCoUserLog"%>
<%@page import="domain.CoUserInfo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.VCoForumTopicList"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/head.jsp">
	<jsp:param name="application-title" value="Homework Search" />
	<jsp:param name="application-left-jsp" value="/jsp/pmanage/menu.jsp" />
</jsp:include>
<script type="text/javascript" src="js/user/modify.js"></script>
<link type="text/css" href="css/user/modify.css" rel="stylesheet" />
<%
  List<CoCourseAssignmentSubmit> lccas=(List<CoCourseAssignmentSubmit>)request.getAttribute("lccas");
  int i=0;
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
<table id="loginsearch2">
  <tr>
    <td>章节号</td>
    <td>题目号</td>
    <td>提交时间</td>
    <td>编程语言</td>
    <td>作业状态</td>
    <td>作业得分</td>
    <td>评测结果</td>
    <td>附加信息</td>
  </tr>
  <%
    for(i=0;i<lccas.size();i++)
    {
   %>
  <tr>
    <td><%=lccas.get(i).getChapterId() %></td>
    <td><%=lccas.get(i).getPid() %></td>
    <td><%=df.format(lccas.get(i).getSubmitTime()) %></td>
    <td><%=lccas.get(i).getLanguage() %></td>
    <td><%=lccas.get(i).getJudgeStatus() %></td>
    <td><%=lccas.get(i).getScore() %></td>
    <td><%=lccas.get(i).getJudgeResult() %></td>
    <td><%=lccas.get(i).getExtendMessage() %></td>
  </tr>
  <%
    }
   %>
</table>
<div class="pagenumber"><br/><br/>当前第 <%=request.getAttribute("page")%> 页<br/>
<br />
<a href="pmanage?action=tohomeworksearchpage&flag=up&page=<%=request.getAttribute("page") %>">上一页</a>
<a href="pmanage?action=tohomeworksearchpage&flag=down&page=<%=request.getAttribute("page") %>">下一页</a>
</div> 

<jsp:include page="/jsp/template/foot.jsp"></jsp:include>