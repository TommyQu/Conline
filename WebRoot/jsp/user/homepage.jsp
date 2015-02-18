<%@page import="domain.CoUserInfo"%>
 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8" xml:lang="UTF-8">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<link rel="stylesheet" type="text/css" href="css/global.css" />
	<link rel="stylesheet" type="text/css" href="css/homepage/jbar.css" />        
  	<link rel="stylesheet" href="css/homepage/jqumaiery-ui-1.9.2.custom.min.css" />
	<link rel="stylesheet" href="css/homepage/jquery-ui-1.9.2.custom.css" />
    <script type="text/javascript" src="js/homepage/jquery-1.8.1.js"></script>
  	<script type="text/javascript" src="js/homepage/global.js"></script>
    <script type="text/javascript" src="js/homepage/jquery-ui.js"></script>
    <script type="text/javascript" src="js/homepage/jbar.js"></script>
    <title>
    	Home Page
    </title>
</head>
<body>
<div id="logo"><img src="img/background.png" alt="C Online" /></div>
<div id="nevigator">
	<ul class="jbar">
		<li><a href="user?action=homepage">网站首页</a>
		</li>
		<li><a href="#">自主学习</a>
			<ul>
				<li><a href="study?action=tocoursepage&page=0">课程</a></li>
				<li><a href="study?action=tohomeworkpage&page=0">作业</a></li>
				<li><a href="study?action=totestpage&page=0">考试</a></li>
			</ul>
		</li>
		<li><a href="#">论坛模块</a>
			<ul>
				<li><a href="user?action=todiscussionarea&page=0">讨论区</a></li>
				<li><a href="user?action=tofreearea&page=0">灌水区</a></li>
				<li><a href="user?action=todownloadarea&page=0">下载区</a></li>
			</ul>
		</li>
		<li><a href="#">天梯模块</a>
			<ul>
				<li><a href="user?action=tonewchallenge">新的挑战</a></li>
				<li><a href="user?action=tomemory">旧的回忆</a></li>
				<li><a href="user?action=toranking">排名系统</a></li>
			</ul>
		</li>
		<li><a href="#">个人管理</a>
			<ul>
				<li><a href="pmanage?action=tomodifypage">修改个人信息</a></li>
				<li><a href="pmanage?action=tologsearch&page=0">查看操作日志</a></li>

			</ul>
		</li>
		<li><a href="user?action=tomoreinformation">更多信息</a>
		</li>
		<li><a href="user?action=logout">退出登录</a>
		</li>
	</ul>
</div>
<table>
<tr>
<td>
<div class="homepage">
<%
  CoUserInfo cui=new CoUserInfo();
  cui=(CoUserInfo)session.getAttribute("user");
 %>
<span align="right">欢迎你,<%=cui.getName()%></span>
<br />
<br />
<span>
C语言是1972年由美国的Dennis Ritchie设计发明的，并首
  c语言宣传图[1]
次在UNIX操作系统的DEC PDP-11计算机上使用。它由早期的编程语言BCPL(Basic Combined Programming Language)发展演变而来，在1970年,AT&T贝尔实验室的Ken Thompson根据BCPL语言设计出较先进的并取名为B的语言,最后导致了C语言的问世。 而B语言之前还有A语言，取名自世界上第一位女程序员Ada（艾达）。
随着微型计算机的日益普及,出现了许多C语言版本。由于没有统一的标准, 使得这些C语言之间出现了一些不一致的地方。为了改变这种情况,美国国家标准研究所(ANSI)为C语言制定了一套ANSI标准，成为现行的C语言标准。
注：国际标准化组织ISO也制定的C语言的标准，被很多编译器所采用，如：GCC等。
C语言是世界上最流行、使用最广泛的高级程序设计语言之一。[2]
在操作系统和系统使用程序以及需要对硬件进行操作的场合，用C语言明显优于其它高级语言，许多大型应用软件都是用C语言编写的。
C语言绘图能力强，具有可移植性，并具备很强的数据处理能力，因此适于编写系统软件，三维，二维图形和动画。它是数值计算的高级语言。
常用的编译软件有Microsoft Visual C++,Borland C++,gcc(linux系统下最常用的编译器),Watcom C++ ,Borland C++, Borland C++ Builder,Borland C++ 3.1 for DOS,Watcom C++ 11.0 for DOS,GNUDJGPP C++, Lccwin32 C Compiler 3.1,Microsoft C,High C等。[3]
同时也是中国国家计算机等级考试中计算机二级考试下的一个考试科目。[4]
</span>
<jsp:include page="/jsp/template/foot.jsp"></jsp:include>