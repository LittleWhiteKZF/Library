<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.opensymphony.xwork2.Action" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_seating.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/seating.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_sidebar.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/side_bar.css"/>
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="../layer-v3.1.1/layer/layer.js"></script>
    <title>图书馆占位系统 | 入座</title>
    <script>
        jQuery.post("setSeat.action");
    </script>
</head>
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
        <div class="seating_box">
            <p id="seat_id" class="seat_id">${seatIn}</p>
            <div id="countdown" class="time">00:00</div>
            <%int seatTime = (int) ActionContext.getContext().getSession().get("seatTime");%>
            <input type="hidden" value="<%=seatTime%>" id="seatTime" name="seatTime"/>

            <table class="option">
                <tr>
                    <td>
                        <div class="button" id="short_leave"
                             onclick="popBox('short_leave_container'),timeout_short(15*60),timestop()">暂离
                        </div>
                    </td>
                    <td>
                        <div class="button" id="long_leave"
                             onclick="popBox('short_leave_container'),timeout_short(90*60),timestop()">长离
                        </div>
                    </td>
                    <td>
                        <div class="button" id="stay" onclick="stay()">续座</div>
                    </td>
                    <td>
                        <form action="leave" method="post" id="leaveForm">
                            <div class="button" id="leave" onclick="leave();sub()">离座</div>
                            <input id="seatIn" name="seatIn" type="hidden" value="${seatIn}"/>
                            <input type="hidden" name="studyTime" id="studyTime" value=""/>
                        </form>
                    </td>
                </tr>
            </table>
            <div class="button button_report" id="report" onclick="popBox('report_container')">
                举报
            </div>
        </div>
    </div>
</div>

<div class="mask" id="black_mask">
</div>

<div class="short_leave_container" id="short_leave_container">
    <div class="short_leave_window" id="short_leave_window">
        <div class="close" onclick="egg()">
        </div>
        <div style="clear:both"></div>
        <div class="short_leave_body" id="short_leave_body">
            <p class="remainder_title">REMAINING TIME</p>
            <div id="remainder_time" class="remainder_time">
                00:00
            </div>
            <div class="button_2" id="return_seat"
                 onclick="closeWindow('short_leave_container'),stop_timeout_short(),timegoon()">回座
            </div>
        </div>
    </div>
</div>

<div class="report_container" id="report_container">
    <div class="report_window" id="report_window">
        <div id="close" class="close" onclick="closeWindow('report_container')">
            <img src="../images/close.png"/>
        </div>
        <div style="clear:both"></div>
        <p class="remainder_title">REPORT</p>
        <div class="report_body" id="report_body">
            <p class="report_text">座位号：
                <input type="text" name="seatId" id="seatId" class="report_text report_text_input"/>
            </p>
            <p class="report_text" style="margin-top:10px;">举报理由：</p>
            <textarea id="reason" name="reason" class="report_text report_text_textarea"></textarea>
            <div class="submit" id="report_submit" onclick="reportFormSubmit()">提交
            </div>

            <iframe name='hidden_frame' id="hidden_frame" style="display:none"></iframe>
        </div>
    </div>

</div>


</body>
<script src="../js/seating.js" type="text/javascript"></script>
<script>
    window.onload = f;
</script>

