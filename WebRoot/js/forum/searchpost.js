$(function($){
	var keyword;
	var type;
	$("submit").submit(function (){

	});
	
	var j=0;
	var s=$("table tr").length;
	var n=$("table tr").first();
	var i=0;
	for(i;i<s;i++){
		if(j%2!=0){
			n.css("background","#EEE");
			i++;
			n=n.next();
			n.css("background","#EEE");
			n=n.next();
		}
		else{
			n=n.next();
			i++;
			n=n.next();
			}
		j++;
		}
});
