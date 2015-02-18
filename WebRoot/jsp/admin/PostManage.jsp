<%@page import="domain.VCoForumTopicList"%>
<%@page import="domain.CoForumTopic"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.CoUserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/admin/adminpage.jsp" ></jsp:include>

<%
  List<VCoForumTopicList> lvcftl=(List<VCoForumTopicList>)request.getAttribute("lvcftl");
  int i=0;
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
  <form>
  <table>
  <tr><td colspan="5" class="title">删除帖子</td></tr>
    <tr class="column">
      <td>标题</td>
      <td>关键字</td>
      <td>上传者</td>
      <td>创建日期</td>
      <td>操作</td>
    </tr>
  <%
    for(i=0;i<lvcftl.size();i++)
    {
   %>
   <tr>
      <td><%=lvcftl.get(i).getTopic() %></td>
      <td><%=lvcftl.get(i).getKeyword() %></td>
      <td><%=lvcftl.get(i).getUserName() %></td>
      <td><%=df.format(lvcftl.get(i).getCreateTime()) %></td>
      <td><a href="admin?action=deletepost&id=<%=lvcftl.get(i).getId()%>">删除</a></td>
    </tr>
   <%
    }
   %>
   </table>
   </form>
   <div class="pagenumber">当前第 <%=request.getAttribute("page")%> 页<br/>
<br />
<a href="admin?action=topostmanagepage&flag=up&page=<%=request.getAttribute("page") %>">上一页</a>
<a href="admin?action=topostmanagepage&flag=down&page=<%=request.getAttribute("page") %>">下一页</a>
</div>
<jsp:include page="/jsp/template/foot.jsp" ></jsp:include>