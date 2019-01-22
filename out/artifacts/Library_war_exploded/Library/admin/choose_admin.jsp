<%@ page import="com.library.pojo.Seat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.library.action.SeatOpAction" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_choose_admin.css" />
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/choose_admin.css" />
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_sidebar.css" />
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/side_bar.css" />
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="../layer-v3.1.1/layer/layer.js"></script>
    <script src="../js/choose_admin.js" type="text/javascript"></script>
    <title>图书馆占位系统 | 座位管理</title>
</head>
<s:action name="showSeats" executeResult="true" namespace="/"/>

<body>
<div class="container">
    <div class="side_bar">
        <div class="side_bar_main">
            <ul>
                <li class="side_bar_li"><p><a href="#">管理员</a></p></li>
                <li class="side_bar_li"><p><a href="../admin/home_page_admin.jsp">个人主页</a></p></li>
                <li class="side_bar_li"><p><a href="../admin/choose_admin.jsp">座位管理</a></p></li>
                <li class="side_bar_li"><p><a href="../admin/report_admin.jsp">举报管理</a></p></li>
                <li class="side_bar_li"><p><a href="../stu/login.jsp">退出登录</a></p></li>
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
                                    status = "occupiedHint()";
                                    c = "seat";
                                } else {
                                    status = "popManageSeatBox(this)";
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


</div>

<div class="mask" id="black_mask">
</div>

<div class="manage_seat_container" id="manage_seat_container">

    <div class="manage_seat_window" id="manage_seat_window">
        <div id="close" class="close" onclick="closeWindow()">
            <img src="../images/close.png"/>
        </div>
        <div style="clear:both"></div>

        <h1 class="title" id="seatNum" name="seatNum"></h1>
        <div class="manage_seat_body" id="manage_seat_body">
            <form id="manageSeatingTime" method="post" action="remove">
                <input type="hidden" name="seatIn" id="seatIn" value="">
                <p class="manage_seat_text" id="sid" name="sid"></p>
                <p class="manage_seat_text" id="sName"></p>
                <div class="submit" onclick="seatSubmitForm()">移出座位</div>
            </form>
        </div>
    </div>
</div>
</body>
</html>