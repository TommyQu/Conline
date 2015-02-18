$(function($){
	var j=0;
	var s=$("#outside tr").length;

	var n=$("#outside tr").first();
	var i=0;
	for(i;i<s;i++){
		if(j%2!=0){
			n.css("background","#EEEEEE");
			i++;
			n=n.next();
			n.css("background","#EEEEEE");
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