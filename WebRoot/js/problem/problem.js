/**
	@ author : ghnjk
	@ date:		2013-06
	@ description: 显示和操作题目
*/


/**页面加载*/
$(function($){
	bindEvents();
});

function bindEvents(){

};

function SubmitProblemButton(div_id,problem,chapterId,problemId,userId,url){
	var div=$("#"+div_id);
	var p=problem;
	var cid=chapterId;
	var pid=problemId;
	var uid=userId;
	var href=url;
	var btn=$("<input>",{
		"type":"button",
		"value":"提交",
		"click":function(){
			var doc=$("<root>");
			doc.append(
				problem.getSubmit(uid)
			);
			var i=0;
			var str=doc.html();
			str=str.replace("<submit",'<?xml version="1.0"  encoding="utf-8" ?>\r\n<submit xmlns="http://ghnjksw.oicp.net:8088/Conline/xml/submit.xsd"\r\n				    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"\r\n				    xsi:schemaLocation="xml/\r\n				                        submit.xsd"');
			//str=str.replace(/\\conline-special-tab\\/g,"\t");
			str=str.replace(/conline-usetime--spceical/g,"useTime");
			str=str.replace(/conline-submittime--spceical/g,"submitTime");
			str=str.replace(/&amp;/g,"&");
			str=str.replace(/&lt;/g,"<");
			str=str.replace(/&gt;/g,">");
			str=str.replace(/<pre>/g,"<![CDATA[");
			str=str.replace(/<\/pre>/g," ]]>");
//$("#ip").val(str);return ;
			$.post(
				href,
				{
					"pid":pid,
					"chapterId":cid,
					"userId":uid,
					"content":str
				},
				function(data){
					alert(data);
				}
			);
		}		
	});
	btn.css("float","right");
	btn.appendTo(div);
}

function HtmlEncoding(str){
	str=str.replace(/\t/g,"    ");
	str=str.replace(/&/g,"&amp;");
	str=str.replace(/ /g,"&nbsp;");
	str=str.replace(/</g,"&lt;");
	str=str.replace(/>/g,"&gt;");
	str=str.replace(/\n/g,"<br />");
	return str;
}

/**显示problem类*/
function ProblemShow(){
	var div;
	var div_title;
	var div_content;
	var problem;/**xml document*/
	//submit related
	var sub_eid;
	var sub_useTime=0;
	var sub_index=0;	/**在试卷中的题号*/

	var jsProblem;/**js class object*/
	
	this.init=function(id,
			eid,index){
		div=$("#"+id);
		div.attr("class","show-problem-div");
		div.html("<div class='title'></div><div class='content'></div>");
		div_title=div.find(".title");
		div_content=div.find(".content");
		div_title.text("题目列表");
		sub_eid=eid;
		sub_index=index;
	};
	this.showProblem=function(){
		
	};
	this.loadProblem=function(xmlUrl){
		div_title.text("加载中....");
		div_content.html("");
		$.ajax({
	        url: xmlUrl,
	        dataType: 'xml',
	        type: 'GET',
	        timeout: 5000,
	        error: function(xml)
	        {
	            alert("加载XML 文件出错！");
	        },
	        success: function(xml)
	        {
	        	problem=$(xml).find("problem");
	        	div_title.text(
	        			sub_index+",\t"+problem.attr("type")
	        			);
	        	div_content.html("<br /><hr /><br />");
	        	var type=problem.attr("type");
	        	if(type == "单选题")
	        		jsProblem=new ChoiceProblem();
	        	else if(type=="多选题")
	        		jsProblem=new MultiChoiceProblem();
	        	else if(type=="填空题")
	        		jsProblem=new FillInBlankProblem();
	        	else if(type=="程序填空题")
	        		jsProblem=new ProgramFillInBlankProblem();
	        	else if(type=="程序题")
	        		jsProblem=new ProgramProblem();
	        	jsProblem.init(problem, sub_index);
	        	jsProblem.getDiv().appendTo(div_content);
	        }
	    });
	};
	
	
	this.getSubmit=function(userId){
		
		var $submit=$("<submit>")
		$submit.attr("uid",userId)
			.attr("eid",sub_eid)
			.attr("conline-submittime--spceical",new Date().toLocaleString());
		
		var ele=jsProblem.getProblemSubmit(sub_useTime);
		$submit.append(ele);
		return $submit;
	}
};

//////////////////////////////////////////////////////////////
/**
 * 单选题
 * @returns {ChoiceProblem}
 */
