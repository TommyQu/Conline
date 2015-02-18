$(function($){
	$("#mediaplayer").hide();
	$("table tr td").css("vertical-align","top");
	$("a[href='#1'],a[href='#2'],a[href='#3'],a[href='#4']").click(function(){
		var s=$(this).parent().parent().parent();
	if(($("#mediaplayer").css("display")=="none")){
		$("#jquery_jplayer_1").jPlayer({
			ready: function () {
				$(this).jPlayer("setMedia", {
					"webmv": "file/1.webm",
					"poster": "file/bg.png"
				});
			},
			swfPath: "js",
			supplied: "webmv, ogv, m4v",
			size: {
				width: "640px",
				height: "360px",
				cssClass: "jp-video-360p"
			},
			smoothPlayBar: true,
			keyEnabled: true
		});
		$("#mediaplayer").insertAfter(s).end();
		$("#mediaplayer").show(800);

	//操作路径
	}
	else {
		$("#mediaplayer").hide(800);
		/*if(s.next()==$("#mediaplayer")) $("#mediaplayer").hide(800);
		else {
			$("#mediaplayer").show(800);
		$("#jquery_jplayer_1").jPlayer({
			ready: function () {
				$(this).jPlayer("setMedia", {
					"webmv": "file/1.webm",
					"poster": "file/bg.png"
				});
			},
			swfPath: "js",
			supplied: "webmv, ogv, m4v",
			size: {
				width: "640px",
				height: "360px",
				cssClass: "jp-video-360p"
			},
			smoothPlayBar: true,
			keyEnabled: true
		});
		$("#mediaplayer").insertAfter(s).end();
		}
		*/
	}
	
	});
	$("a").click(function(){
		
	});
});

