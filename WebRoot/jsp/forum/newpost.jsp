<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/head.jsp" >
	<jsp:param name="application-title" value="NewPost" />
	<jsp:param name="application-left-jsp" value="/jsp/forum/menu.jsp"/>
</jsp:include>
<link type="text/css" href="css/forum/newpost.css" rel="stylesheet" />
<form action="post?action=newpost" method="post">
<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
<div id="forum">
<table>
  <tr>
    <td>标题</td>
    <td class="tdclass"><input id="title" type="text" name="topic" /></td>
  </tr>
    <tr>
    <td>内容</td>
    <td class="tdclass"><textarea id="content" name="content" /></textarea>
  </tr>
  <tr>
    <td colspan="2"><input id="submit" type="submit" name="submit" value="发布帖子" /></td>
  </tr>
</table>
</div>
</form>
<jsp:include page="/jsp/template/foot.jsp"></jsp:include>