function ChoiceProblem(){
/**共有接口*/	
	var div;
	/**获取答案XML节点*/
	this.getProblemSubmit=function(useTime){
		var ele=$("<problem-submit>")
			.attr("pid",problem.attr("eid"))
			.attr("index",index-1)
			.attr("conline-usetime--spceical",useTime);	
		var ans=0;
		var i;
		for( i=0;i<arrOption.length;i++){
			if($(arrOption[i]).attr("checked")=="checked"){
				ans=i;
				break;
			}
		}
		var op=$("<option>").text(ans);
		ele.append(op);
		return ele;
	}
	
	
	this.getDiv=function(){
		return div;
	}
	this.init=function(p,idx){
		problem=p;		
		index=idx;
		div=$("<div>",{
			"class":"div_choice",
			"id":"div_choice_"+index
		});
		div_problem=$("<p>",{
			"class":"p_problem",
			"id":"p_problem_"+index,
			"html":problem.find("question").text()
		}).appendTo(div);
		$("<br>").appendTo(div);		
		problem.find("option").each(function(i){
			arrOption[i]=$("<input>",{
				"type":"radio",
				"name":"choice_"+index,
				"id":"option_"+index+"_"+i,
				"value":i
			});
			arrOption[i].appendTo(div);
			$("<label>",{
				"id":"label_op_"+index+"_"+i,
				"for":"option_"+index+"_"+i,
				"html":String.fromCharCode(65+i)+")\t"+$(this).text()
			}).appendTo(div);
			$("<br />").appendTo(div);
		});
		this.bindEvents();
	};

	/**
	 * 绑定事件
	 */
	this.bindEvents=function(){		
		/**修改当选题的选中事件**/
		div.children("input[type='radio']").bind("change", function(event) {
			  // 这里可以写些验证代码
			div.children("input[type='radio']").each(function(i){
				if($(this).attr("checked")=="checked"){
					$(this).css("background","#CC99AA");
					div.children("#label_op_"+index+"_"+i).css("background","#CC99AA");
				}
				else{
					$(this).css("background","");
					div.children("#label_op_"+index+"_"+i).css("background","");
				}
			});
			event.stopPropagation();
		});		
	};
		
	

/**私有接口*/	
	var div_problem;
	var index;
	var problem;
	var arrOption=new Array();
		
};
////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
/**
 * 多选题
 * @returns {ChoiceProblem}
 */
function MultiChoiceProblem(){
/**共有接口*/	
	var div;
	/**获取答案XML节点*/
	this.getProblemSubmit=function(useTime){
		var ele=$("<problem-submit>")
			.attr("pid",problem.attr("eid"))
			.attr("index",index-1)
			.attr("conline-usetime--spceical",useTime);	
		var i;
		for( i=0;i<arrOption.length;i++){
			var str="false";
			if($(arrOption[i]).attr("checked")=="checked"){
				str="true";
			}
			ele.append($("<check>").text(str));
		}
		return ele;
	}
	
	
	this.getDiv=function(){
		return div;
	}
	this.init=function(p,idx){
		problem=p;		
		index=idx;
		div=$("<div>",{
			"class":"div_multi_choice",
			"id":"div_multi_choice_"+index
		});
		div_problem=$("<p>",{
			"class":"p_problem",
			"id":"p_problem_"+index,
			"html":problem.find("question").text()
		}).appendTo(div);
		$("<br>").appendTo(div);		
		problem.find("option").each(function(i){
			arrOption[i]=$("<input>",{
				"type":"checkbox",
				"name":"choice_"+index,
				"id":"option_"+index+"_"+i,
				"value":i
			});
			arrOption[i].appendTo(div);
			$("<label>",{
				"id":"label_op_"+index+"_"+i,
				"for":"option_"+index+"_"+i,
				"html":String.fromCharCode(65+i)+")\t"+$(this).text()
			}).appendTo(div);
			$("<br />").appendTo(div);
		});
		this.bindEvents();
	};

	/**
	 * 绑定事件
	 */
	this.bindEvents=function(){		
		/**修改当选题的选中事件**/
		div.children("input[type='checkbox']").bind("change", function(event) {
			  // 这里可以写些验证代码
			div.children("input[type='checkbox']").each(function(i){
				if($(this).attr("checked")=="checked"){
					$(this).css("background","#CC99AA");
					div.children("#label_op_"+index+"_"+i).css("background","#CC99AA");
				}
				else{
					$(this).css("background","");
					div.children("#label_op_"+index+"_"+i).css("background","");
				}
			});
			event.stopPropagation();
		});		
	};
		
	

/**私有接口*/	
	var div_problem;
	var problem;
	var index;
	var arrOption=new Array();
		
};
////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
/**
 * 填空题
 * @returns {ChoiceProblem}
 */
