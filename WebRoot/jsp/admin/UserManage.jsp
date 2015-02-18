<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.CoUserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/admin/adminpage.jsp" ></jsp:include>
<%
  List<CoUserInfo> lcui=(List<CoUserInfo>)request.getAttribute("lcui");
  int i=0;
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
  <form>
  <table>
  <tr><td colspan="5" class="title">用户管理</td></tr>
    <tr class="column">
      <td>用户名</td>
      <td>性别</td>
      <td>邮箱</td>
      <td>创建日期</td>
      <td>操作</td>
    </tr>
  <%
    for(i=0;i<lcui.size();i++)
    {
   %>
   <tr>
      <td><%=lcui.get(i).getName() %></td>
      <td><%=lcui.get(i).getSex() %></td>
      <td><%=lcui.get(i).getEmail() %></td>
      <td><%=df.format(lcui.get(i).getCreatTime()) %></td>
      <td><a href="admin?action=usermanage&id=<%=lcui.get(i).getId()%>">删除</a></td>
    </tr>
   <%
    }
   %>
   </table>
   </form>
   <div class="pagenumber">当前第 <%=request.getAttribute("page")%> 页<br/><br/>
<a href="admin?action=tousermanagepage&flag=up&page=<%=request.getAttribute("page") %>">上一页</a>
<a href="admin?action=tousermanagepage&flag=down&page=<%=request.getAttribute("page") %>">下一页</a>
</div>
<jsp:include page="/jsp/template/foot.jsp" ></jsp:include>
