<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.VCoForumTopicList"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/head.jsp">
	<jsp:param name="application-title" value="SearchPost" />
	<jsp:param name="application-left-jsp" value="/jsp/forum/menu.jsp" />
</jsp:include>
<link type="text/css" href="css/forum/searchpost.css" rel="stylesheet">
<script type="text/javascript" src="js/forum/searchpost.js"></script>
<%
	List<VCoForumTopicList> lvcftl = (List<VCoForumTopicList>) request
			.getAttribute("lvcftl");
	int i = 0;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<%
	String keyword = (String) request.getAttribute("keyword");
	if (keyword.equals("discussion"))
		keyword = "讨论区";
	else if (keyword.equals("free"))
		keyword = "灌水区";
	else
		keyword = "下载区";
%>
<div id="searchpost">
	<table>
		<tr id="searchline">
			<form action="post?action=searchpost&flag=0" method="post">
			<td></td>
			<td>在<%=keyword%>中查询，<span style="font-weight:bold">关键词</span>为：
				<input type="text" name="searchcontent"> 
				<input type="hidden" name="page" value="0">
				<input type="hidden" name="keyword"
				value="<%=request.getAttribute("keyword")%>">
				</td>
			<td><select id="select" name="select"
				onChange="redirec(document.frm.s1.options.selectedIndex)">
					<option selected>请选择查询方式</option>
					<option value="namesearch">按用户名查询</option>
					<option value="topicsearch">按主题查询</option>
					<option value="contentsearch">按内容查询</option>
			</select></td>
			<td><input id="submit" type="submit" value="查询"></td>
			</form>
		</tr>
		<tr></tr>
		<%
			for (i = 0; i < lvcftl.size(); i++) {
		%>
		<tr>
			<td id="getreplycount" rowspan="2"><%=lvcftl.get(i).getReplyCount()%>/<%=lvcftl.get(i).getAccessCount()%></td>
			<td id="gettopic"><a
				href="post?action=getonepost&topicId=<%=lvcftl.get(i).getId()%>" /><%=lvcftl.get(i).getTopic()%></td>
			<input type="hidden" name="topic"
				value="<%=lvcftl.get(i).getTopic()%>" />
			<td id="getusername">发帖人：<%=lvcftl.get(i).getUserName()%></td>
			<td id="getcreatetime">创建时间:<%=df.format(lvcftl.get(i).getCreateTime())%></td>
		</tr>
		<tr>
			<td id="getcontent" colspan="2"><%=lvcftl.get(i).getContent()%></td>
			<td id="getlastrelpytime">最新动态:<%=df.format(lvcftl.get(i).getLastReplyTime())%></td>
		</tr>
		<%
			}
		%>
	</table>
	<div class="pagenumber">当前第 <%=request.getAttribute("page")%> 页  <br /><br />
	<a href="user?action=tosearchpostpage&keyword=<%=request.getAttribute("keyword")%>&flag=up&page=<%=request.getAttribute("page")%>">上一页</a>
	<a href="user?action=tosearchpostpage&keyword=<%=request.getAttribute("keyword")%>&flag=down&page=<%=request.getAttribute("page")%>">下一页</a>
	</div>
	</div>
<jsp:include page="/jsp/template/foot.jsp"></jsp:include>