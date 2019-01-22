<%@ page import="java.util.List" %>
<%@ page import="com.library.pojo.Report" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:action name="showReport" namespace="/" executeResult="true"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_report_admin.css" />
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/report_admin.css" />
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_sidebar.css" />
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/side_bar.css" />
    <script src="../js/report_admin.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="../layer-v3.1.1/layer/layer.js"></script>
    <title>图书馆占位系统 | 举报管理</title>
</head>

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
        <div class="report_box">
            <div class="report_title"><p>举报管理</p></div>
            <div class="report_show_box">
                <table id="report_table" border="0" cellspacing="0">
                    <th width="50">序号</th>
                    <th width="180">举报时间</th>
                    <th width="90">举报人</th>
                    <th width="90">被举报人</th>
                    <th width="400">举报理由</th>
                    <th width="40" colspan="2">处理</th>
                    <%List<Report> reports = (List<Report>) ActionContext.getContext().getSession().get("reports");%>
                    <%
                        if (reports != null) {
                            int i = 0;
                    %>
                    <%for (Report report : reports) {%>
                    <%i++;%>
                    <tr>
                        <td><%=i%>
                        </td>
                        <td><%=report.getDate()%>
                        </td>
                        <td><%=report.getReportUser()%>
                        </td>
                        <td><%=report.getReportedUser()%>
                        </td>
                        <td><%=report.getReason()%>
                        </td>
                        <td>
                            <%
                                if (report.getStatus() == -1) {
                                    out.println("已扣分");
                                } else if (report.getStatus() == 0) {
                                    out.println("");
                                } else {
                            %>
                            <button onclick="deduct('<%=i%>','<%=report.getReportId()%>','<%=report.getReportedUser()%>')">
                                扣分
                            </button>
                            <%}%>


                        </td>
                        <td>
                            <%
                                if (report.getStatus() == -1) {
                                    out.println("");
                                } else if (report.getStatus() == 0) {
                                    out.println("已忽略");
                                } else {
                            %>
                            <button onclick="ignore('<%=i%>','<%=report.getReportId()%>')">忽略</button>
                            <%}%>
                        </td>
                    </tr>
                    <%}%>
                    <%}%>

                </table>
            </div>
        </div>
    </div>
</div>


</body>
</html>