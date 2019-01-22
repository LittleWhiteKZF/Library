// JavaScript Document
var end;
var time;
if (sessionStorage.getItem("end") != null) {//若是不是第一次进入则已经有了结束时间，则将结束时间赋值给end
    end = sessionStorage.getItem("end");
}
else {
    time = document.getElementById('seatTime').value * 60;
}

var stopState = false;


if (sessionStorage.getItem("start") == null) {
    start = new Date().getTime();
    sessionStorage.setItem("start", start);
}


//将0-9的数字前面加上0		
function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

//座位倒计时
function countdown() {
    if (sessionStorage.getItem("end") == null) {
        end = new Date().getTime() / 1000 + time;
        sessionStorage.setItem("end", end);
    } else end = sessionStorage.getItem("end");
    var now = new Date().getTime() / 1000;
    time = end - now;

    sessionStorage.setItem("time", time);
    minutes = Math.floor(time / 60);
    seconds = Math.floor(time % 60);
    msg = checkTime(minutes) + ":" + checkTime(seconds);
    document.getElementById('countdown').innerText = msg;
    if (time !== 0 && !stopState) {
        timeout();
    }
    if (time == 0) {
        leave();
    }


}

//
// 刷新倒计时
var seatIn=document.getElementById("seatIn");
function timeout() {

                t = setTimeout(countdown, 1000);


}

//暂离倒计时
function timeout_short(short_time) {
    ts = setTimeout(function () {
        short_time--;
        end = sessionStorage.getItem("end");
        end++;
        sessionStorage.setItem("end", end);
        minutes = Math.floor(short_time / 60);
        seconds = Math.floor(short_time % 60);
        msg = checkTime(minutes) + ":" + checkTime(seconds);
        document.getElementById('remainder_time').innerText = msg;
        if (short_time !== 0) {
            timeout_short(short_time);
        } else {
            closeWindow('short_leave_container');
            stop_timeout_short();
            timegoon();
        }
    }, 1000);
}

//暂离倒计时停止
function stop_timeout_short() {
    clearTimeout(ts);
}

window.onload = timeout();

//座位倒计时继续
function timegoon() {
    stopState = false;
    timeout();
}

//座位倒计时停止
function timestop() {
    stopState = true;
}

//续座
function stay() {
    layer.alert('续座成功！',{icon:6})
    var t = document.getElementById('seatTime').value * 60;
    end = sessionStorage.getItem("end");
    time = sessionStorage.getItem("time");
    var p = t * 1 - time * 1;
    end = end * 1 + p * 1 + 1;
    sessionStorage.setItem("end", end);


}


//离座
function leave() {
    clearTimeout(t);
    var leave_time = new Date();
    var learning_time = leave_time - sessionStorage.getItem("start");
    hours = Math.floor((learning_time % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    mins = Math.floor((learning_time % (1000 * 60 * 60)) / (1000 * 60));
    secs = Math.floor((learning_time % (1000 * 60)) / 1000);
    msg = checkTime(hours) + ":" + checkTime(mins) + ":" + checkTime(secs);
    document.getElementById('studyTime').value = hours;
    sessionStorage.clear();
    alert('总学习时间:'+msg);

}

//弹出暂离页面
function popBox(w) {
    var mask = document.getElementById('black_mask');
    var container = document.getElementById(w);
    mask.style.display = 'block';
    container.style.display = 'block';
    var seatId =document.getElementById("seatIn").value;
    var parmar={seatIn:seatId};
    jQuery.post("shortLeave.action",parmar);
}

//关闭暂离页面
function closeWindow(w) {
    var mask = document.getElementById('black_mask');
    var container = document.getElementById(w);
    mask.style.display = 'none';
    container.style.display = 'none';
    var seatId =document.getElementById("seatIn").value;
    var parmar={seatIn:seatId};
    jQuery.post("comeback.action",parmar);
}

//提交举报表单
// function reportFormSubmit(){
// 	var form = document.getElementById('report_form');
// 	form.submit();
// 	var msg=document.getElementById('msg').value;
// 	alert(msg);
// }
function sub() {
    var form = document.getElementById('leaveForm');
    form.submit();
}

function reportFormSubmit() {
    var seatId = $("#seatId").val();
    var reason = $("#reason").val();
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "report.action",
        data: {seatId: seatId, reason: reason},
        success: function (json) {
            alert(json);
        },
        error: function () {
            alert("出错了！");
        }
    });

}

function f() {
    window.setInterval("isleave()", 3000);
    sessionStorage.setItem("seatIn",$("#seatIn").val())
}


function isleave() {
    var s = document.getElementById("seatIn").value;
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "isLeave.action",
        data: {seatIn: s},
        success: function (json) {
            if (json == "true") {
                alert("您已被移出座位！");
                sessionStorage.setItem("out","out");
                window.location.href = "../stu/choose.jsp"
            }


        },
        error: function () {
            alert("出错了！");
        }


    })
}

function egg() {
    alert("华生，你发现了盲点！");
}