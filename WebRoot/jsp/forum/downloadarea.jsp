<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.VCoForumTopicList"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/head.jsp" >
	<jsp:param name="application-title" value="DownloadArea" />
	<jsp:param name="application-left-jsp" value="/jsp/forum/menu.jsp"/>
</jsp:include>
<link type="text/css" href="css/forum/discussionarea.css" rel="stylesheet" />
<%
List<VCoForumTopicList> lvcftl=(List<VCoForumTopicList>)request.getAttribute("lvcftl");
int i=0;
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
<div id="forum">
<table>
  <% 
  for(i=0;i<lvcftl.size();i++)
  {
  %>
  <tr>
    <td id="getreplycount" rowspan="2"><%=lvcftl.get(i).getReplyCount()%>/<%=lvcftl.get(i).getAccessCount() %></td>
    <td id="gettopic"><a href="post?action=download&topicId=<%=lvcftl.get(i).getId()%>" /><%=lvcftl.get(i).getTopic()%></td>
    <input type="hidden" name="topic" value="<%=lvcftl.get(i).getTopic()%>" />
    <td id="getusername" rowspan="2"><img src="img/index/user.png" /><%=lvcftl.get(i).getUserName()%></td>
    <td id="getcreatetime"><img src="img/index/create.png" /> <%=df.format(lvcftl.get(i).getCreateTime())%></td>
    </tr>
    <tr>
    <td ><div id="getcontent"><%=lvcftl.get(i).getContent() %></div></td>
    <td id="getlastrelpytime"><img src="img/index/reply.png" /> <%=df.format(lvcftl.get(i).getLastReplyTime())%></td>
  </tr>
  <%
  }
   %>
</table>
<div class="pagenumber">当前第 <%=request.getAttribute("page")%> 页<br />
<a href="user?action=todiscussionarea&flag=up&page=<%=request.getAttribute("page") %>">上一页</a>
<a href="user?action=todiscussionarea&flag=down&page=<%=request.getAttribute("page") %>">下一页</a>
</div> 
</div>
<jsp:include page="/jsp/template/foot.jsp"></jsp:include>