<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/head.jsp" >
	<jsp:param name="application-title" value="Homework" />
	<jsp:param name="application-left-jsp" value="/jsp/study/menu.jsp"/>
</jsp:include>
<link type="text/css" href="css/study/testpage.css" rel="stylesheet" />
<link rel="stylesheet" href="css/problem/problem.css" />
<script type="text/javascript" src="js/problem/problem.js" ></script>
<script type="text/javascript" src="js/study/testpage.js" ></script>
<div class="outside">
<div class="testtitle"><a href="#1">第一单元</a></div>
	<div class="testsection"><ul>
	<li><a href="#1">第一题</a></li>
	<li><a href="#2">第二题</a></li>
	<li><a href="#3">第三题</a></li>
	<li><a href="#4">第四题</a></li>
	<li><a href="#5">第五题</a></li>
	</ul>
	</div>
	<div class="testtitle"><a href="#1">第二单元</a></div>
	<div class="testsection"><ul>
	<li><a href="#6">第一题</a></li>
	<li><a href="#7">第二题</a></li>
	<li><a href="#8">第三题</a></li>
	<li><a href="#9">第四题</a></li>
	<li><a href="#10">第五题</a></li>
	</ul>
	</div>
	<div class="testtitle"><a href="#1">第三单元</a></div>
	<div class="testsection"><ul>
	<li><a href="#11">第一题</a></li>
	<li><a href="#12">第二题</a></li>
	<li><a href="#13">第三题</a></li>
	<li><a href="#14">第四题</a></li>
	<li><a href="#15">第五题</a></li>
	</ul>
	</div>
	</div>
	<div id="studyarea"></div>
<jsp:include page="/jsp/template/foot.jsp"></jsp:include>