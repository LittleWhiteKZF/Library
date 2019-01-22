<%@ page import="com.library.pojo.Post" %>
<%@ page import="com.library.pojo.User" %>
<%@ page import="com.library.pojo.Praise" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.library.pojo.Comment" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if (ActionContext.getContext().getSession().get("find") == null&&ActionContext.getContext().getSession().get("change")==null) {%>
<s:action name="showPosts" executeResult="true" namespace="/"/>
<%}%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_review.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/review.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_sidebar.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/side_bar.css"/>
    <script type="text/javascript" src="../js/review.js"></script>
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="../layer-v3.1.1/layer/layer.js"></script>
    <title>图书馆占位系统 | 书评</title>
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
        <div class="review_box">
            <div class="review_post">
                <div onclick="popBox()" class="review_post_button">发表我的书评
                </div>
                <div class="review_search">
                    <form id="review_search_form" method="post" action="findPosts">
                        <input class="review_search_input" type="text" name="book_name_input" id="book_name_input"
                               placeholder="输入书名查找书评"/>
                    </form>
                </div>
                <div class="review_search_button" onclick="searchFormSubmit()">
                </div>
            </div>

            <div class="review_show_box">
                <%
                    List<Post> posts = (List<Post>) ActionContext.getContext().getSession().get("post");
                %>
                <%List<Praise> praises = (List<Praise>) ActionContext.getContext().getSession().get("praise");%>
                <%
                    User user = (User) ActionContext.getContext().getSession().get("user");
                    if (posts != null) {
                %>

                <%for (Post post : posts) {%>
                <div class="review_show">

                    <div class="review_title"><%=post.getTitle()%>
                    </div>
                    <div class="review_book"><%=post.getBook()%>
                    </div>
                    <div class="user_info">
                        <div class="user_photo"><img src="<%=post.getProfile()%>"/></div>
                        <a class="user_name"><%=post.getUserName()%>
                        </a>
                        <a class="post_time">发表于：<%=post.getTime()%>
                        </a>
                    </div>
                    <div class="clearfloat"></div>
                    <div class="review_content">
                        <%=post.getContent()%>
                    </div>
                    <div class="review_option">
                        <input type="hidden" id="good_<%=post.getPid()%>" name="pid" value="<%=post.getPid()%>">
                        <%int a = 0;%>
                        <%if (praises != null) {%>
                        <%for (Praise praise : praises) {%>
                        <% if (praise.getPostid() == post.getPid() && praise.getUserid().equals(user.getSid()))
                            a = 1;
                        %>
                        <%}%>
                        <%}%>
                        <div class="approve_button" id="approve_button"
                             onclick="likeit(<%=post.getPid()%>,<%=post.getGood()%>,<%=a%>)">
                            <div style="margin-left:2px;float:left;"><img src="../images/triangle_1.png"/></div>
                            <input type="hidden" value="<%=post.getGood()%>" id="g_<%=post.getPid()%>">
                            <span id="l_<%=post.getPid()%>"> <%=post.getGood()%> </span> 赞
                        </div>

                        <div class="against_button" id="against_button"
                             onclick="dislikeit(<%=post.getPid()%>,<%=post.getGood()%>,<%=a%>)">
                            <div style="margin-left:2px;float:left;"><img src="../images/triangle_2.png"/></div>
                        </div>


                        <div class="comment_button" id="comment_button_<%=post.getPid()%>"
                             onclick="showComment(<%=post.getPid()%>)">
                            <div style="margin-left:2px;margin-right:3px;float:left;"><img
                                    src="../images/comment_icon.png"/></div>
                            <%if (post.comments != null) {%>
                            <%
                                    out.println(post.comments.size());
                                }
                            %>
                            <%if (post.comments == null) {%>
                            <%
                                    out.println(0);
                                }
                            %>
                            条评论
                        </div>

                        <div class="close_comment_button" id="close_comment_button_<%=post.getPid()%>"
                             onclick="closeComment(<%=post.getPid()%>)">
                            <div style="margin-left:2px;margin-right:3px;float:left;"><img
                                    src="../images/comment_icon.png"/></div>
                            收起评论
                        </div>
                    </div>

                    <div class="comment_list" id="comment_list_<%=post.getPid()%>">

                        <div class="comment_submit">
                            <form id="comment_form_<%=post.getPid()%>" method="post" action="publishComment">
                                <input type="hidden" id="pid" name="pid" value="<%=post.getPid()%>">
                                <input id="comment_content_<%=post.getPid()%>" name="content" type="text"
                                       class="comment_input"/>
                                <div class="comment_submit_button" onclick="subComment(<%=post.getPid()%>)">评论</div>
                            </form>

                        </div>

                        <%List<Comment> comments = post.comments;%>
                        <%if (comments != null) {%>
                        <%for (Comment comment : comments) {%>
                        <div class="comment_show">
                            <div class="comment_show_info">
                                <div class="user_photo"><img src="<%=comment.getProfile()%>"/></div>
                                <a class="user_name"><%=comment.getUserName()%>
                                </a>
                                <a class="post_time"><%=comment.getTime()%>
                                </a>
                            </div>
                            <div class="comment_content"><%=comment.getContent()%>
                            </div>
                        </div>
                        <%}%>
                        <%}%>
                    </div>
                    <div class="clearfloat"></div>
                </div>
                <%
                        }
                    }
                %>
                <div style="margin-left:435px;">
                    <%int num = (int) ActionContext.getContext().getSession().get("pageNum");%>
                    <%int now = (int) ActionContext.getContext().getSession().get("pageNow");%>
                    <table>
                        <tr>
                            <td>
                                <button onclick="First()">首页</button>
                            </td>

                            <td>
                                <button onclick="Last(<%=now%>)">上一页</button>&nbsp;&nbsp;
                            </td>
                            <%
                                for (int i = 1; i <= num; i++) {
                            %>
                            <td>
                                <button onclick="Page(<%=i%>)" style="border-style:none;background-color:#FFF"><u><%= i%>
                                </u></button>&nbsp;&nbsp;
                            </td>
                            <%}%>
                            <td>
                                <button onclick="Next(<%=now%>,<%=num%>)">下一页</button>
                            </td>
                            <td>
                                <button onclick="End(<%=num%>)">尾页</button>&nbsp;&nbsp;
                            </td>
                            <td>当前第&nbsp;<%= now %>&nbsp;页</td>
                        </tr>
                    </table>
                    <br>
                    <br>
                </div>
            </div>


        </div>
    </div>
</div>

<div class="mask" id="black_mask">
</div>

<div class="post_container" id="post_container" onscroll=SetCookie("scroll",div.scrollTop); onload="scrollback();">
    <div class="post_window" id="post_window">
        <div id="close" class="close" onclick="closeWindow()">
            <img src="../images/close.png"/>
        </div>
        <div style="clear:both"></div>
        <div class="post_body" id="post_body">
            <%--<form id="post_form" method="post" action="publishPost">--%>
            <p class="post_text">标题：<input type="text" id="title" name="title" class="post_text" placeholder="标题"/>
            </p>
            <p class="post_text" style="margin-top:20px;">书名：<input type="text" id="book" class="post_text"
                                                                    placeholder="书名"/>
            </p>
            <p class="post_text" style="margin-top:10px;">内容：
                <textarea id="context" name="context" class="post_text" placeholder="请输入内容......"></textarea></p>
            <div class="submit" id="report_submit" onclick="postFormSubmit()">提交
            </div>

            <%--</form>--%>

        </div>
    </div>
</div>
</body>
<%ActionContext.getContext().getSession().put("find", null);%>
</html>