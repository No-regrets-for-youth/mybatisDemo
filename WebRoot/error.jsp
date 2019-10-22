<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'error.jsp' starting page</title>

  </head>
  
  <body>
  对不起操作失败：<br/>
  错误原因：
  ${ sessionScope.code }
  </body>
</html>
