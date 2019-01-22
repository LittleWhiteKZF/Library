// JavaScript Document
function formSubmit(){
	var form = document.getElementById('book_search');
	form.submit();
}
function First(){
	var page = 1;
	location.href = "search.jsp?num="+page;
}
function End(pagenum){
	var num = pagenum;
	location.href = "search.jsp?num="+num;
}
function Last(pages){
	var page = pages;
	if(page<=1){
		layer.alert("当前首页！",{icon:6});
	}else{
		page--;
        location.href = "search.jsp?num="+page;
	}

}
function Next(pages,pagenum){
	var page = pages;
	var num = pagenum;
	if(page>=num){
		layer.alert("没有下一页了！",{icon:6});
	}else{
		page++;
        location.href = "search.jsp?num="+page;
	}

}
function Page(pages){
	var page = pages;
	location.href = "search.jsp?num="+page;
}