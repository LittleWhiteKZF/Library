// JavaScript Document
// 弹出座位管理页面


function popManageSeatBox(e) {

    var id = e.getAttribute("id");//获取座位ID
    document.getElementById("seatIn").value = id;
    var params = {seatIn: id};
    $.ajax({
        type: "POST",
        url: "search.action",
        data: params,
        dataType: "json",
        success: function (json) {
            var l = json.split(',');
            document.getElementById('sid').innerHTML = '学号：' + l[0];
            document.getElementById('sName').innerHTML = '姓名：' + l[1];
        },
        error: function () {
            alert('error');
        }
    });
    var mask = document.getElementById('black_mask');
    var manage_seat_container = document.getElementById('manage_seat_container');
    mask.style.display = 'block';
    manage_seat_container.style.display = 'block';
    document.getElementById("seatNum").innerHTML = id;//在选座窗口显示座位ID


}


//弹出座位无人占用提示
function occupiedHint() {
    layer.alert("该座位无人占用。", {icon: 6});
}

//关闭座位管理页面
function closeWindow() {
    var mask = document.getElementById('black_mask');
    var manage_seat_container = document.getElementById('manage_seat_container');
    mask.style.display = 'none';
    manage_seat_container.style.display = 'none';
}

//提交座位表单
function seatSubmitForm() {
    var seatIn = document.getElementById('seatIn').value;
    $.ajax({
        type: "POST",
        url: "isShortLeave.action",
        dataType: "json",
        data: {seatIn: seatIn},
        success: function (json) {
            var form = document.getElementById('manageSeatingTime');
            if (json == 'leave') {
                if (confirm("该用户已开启座位保护，是否继续移出？")) {
                    form.submit();
                    alert("移出成功");
                }
            } else {
                form.submit();
                alert("移出成功");
            }
        }
    })




}

//更换为楼层1
function turnToFloor1() {
    var floor1 = document.getElementById('floor1');
    var floor2 = document.getElementById('floor2');
    floor1.className = "floor_focus";
    floor2.className = "floor_not_focus";
    var floor1_box = document.getElementById('floor1_box');
    var floor2_box = document.getElementById('floor2_box');
    floor1_box.style.display = 'block';
    floor2_box.style.display = 'none';
}

//更换为楼层2
function turnToFloor2() {
    var floor1 = document.getElementById('floor1');
    var floor2 = document.getElementById('floor2');
    floor1.className = "floor_not_focus";
    floor2.className = "floor_focus";
    var floor1_box = document.getElementById('floor1_box');
    var floor2_box = document.getElementById('floor2_box');
    floor1_box.style.display = 'none';
    floor2_box.style.display = 'block';
}
