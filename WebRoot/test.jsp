<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/head.jsp" >
	<jsp:param name="application-title" value="ddd" />
	<jsp:param name="application-left-jsp" value="/jsp/study/menu.jsp"/>
</jsp:include>

<link rel="stylesheet" href="css/problem/problem.css" />
<script type="text/javascript" src="js/problem/problem.js" >
</script>

<input type="button" onclick="doShow();" value="提交"/>

<br /><br />
<div id="p1">

</div>
<script type="text/javascript">
	var show1=new ProblemShow();
	show1.init("p1",1,1);
	//init(div_id,pid,index);
	show1.loadProblem("test.xml");
	var btn=new SubmitProblemButton("p1",show1,1,3,1,"study?action=submithomework");
	//(div_id,problem_object,chapterId,pid,0,"study?action=submithomework")
</script>

<div id="p2">

</div>
<script type="text/javascript">
	var show2=new ProblemShow();
	show2.init("p2",2,2);
	show2.loadProblem("test2.xml");
	var btn=new SubmitProblemButton("p2",show2,1,4,1,"study?action=submithomework");
</script>
<div id="p3">

</div>
<script type="text/javascript">
	var show3=new ProblemShow();
	show3.init("p3",3,3);
	show3.loadProblem("test3.xml");
	var btn=new SubmitProblemButton("p3",show3,1,5,1,"study?action=submithomework");	
</script>
<div id="p4">

</div>
<script type="text/javascript">
	var show4=new ProblemShow();
	show4.init("p4",4,4);
	show4.loadProblem("test4.xml");
	var btn=new SubmitProblemButton("p4",show4,1,1,1,"study?action=submithomework");	
</script>
<div id="p5">

</div>
<script type="text/javascript">
	var show5=new ProblemShow();
	show5.init("p5",5,5);
	show5.loadProblem("test5.xml");
	var btn=new SubmitProblemButton("p5",show5,1,2,1,"study?action=submithomework");	
</script>

<jsp:include page="/jsp/template/foot.jsp"></jsp:include>