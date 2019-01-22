// JavaScript Document
// 提交表单

function loginSubmitForm() {
    var form = document.getElementById('login');
    var sid = document.getElementById('sid').value;
    var password = document.getElementById('password').value;
        if (sid == '' || password == '') {
            layer.alert("账号和密码不能为空！", {icon: 6});
        } else if (isNaN(sid)) {
            layer.alert("账号只能由数字组成！", {icon: 6});
        } else {
            form.submit();
        }

}


//重置表单
function doClearForm(){
	var form = document.getElementById('login');
	form.reset();
}

function getContentSize() {
    if(document.documentElement.scrollWidth > 1100){
        var wh = (document.documentElement.scrollHeight - 400)/2;
        ch = wh + "px";
        document.getElementById( "login" ).style.marginTop = ch;
    }
}
window.onload = getContentSize;
window.onresize = getContentSize;