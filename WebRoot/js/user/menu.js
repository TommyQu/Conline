$(document).ready(function() {
	$("#nav").children("li:first").nextAll().children("ul").children("li").hide();
				});

$(function($) {
	$("#nav").children("li").children("a").click(function() {
		$(this).parent().children("ul").children("li").show();
		$(this).parent().siblings().children("ul").children("li").hide();
	});
});