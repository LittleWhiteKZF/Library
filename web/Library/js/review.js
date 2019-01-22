// JavaScript Document
//弹出发表书评页面
function popBox() {
    var mask = document.getElementById('black_mask');
    var container = document.getElementById('post_container');
    mask.style.display = 'block';
    container.style.display = 'block';
}

//关闭发表书评页面
function closeWindow() {
    var mask = document.getElementById('black_mask');
    var container = document.getElementById('post_container');
    mask.style.display = 'none';
    container.style.display = 'none';
}

//提交发表书评表单
function postFormSubmit() {
    // var form = document.getElementById('post_form');
    // form.submit();
    var title = $("#title").val();
    var text = $("#context").val();
    var book = $("#book").val();
    if (title == null || title == '') {
        layer.alert("标题不可为空！", {icon: 6})
    }
    else if (text == null || text == '') {
        layer.alert("内容不可为空！", {icon: 6})
    }
    else if (book == null || book == '') {
        layer.alert("书名不可为空！", {icon: 6})
    } else {
        var context = text.replace(/\r\n/g, '<br/>').replace(/\n/g, '<br/>').replace(/\s/g, ' ');//将换行符存入数据库
        var url = "publishPost.action";
        var param = {title: title, context: context, book: book};
        jQuery.post(url, param);
        alert("提交成功");
        window.location.reload();

    }
}

function subComment(id) {
    var form = document.getElementById('comment_form_' + id);
    form.submit();
    alert("评论成功");

}

//显示评论
function showComment(id) {
    var comment_list = document.getElementById('comment_list_' + id);
    var comment_button = document.getElementById('comment_button_' + id);
    var close_comment_button = document.getElementById('close_comment_button_' + id);
    comment_list.style.display = 'block';
    comment_button.style.display = 'none';
    close_comment_button.style.display = 'block';
}

//收起评论
function closeComment(id) {
    var comment_list = document.getElementById('comment_list_' + id);
    var comment_button = document.getElementById('comment_button_' + id);
    var close_comment_button = document.getElementById('close_comment_button_' + id);
    comment_list.style.display = 'none';
    comment_button.style.display = 'block';
    close_comment_button.style.display = 'none';
}

function likeit(id, num, a) {
    var good = document.getElementById('g_' + id).value;
    if (good < num || a == 0 && good == num) {
        good++;
    } else
        layer.alert("你已点赞过！", {icon: 6});

    document.getElementById('l_' + id).innerHTML = good;
    document.getElementById('g_' + id).value = good;
    var url = "like.action";
    var param = {pid: eval(document.getElementById('good_' + id).value)};
    jQuery.post(url, param);
}

function dislikeit(id, num, a) {
    var good = document.getElementById('g_' + id).value;
    if (good > num || a == 1 && good == num) {
        good--;
    } else
        layer.alert("你未点赞该帖子", {icon: 6});
    document.getElementById('l_' + id).innerHTML = good;
    document.getElementById('g_' + id).value = good;
    var url = "dislike.action";
    var param = {pid: eval(document.getElementById('good_' + id).value)};
    jQuery.post(url, param);
}

//提交搜索表单
function searchFormSubmit() {
    var book = document.getElementById("book_name_input").value;
    var param = {book_name_input: book};
    $.ajax({
        type: "POST",
        data: param,
        dataType: "json",
        url: "isNull.action",
        success: function f(json) {
            if(json=="null"){
                layer.alert("找不到该帖子！",{icon:6});
            }else{
                var form = document.getElementById('review_search_form');
                form.submit();
            }
        }
    })

}

function First() {
    var param = {nowPage: 1};
    var url = "changePage";
    jQuery.post(url, param);
    window.setTimeout("window.location='../stu/review.jsp'",100);
}

function Last(now) {
    if (now - 1 < 1) {
        layer.alert("当前已是第一页！", {icon: 6});
    } else {
        jQuery.post("changePage", {nowPage: now - 1});
        window.setTimeout("window.location='../stu/review.jsp'",100);
    }
}

function Page(i) {
    jQuery.post("showPosts", {nowPage: i});
    window.setTimeout("window.location='../stu/review.jsp'",100);
}

function Next(now, num) {
    if (now + 1 > num) {
        layer.alert("当前已是最后页！", {icon: 6});
    } else {
        jQuery.post("changePage", {nowPage: now + 1});
        window.setTimeout("window.location='../stu/review.jsp'",100);
    }
}

function End(num) {

    jQuery.post("changePage", {nowPage: num});
    window.setTimeout("window.location='../stu/review.jsp'",100);
}





