<%@page import="domain.CoProblem"%>
<%@page import="domain.CoFileInfo"%>
<%@page import="domain.VCoForumTopicList"%>
<%@page import="domain.CoForumTopic"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.CoUserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/admin/adminpage.jsp" ></jsp:include>

<%
  List<CoProblem> lcp=(List<CoProblem>)request.getAttribute("lcp");
  int i=0;
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
  <form>
  <table>
  <tr><td colspan="5" class="title">删除题目</td></tr>
    <tr class="column">
      <td>题目id</td>
      <td>关键字</td>
      <td>提供者</td>
      <td>上传时间</td>
    </tr>
  <%
    for(i=0;i<lcp.size();i++)
    {
   %>
   <tr>
      <td><%=lcp.get(i).getId() %></td>
      <td><%=lcp.get(i).getKeyword() %></td>
      <td><%=lcp.get(i).getProviderName() %></td>
      <td><%=df.format(lcp.get(i).getCreateTime()) %></td>
      <td><a href="admin?action=deleteproblem&id=<%=lcp.get(i).getId()%>">删除</a></td>
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