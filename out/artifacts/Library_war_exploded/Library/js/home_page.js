// JavaScript Document
//弹出修改密码页面
function popChangePasswordBox() {
    var mask = document.getElementById('black_mask');
    var change_password_container = document.getElementById('change_password_container');
    mask.style.display = 'block';
    change_password_container.style.display = 'block';
}

//关闭修密码改页面
function closeWindow() {
    var mask = document.getElementById('black_mask');
    var change_password_container = document.getElementById('change_password_container');
    var form = document.getElementById('changePassword');
    form.reset();
    mask.style.display = 'none';
    change_password_container.style.display = 'none';

}

//提交修改密码表单
function passwordSubmitForm() {

    var form = document.getElementById('changePassword');
    form.submit();



}
//获取页面高度和宽度
function getContentSize() {
    var wh = document.documentElement.scrollHeight;
    ch = wh + "px";
    document.getElementById( "cont" ).style.height = ch;

}
window.onload = getContentSize;
window.onresize = getContentSize;