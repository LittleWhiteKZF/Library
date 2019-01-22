<%@ page import="java.util.List" %>
<%@ page import="com.library.pojo.Seat" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.library.action.SeatOpAction" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_choose.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/choose.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_sidebar.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/side_bar.css"/>
    <script src="../js/choose.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="../layer-v3.1.1/layer/layer.js"></script>
    <title>图书馆占位系统 | 选座</title>
    <script>
        if (sessionStorage.getItem("out") == 'out') {
            sessionStorage.clear();
            console.log("out")
        }
    </script>
</head>
<s:action name="showSeats" executeResult="true" namespace="/"/>
<body>
<div class="container">
    <div class="side_bar">
        <div class="side_bar_main">
            <ul>
                <li class="side_bar_li"><p><a href="home_page.jsp">个人主页</a></p></li>
                <li class="side_bar_li"><p><a href="choose.jsp">选座</a></p></li>
                <li class="side_bar_li"><p><a href="review.jsp">书评</a></p></li>
                <li class="side_bar_li"><p><a href="search.jsp">图书查询</a></p></li>
                <li class="side_bar_li"><p><a href="login.jsp">退出登录</a></p></li>
            </ul>
        </div>
    </div>

    <div class="box">
        <div class="choose_box">
            <div class="floor">
                <ul>
                    <li id="floor1" class="floor_focus" onclick="turnToFloor1()">1F</li>
                    <li id="floor2" class="floor_not_focus" onclick="turnToFloor2()">2F</li>
                </ul>
            </div>
            <%
                HashMap seats = (HashMap) ActionContext.getContext().getSession().get("seats");
                Set<String> floors = seats.keySet();
                System.out.println(floors);
                String display;
                for (String floor : floors) {
                    System.out.println(1);
                    if (floor.equals("1"))
                        display = "block";
                    else display = "none";
            %>

            <div class="seat_box" id="floor<%=floor%>_box" style="display:<%=display%>">
                <table class="seat_table">
                    <%List<Seat> seat = (List<Seat>) seats.get(floor);%>
                    <%SeatOpAction s = new SeatOpAction();%>
                    <%for (int i = 0; i < seat.size(); i += 6) {%>
                    <tr>
                        <%for (int j = i; j <= seat.size(); j++) {%>
                        <td>
                            <%String status, c;%>
                            <%
                                if (seat.get(j).isStatus()) {
                                    if (s.enterOrNot()) {
                                        status = "popChooseSeatBox(this)";
                                        c = "seat";
                                    } else {
                                        status = "cannotIn()";
                                        c = "seat";
                                    }
                                } else {

                                    status = "occupiedHint(this)";
                                    c = "seated";

                                }
                            %>
                            <div class="<%=c%>" onclick="<%=status%>"
                                 id="<%=seat.get(j).getSeatId()%>"><%=seat.get(j).getSeatId()%>
                            </div>
                        </td>
                        <%if ((j + 1) % 6 == 0) break;%>
                        <%}%>
                    </tr>
                    <%}%>
                </table>
            </div>
            <%}%>
        </div>
    </div>
</div>
<div class="mask" id="black_mask">
</div>

<div class="choose_seat_container" id="choose_seat_container">
    <div class="choose_seat_window" id="choose_seat_window">
        <div id="close" class="close" onclick="closeWindow()">
            <img src="../images/close.png"/>
        </div>
        <div style="clear:both"></div>
        <h1 class="title" id="seatNum"></h1>
        <div class="choose_seat_body" id="choose_seat_body">
            <form id="chooseSeatingTime" method="post" action="seatIn">
                <input type="hidden" name="seatIn" id="seatIn" value=""/>
                <s:token></s:token>
                <p class="choose_seat_text">入座时间：
                    <select name="seatingTime" class="seating_time_select">
                        <option value="120">
                            120分钟
                        </option>
                        <option value="180">
                            180分钟
                        </option>
                    </select>
                </p>
                <div class="submit" id="seat_submit" onclick="seatSubmitForm()">提交
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    var s=sessionStorage.getItem('seatIn');
    window.onload = f;
    function f() {
        window.setInterval("leave()", 3000);
    }
    function leave() {
        if(s!=null){
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
    }

</script>
</html>