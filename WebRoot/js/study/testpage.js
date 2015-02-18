$(function($){
	$(".testsection").hide();
	$(" table tr td").css("vertical-align","top");
	$(".testtitle>a").click(function(){
		$(this).parent().siblings(".testsection").css("display","none");
		if($(this).parent().next().css("display")=="none") 
			$(this).parent().next().show(800);
	    else 
		$(this).parent().next().hide(800);
	});
	$(".testsection>ul>li>a").click(function(){
		var s=$(this).parent().parent().parent();
	if(($("#studyarea").css("display")=="none")){
		alert($(this).attr("href"));
		$("#studyarea").insertAfter(s).end();
		//操作路径
		{
			var show1=new ProblemShow();
			show1.init("studyarea",1,1);
			//init(div_id,pid,index);
			show1.loadProblem("test.xml");
			var btn=new SubmitProblemButton("studyarea",show1,1,3,1,"study?action=submithomework");
			//(div_id,problem_object,chapterId,pid,0,"study?action=submithomework")
		}
		
		$("#studyarea").show(800);
	}
	else {
		$("#studyarea").hide(800);
	}});
});