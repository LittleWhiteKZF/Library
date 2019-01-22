<%@ page import="java.util.List" %>
<%@ page import="com.library.pojo.Page" %>
<%@ page import="com.library.pojo.Book" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if (ActionContext.getContext().getSession().get("seek") == null) {%>
<s:action name="showBooks" executeResult="true" namespace="/"/>
<%}%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_search.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/search.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_sidebar.css"/>
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/side_bar.css"/>
    <script src="../js/search.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="../layer-v3.1.1/layer/layer.js"></script>
    <title>图书馆占位系统 | 图书查询</title>
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
        <div class="search_box">
            <div class="search_input">
                <div style="float:left;margin-left:55px;">
                    <form id="book_search" method="post" action="seekBook">
                        <p class="search_input_title">请输入图书名称：
                            <input type="text" id="bookName" name="bookName" class="search_input_text"/></p>
                    </form>

                </div>
                <div class="book_search_button" onclick="formSubmit()">
                </div>
            </div>

            <div class="result_show" id="result_show">
                <div class="result_show_title">
                    <p>搜索结果</p>
                </div>
                <%List<Page> pages = (List<Page>) ActionContext.getContext().getSession().get("pages");%>
                <%int num = (int) ActionContext.getContext().getSession().get("num"); %>
				<%if(request.getParameter("num") != null)
					num = Integer.parseInt(request.getParameter("num")); %>
                <%if(pages!=null){%>
                <div class="result_show_box">
                    <%if (pages != null) {%>
                    <%for (Book book : pages.get(num-1).getData()) { %>
                    <div class="result_show_book">
                        <div class="book_cover">
                            <img src="<%=book.getPicture()%>"/>
                        </div>
                        <div class="book_information">
                            <%String status;%>
                            <%
                                if (book.isStatus()) status = "可借";
                                else status = "不可借";
                            %>
                            <p>书名：<%=book.getName()%>
                            </p>
                            <p>作者：<%=book.getAuthor()%>
                            </p>
                            <p>出版社：<%=book.getPress()%>
                            </p>
                            <p>余量：<%=book.getRemain_amount()%>
                            </p>
                            <p>馆藏：<%=book.getLocation()%> (<%=status%>)</p>
                        </div>
                    </div>
                    <%

                            }
                        }
                    %>
                    <div style="margin-left:300px;">
                    	<table>
   							<tr>
   								<td><button onclick="First()" >首页</button></td>
   								<td><button onclick="Last(<%= num%>)">上一页</button>&nbsp;&nbsp;</td>
   								<%
   								for(int i=1;i<=pages.size();i++){
   								%>
   								<td><button onclick="Page(<%= i%>)" style="border-style:none;background-color:#FFF"><u><%= i%></u></button>&nbsp;&nbsp;</td>
   								<%}%>
   								<td><button onclick="Next(<%= num%>,<%= pages.size()%>)">下一页</button></td>
   								<td><button onclick="End(<%= pages.size()%>)">尾页</button>&nbsp;&nbsp;</td>
   								<td>当前第&nbsp;<%= num %>&nbsp;页</td>
   							</tr>
   						</table>
                    </div>
                    <%}else{%><h1>找不到该图书！</h1><%}%>
                </div>
            </div>
        </div>
    </div>
</div>
<%ActionContext.getContext().getSession().put("seek",null);%>
</body>
