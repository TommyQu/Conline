<%@page import="domain.CoUserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	CoUserInfo cui = (CoUserInfo) session.getAttribute("user");
%>
	<link rel="stylesheet" type="text/css" href="css/homepage/jbar.css" />        
  	<link rel="stylesheet" href="css/homepage/jqumaiery-ui-1.9.2.custom.min.css" />
	<link rel="stylesheet" href="css/homepage/jquery-ui-1.9.2.custom.css" />
    <script type="text/javascript" src="js/homepage/jquery-1.8.1.js"></script>
  	<script type="text/javascript" src="js/homepage/global.js"></script>
    <script type="text/javascript" src="js/homepage/jquery-ui.js"></script>
    <script type="text/javascript" src="js/homepage/jbar.js"></script>
	<link rel="stylesheet" href="css/admin/adminpage.css" />
    <script type="text/javascript" src="js/admin/adminpage.js"></script>
<html>
<head>
<title>Admin Page</title>
</head>
<body>
<table>
<tr>
<td>
	<div class="left">
		<div id="manager"><a>管理员：<%=cui.getName()%></a></div><hr/>
		<ul>
			<li><a href="admin?action=tousermanagepage&page=0">用户管理</a></li>
			<li><a href="#">论坛管理</a>
				<ul>
					<li><a href="admin?action=topostmanagepage&page=0">删除帖子 </a></li>
					<li><a href="admin?action=toforumlogpage&page=0">论坛日志 </a></li>
				</ul>
			</li>
			<li><a href="#">资料管理</a>
				<ul>
					<li><a href="admin?action=touploadfilepage&page=0">上传资料 </a></li>
					<li><a href="admin?action=todeletefilepage&page=0">删除资料 </a></li>
				</ul>
			</li>
			<li><a href="#">题目管理</a>
				<ul>
					<li><a href="admin?action=touploadproblempage">上传题目</a></li>
					<li><a href="admin?action=todeleteproblempage&page=0">删除题目</a></li>
				</ul>
			</li>
			<li><a href="#">邮件系统</a>
				<ul>
					<li><a href="admin?action=tonotifypage">邮件通知</a></li>
				</ul>
			</li>
			<li><a href="admin?action=judgeclient&page=0">评测机状态</a></li>
			<li><a href="admin?action=logout">退出</a></li>
		</ul>
	</div>
	</td>
	<td>
	<div class="right">
