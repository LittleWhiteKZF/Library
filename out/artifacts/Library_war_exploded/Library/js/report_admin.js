// JavaScript Document
function deduct(r,rid,userId){
	var x = document.getElementById('report_table').rows[r].cells;
	x[5].innerHTML="已扣分";
	x[6].innerHTML="";
	var url="minus.action";
	var param={reportId:rid,userId:userId};
    jQuery.post(url,param);

}

function ignore(r,rid){
	var x = document.getElementById('report_table').rows[r].cells;
	x[5].innerHTML="";
	x[6].innerHTML="已忽略";
    var url="ignore.action";
    var param={reportId:rid};
    jQuery.post(url,param);
}