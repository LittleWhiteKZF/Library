<%@ page import="com.library.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_homepage.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/home_page.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_sidebar.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/side_bar.css"/>
    <script src="../js/home_page.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="../layer-v3.1.1/layer/layer.js"></script>
    <title>图书馆占位系统 | 个人主页</title>
</head>

<body>
<input type="hidden" id="identity" value="admin"/>
<div class="container" id="cont">
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
        <div class="personal_information">
            <div class="personal_information_background">
                <img src="${admin.profile}">
            </div>
            <div class="photo">
                <img src="${admin.profile}">
            </div>
            <div class="information">
                <table class="information_table">

                    <tr>
                        <td align="right">工号：</td>
                        <td>${admin.adminId}</td>
                    </tr>
                    <tr>
                        <td align="right">姓名：</td>
                        <td>${admin.name}</td>
                    </tr>
                    <tr>
                        <td align="right">入职时间：</td>
                        <td>${admin.intime}</td>
                    </tr>
                </table>
            </div>
            <div class="turn_to_change_password" onclick="popChangePasswordBox()"><p>修改密码</p>
            </div>
        </div>
        <div class="ranking_list">
            <table class="ranking_list_table">
                <div class="title">学霸榜</div>
                <img src="../images/ranking_list_2.png"/>
                <%List<User> rank = (List<User>) ActionContext.getContext().getSession().get("rank");%>
                <%for (int i = 1; i <= rank.size(); i++) {%>
                <tr>
                    <td align="center"><%out.print(i);%></td>
                    <td align="right"><%out.print(rank.get(i - 1).getName());%></td>
                    <td align="right"><%out.print(rank.get(i - 1).getTime());%>小时</td>

                </tr>
                <%}%>
            </table>
        </div>

        <div class="about">
            <p class="about_title">关于</p>
            <div class="about_function">
                <div class="function_icon">
                    <img src="../images/seat_icon.png"/>
                </div>
                <div class="function_content">
                    <h1>选座</h1>
                    <br/>
                    <p>用户选择座位，若该座位没人可入座，点击退座离开。</p>
                </div>
            </div>
            <div class="about_function">
                <div class="function_icon">
                    <img src="../images/book_icon.png"/>
                </div>
                <div class="function_content">
                    <h1>书评</h1>
                    <br/>
                    <p>用户可以对自己最近阅读的书籍发表书评，同时可以查看其他用户的书评，并且可以评论，点赞以及不赞同。</p>
                </div>
            </div>
            <div class="about_function">
                <div class="function_icon">
                    <img src="../images/search_icon.png"/>
                </div>
                <div class="function_content">
                    <h1>图书查询</h1>
                    <br/>
                    <p>输入图书名称可模糊查询所有同名图书所在楼层位置，图书编号，图片，作者，出版社，所剩本数，是否可外借等信息。</p>
                </div>
            </div>
            <div class="about_function">
                <div class="function_icon">
                    <img src="../images/user_icon.png"/>
                </div>
                <div class="function_content">
                    <h1>个人中心</h1>
                    <br/>
                    <p>显示用户的个人信息，如：头像、学号、姓名、专业、信用积分、学习总时长，并可修改密码。</p>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mask" id="black_mask">
</div>

<div class="change_password_container" id="change_password_container">
    <div class="change_password_window" id="change_password_window">
        <div id="close" class="close" onclick="closeWindow()">
            <img src="../images/close.png"/>
        </div>
        <div style="clear:both"></div>
        <h1 class="title">CHANGE PASSWORD</h1>
        <div class="change_password_body" id="change_password_body">
            <form id="changePassword" method="post" action="changeAdmin">
                <p><i class="password_icon"></i><input class="change_password_input" type="password" id="oldPwd"
                                                       name="oldPwd" placeholder="请输入旧密码"/></p>
                <p><i class="password_icon"></i><input class="change_password_input" type="password" id="newPwd"
                                                       name="newPwd" placeholder="请输入新密码"/></p>
                <div class="submit" id="password_submit" onclick="passwordSubmitForm()">提交</div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