function FillInBlankProblem(){
/**共有接口*/	
	var div;
	this.getProblemSubmit=function(useTime){
		var ele=$("<problem-submit>")
			.attr("pid",problem.attr("eid"))
			.attr("index",index-1)
			.attr("conline-usetime--spceical",useTime);	
		var i;	
		for( i=0;i<arrText.length;i++){
			var str=arrText[i].val();
			//str=str.replace(/\t/g,"\\conline-special-tab\\");
			ele.append($("<text>").append($("<pre>").text(str)));
		}	
		return ele;
	}
	
	this.getDiv=function(){
		return div;
	}
	this.init=function(p,idx){
		problem=p;		
		index=idx;
		div=$("<div>",{
			"class":"div_fill_blank",
			"id":"div_fill_blacke_"+index
		});
		div_problem=$("<p>",{
			"class":"p_problem",
			"id":"p_problem_"+index			
		}).appendTo(div);

		var input_i=0;
		problem.find("question").children().each(function(i){
			if( $(this).is("text") ){
				$("<span>",{
					"html":$(this).text()
				}).appendTo(div);
			}
			else if( $(this).is("input") ){
				
				arrText[input_i]=$("<input>",{
					"type":"text",
					"class":"input_text",
					"id":"input_text_"+index+"_"+input_i,
					"size":$(this).attr("text-size"),
					"value":$(this).find("show-text").text()
				});
				arrText[input_i].attr("size",$(this).attr("text-size"));
				arrText[input_i].css("background","#DEDEDE");
				arrText[input_i].appendTo(div);
				input_i++;
			}
		});
		
		
	};

		
	

/**私有接口*/	
	var div_problem;
	var problem;
	var index;
	var arrText=new Array();
		
};
////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
/**
 * 程序填空题
 * @returns {ChoiceProblem}
 */
function ProgramFillInBlankProblem(){
/**共有接口*/	
	var div;
	this.getProblemSubmit=function(useTime){
		var ele=$("<problem-submit>")
			.attr("pid",problem.attr("eid"))
			.attr("index",index-1)
			.attr("conline-usetime--spceical",useTime);	
		var i;	
		for( i=0;i<arrText.length;i++){
			var str=arrText[i].val();
			//str=str.replace(/\t/g,"\\conline-special-tab\\");
			ele.append($("<text>").append($("<pre>").text(str)));
		}
		return ele;
	}	
	
	
	this.getDiv=function(){
		return div;
	}
	this.init=function(p,idx){
		problem=p;		
		index=idx;
		div=$("<div>",{
			"class":"div_fill_blank",
			"id":"div_fill_blacke_"+index
		});
		div_problem=$("<p>",{
			"class":"p_problem",
			"id":"p_problem_"+index,
			"html":problem.find("question").text()
		}).appendTo(div);
		var div_p=$("<div>",{
			"class":"p_program",
			"id":"p_program_"+index
		}).appendTo(div);
		div_p.css("background","#CCCCCC");
		var input_i=0;
		problem.find("program").children().each(function(i){
			if( $(this).is("code") ){
				var str=$(this).text();
				str=HtmlEncoding(str);
				var tp=$("<span>",{
					"html":str
				}).appendTo(div_p);
				//tp.css("display","inline-block");
			}
			else if( $(this).is("input") ){				
				var tp=arrText[input_i]=$("<input>",{
					"type":"text",
					"class":"input_text",
					"id":"input_text_"+index+"_"+input_i,
					"size":$(this).attr("text-size"),
					"value":$(this).find("show-text").text()
				});
				arrText[input_i].attr("size",$(this).attr("text-size"));
				arrText[input_i].css("background","#DEDEDE");
				arrText[input_i].appendTo(div_p);
			//	tp.css("display","inline-block");
				input_i++;
			}
		});
		
		
	};

		
	

/**私有接口*/	
	var div_problem;
	var problem;
	var index;
	var arrText=new Array();
		
};
////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
/**
 * 程序题目
 * @returns {ChoiceProblem}
 */
function ProgramProblem(){
/**共有接口*/	
	var div;
	this.getProblemSubmit=function(useTime){
		var ele=$("<problem-submit>")
			.attr("pid",problem.attr("eid"))
			.attr("index",index-1)
			.attr("conline-usetime--spceical",useTime);	
		var str=textarea.val();		
		//str=str.replace(/\t/g,"\\conline-special-tab\\");
		ele.append($("<text>").append($("<pre>").text(str)));
		return ele;
	}	
	this.getDiv=function(){
		return div;
	}
	this.init=function(p,idx){
		problem=p;		
		index=idx;
		div=$("<div>",{
			"class":"div_program",
			"id":"div_program_"+index
		});
		div_problem=$("<p>",{
			"class":"p_problem",
			"id":"p_problem_"+index,
			"html":problem.find("question").text()
		}).appendTo(div);
		var div_p=$("<div>",{
			"class":"p_program",
			"id":"p_program_"+index
		}).appendTo(div);
		div_p.css("background","#CCCCCC");
		textarea=$("<textarea>",{
			"class":"program_textarea",
			"id":"program_textarea_"+index,
			"cols":60,
			"rows":20
		});
		textarea.css("background","#CCCCCC");
		textarea.appendTo(div_p);
		
		
	};

		
	

/**私有接口*/	
	var div_problem;
	var problem;
	var index;
	var textarea;
		
};
////////////////////////////////////////////////////////////////////
