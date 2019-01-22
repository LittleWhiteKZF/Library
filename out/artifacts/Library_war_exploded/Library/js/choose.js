// JavaScript Document
//弹出选择座位页面
function popChooseSeatBox(e) {
    var mask = document.getElementById('black_mask');
    var choose_seat_container = document.getElementById('choose_seat_container');
    mask.style.display = 'block';
    choose_seat_container.style.display = 'block';
    var id = e.getAttribute("id");//获取座位ID
    document.getElementById("seatNum").innerHTML = id;//在选座窗口显示座位ID
    document.getElementById("seatIn").value = id;

}



function cannotIn() {
    layer.alert('您已入座，请勿重复选座！',{icon:6})
}

//弹出座位已被占用提示
function occupiedHint(e) {
    var id=e.getAttribute("id");
    document.getElementById("seatIn").value = id;
    var params={seatIn:id};
    $.ajax({
        type:"POST",
        url:"back.action",
        data:params,
        dataType:"json",
        success:function (json) {
            if(json=="back"){
                jQuery.post("setSeat.action");
                window.location.href="../stu/seating.jsp";

            }
            else layer.alert("该座位已被占用，请选择其他座位。",{icon:6});

        },
        error:function () {
            alert("出错了！");
        }
    })

}



//关闭选择座位页面
function closeWindow() {
    var mask = document.getElementById('black_mask');
    var choose_seat_container = document.getElementById('choose_seat_container');
    mask.style.display = 'none';
    choose_seat_container.style.display = 'none';
}

//提交座位表单
function seatSubmitForm() {
    var form = document.getElementById('chooseSeatingTime');
    form.submit();

}

//更换为楼层1
function turnToFloor1(){
    var floor1 = document.getElementById('floor1');
    var floor2 = document.getElementById('floor2');
    floor1.className="floor_focus";
    floor2.className="floor_not_focus";
    var floor1_box = document.getElementById('floor1_box');
    var floor2_box = document.getElementById('floor2_box');
    floor1_box.style.display = 'block';
    floor2_box.style.display = 'none';
}

//更换为楼层2
function turnToFloor2(){
    var floor1 = document.getElementById('floor1');
    var floor2 = document.getElementById('floor2');
    floor1.className="floor_not_focus";
    floor2.className="floor_focus";
    var floor1_box = document.getElementById('floor1_box');
    var floor2_box = document.getElementById('floor2_box');
    floor1_box.style.display = 'none';
    floor2_box.style.display = 'block';
}
