
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8" xml:lang="UTF-8">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/homepage/jbar.css" />
<link rel="stylesheet"
	href="css/homepage/jqumaiery-ui-1.9.2.custom.min.css" />
<link rel="stylesheet" href="css/homepage/jquery-ui-1.9.2.custom.css" />
<script type="text/javascript" src="js/homepage/jquery-1.8.1.js"></script>
<script type="text/javascript" src="js/homepage/global.js"></script>
<script type="text/javascript" src="js/homepage/jquery-ui.js"></script>
<script type="text/javascript" src="js/homepage/jbar.js"></script>
<title><%=request.getParameter("application-title")%></title>
</head>
<body>
	<div id="logo">
		<img src="img/background.png" alt="C Online" />
	</div>
	<div id="nevigator">
		<ul class="jbar">
			<li><a href="user?action=homepage">网站首页</a></li>
			<li><a href="#">自主学习</a>
				<ul>
					<li><a href="study?action=tocoursepage&page=0">课程</a>
					</li>
					<li><a href="study?action=tohomeworkpage&page=0">作业</a>
					</li>
					<li><a href="study?action=totestpage&page=0">考试</a>
					</li>
				</ul></li>
			<li><a href="#">论坛模块</a>
				<ul>
					<li><a href="user?action=todiscussionarea&page=0">讨论区</a>
					</li>
					<li><a href="user?action=tofreearea&page=0">灌水区</a>
					</li>
					<li><a href="user?action=todownloadarea&page=0">下载区</a>
					</li>
				</ul></li>
			<li><a href="#">天梯模块</a>
				<ul>
					<li><a href="user?action=tonewchallenge">新的挑战</a>
					</li>
					<li><a href="user?action=tomemory">旧的回忆</a>
					</li>
					<li><a href="user?action=toranking">排名系统</a>
					</li>
				</ul></li>
			<li><a href="#">个人管理</a>
				<ul>
				    <li><a href="pmanage?action=tohomeworksearchpage&page=0">查看作业状态</a>
					</li>
					<li><a href="pmanage?action=tomodifypage">修改个人信息</a>
					</li>
					<li><a href="pmanage?action=tologsearch&page=0">查看操作日志</a>
					</li>
				</ul></li>
			<li><a href="user?action=tomoreinformation">更多信息</a></li>
			<li><a href="user?action=logout">退出登录</a></li>
		</ul>
	</div>
	<table id="outside">
		<tr>
			<td>
				<div class="left">
					<%
						String tmpUrl = request.getParameter("application-left-jsp");
					%>
					<jsp:include page="<%=tmpUrl %>"></jsp:include>

				</div>
			</td>
			<td>
				<div class="right">