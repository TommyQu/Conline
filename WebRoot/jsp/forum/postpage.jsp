<%@page import="domain.VCoForumReplyList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.VCoForumTopicList"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/head.jsp">
	<jsp:param name="application-title" value="PostPage" />
	<jsp:param name="application-left-jsp" value="/jsp/forum/menu.jsp" />
</jsp:include>
<link type="text/css" href="css/forum/postage.css" rel="stylesheet" />
<script type="text/javascript" src="js/forum/dealwith.js"></script>
<%
	VCoForumTopicList vcftl = (VCoForumTopicList) request
			.getAttribute("vcftl");
	int i = 0;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	List<VCoForumReplyList> lvcfrl = (List<VCoForumReplyList>) request.getAttribute("lvcfrl");
%>
<div id="postage">
<table>
	<tr id="special">
		<td class="gettopic" colspan="2">标题：<%=vcftl.getTopic()%></td>
	</tr>
	<tr>
		<td class="getusername" rowspan="2"><span>楼主</span><br/><img src="img/index/user.png" /> <%=vcftl.getUserName()%></td>
		<td class="getcontent"><%=vcftl.getContent()%></td>
	</tr>
	<tr>
		<td class="getcreatetime"><img src="img/index/create.png" /><%=df.format(vcftl.getCreateTime())%></td>
	</tr>
	<%
		for (i = 0; i < lvcfrl.size(); i++) {
	%>
	<tr>

	</tr>
	<tr>
		<td class="getusername" rowspan="2"><span><%=i + 1%>楼</span><br/><img src="img/index/user.png" /> <%=lvcfrl.get(i).getUserName()%></td>
		<td class="getcontent"><%=lvcfrl.get(i).getContent()%></td>
	</tr>
	<tr>
		<td class="getcreatetime"><img src="img/index/reply.png" /><%=df.format(lvcfrl.get(i).getCreateTime())%></td>
	</tr>
	<%
		}
	%>
</table>
<form action="post?action=reply&topicId=<%=vcftl.getId()%>" method="post">
	<table>
		<tr>
			<td id="reply">发表回复</td>
			<td id="replycontent"><textarea type="text" name="content" ></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2"><input id="submit" type="submit" name="确认" value="确认"/></td>
		</tr>
	</table>
</form>
</div>
<jsp:include page="/jsp/template/foot.jsp"></jsp:include>