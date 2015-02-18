<%@page import="domain.CoJudgeClient"%>
<%@page import="domain.CoFileInfo"%>
<%@page import="domain.VCoForumTopicList"%>
<%@page import="domain.CoForumTopic"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.CoUserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/admin/adminpage.jsp" ></jsp:include>

<%
  List<CoJudgeClient> lcfi=(List<CoJudgeClient>)request.getAttribute("lcjc");
  int i=0;
 %>
  <form>
  <table>
  <tr><td colspan="5" class="title">删除资料</td></tr>
    <tr class="column">
      <td>评测机IP地址</td>
      <td>当前评测线程数</td>
      <td>最大并发评测线程</td>
      <td>分配任务优先级</td>
      <td>是否上线</td>
    </tr>
  <%
    for(i=0;i<lcfi.size();i++)
    {
   %>
   <tr>
      <td><%=lcfi.get(i).getIp() %></td>
      <td><%=lcfi.get(i).getCurrentThread() %></td>
      <td><%=lcfi.get(i).getMaxJudgeThread() %></td>
      <td><%=lcfi.get(i).getLevel() %></td>
      <td><%=lcfi.get(i).getStatus() %></td>
    </tr>
   <%
    }
   %>
   </table>
   </form>
   <div class="pagenumber">当前第 <%=request.getAttribute("page")%> 页<br/>
<br />
<a href="admin?action=judgeclient&flag=up&page=<%=request.getAttribute("page") %>">上一页</a>
<a href="admin?action=judgeclient&flag=down&page=<%=request.getAttribute("page") %>">下一页</a>
</div>
<jsp:include page="/jsp/template/foot.jsp" ></jsp:include>