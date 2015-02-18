<%@page import="domain.CoForumLog"%>
<%@page import="domain.CoProblem"%>
<%@page import="domain.CoFileInfo"%>
<%@page import="domain.VCoForumTopicList"%>
<%@page import="domain.CoForumTopic"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.CoUserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/admin/adminpage.jsp" ></jsp:include>

<%
  List<CoForumLog> lcfl=(List<CoForumLog>)request.getAttribute("lcfl");
  CoUserInfo cui=(CoUserInfo)request.getSession().getAttribute("user");
  int i=0;
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
  <form>
  <table>
  <tr><td colspan="5" class="title">论坛日志</td></tr>
    <tr class="column">
      <td>日志id</td>
      <td>操作人</td>
      <td>日志时间</td>
      <td>日志说明</td>
      <td>操作</td>
    </tr>
  <%
    for(i=0;i<lcfl.size();i++)
    {
   %>
   <tr>
      <td><%=lcfl.get(i).getId() %></td>
      <td><%=cui.getName() %></td>
      <td><%=df.format(lcfl.get(i).getCreatTime()) %></td>
      <td><%=lcfl.get(i).getContent() %></td>
      <td><a href="admin?action=deletelog&id=<%=lcfl.get(i).getId()%>">删除</a></td>
    </tr>
   <%
    }
   %>
   </table>
   </form>
   <div class="pagenumber">当前第 <%=request.getAttribute("page")%> 页<br/>
<br />
<a href="admin?action=todeleteproblempage&flag=up&page=<%=request.getAttribute("page") %>">上一页</a>
<a href="admin?action=todeleteproblempage&flag=down&page=<%=request.getAttribute("page") %>">下一页</a>
</div>
<jsp:include page="/jsp/template/foot.jsp" ></jsp:include>