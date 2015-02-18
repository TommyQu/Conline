$(function($){
	$(".left>ul>li>ul>li").hide();
	$("a").click(function(){
		if($(this).parent().children("ul").children("li").is(":hidden")) {
			$(this).parent().children("ul").children("li").show("slow");
		}
		else{
			$(this).parent().children("ul").children("li").hide("slow");
		}
		$(this).parent().siblings().children("ul").children("li").hide("slow");
		});
});