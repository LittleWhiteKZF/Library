<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" media="screen and (max-device-width: 1100px)" href="../css/m_login.css" />
    <link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1100px)" href="../css/login.css" />
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon" />
    <script src="../js/login.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="../layer-v3.1.1/layer/layer.js"></script>
<title>图书馆占位系统 | 登录</title>
</head>

<body>
   <div class="login_container">
       <div class="login_main">
           <div class="logo">
           </div>
           <div class="login">
               <form id="login" method="post" action="login">
                   <h1 class="login_title">LOGIN</h1>
                   <p class="login_text"><i class="user_icon"></i><input class="login_text_input" type="text" name="sid" id='sid' placeholder="请输入账号"/></p>

                   <p class="login_text"><i class="password_icon"></i><input class="login_text_input" type="password" name="password" id="password" placeholder="请输入密码"/>
                           <select name="identity" class="identity_select" id="identity" >
                               <option value="student">
                                   学生
                               </option>
                               <option value="administrator">
                                   管理员
                               </option>
                           </select>
                   </p>
                   <div class="submit" id="submit" onclick="loginSubmitForm()">
                       登录</div>
               </form>
           </div>
       </div>
    </div>
</body>

