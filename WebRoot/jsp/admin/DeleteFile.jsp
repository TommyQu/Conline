<%@page import="domain.CoFileInfo"%>
<%@page import="domain.VCoForumTopicList"%>
<%@page import="domain.CoForumTopic"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.CoUserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/admin/adminpage.jsp" ></jsp:include>

<%
  List<CoFileInfo> lcfi=(List<CoFileInfo>)request.getAttribute("lcfi");
  int i=0;
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
  <form>
  <table>
  <tr><td colspan="5" class="title">删除资料</td></tr>
    <tr class="column">
      <td>文件名</td>
      <td>文件大小</td>
      <td>下载量</td>
      <td>上传时间</td>
    </tr>
  <%
    for(i=0;i<lcfi.size();i++)
    {
   %>
   <tr>
      <td><%=lcfi.get(i).getFileName() %></td>
      <td><%=lcfi.get(i).getFileSize() %></td>
      <td><%=lcfi.get(i).getDownCount() %></td>
      <td><%=df.format(lcfi.get(i).getCreateTime()) %></td>
      <td><a href="admin?action=deletefile&id=<%=lcfi.get(i).getId()%>">删除</a></td>
    </tr>
   <%
    }
   %>
   </table>
   </form>
   <div class="pagenumber">当前第 <%=request.getAttribute("page")%> 页<br/>
<br />
<a href="admin?action=todeletefilepage&flag=up&page=<%=request.getAttribute("page") %>">上一页</a>
<a href="admin?action=todeletefilepage&flag=down&page=<%=request.getAttribute("page") %>">下一页</a>
</div>
<jsp:include page="/jsp/template/foot.jsp" ></jsp:include>