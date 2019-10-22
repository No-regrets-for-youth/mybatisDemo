<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>转账</title>
  </head>
  
  <body>
    <form action="transfer" method="post">
    	转账账户：<input type="text" name="accOutNo"/><br/>
    	密码：<input type="password" name="accOutPwd" /><br/>
    	金额：<input type="text" name="accOutBalance" /><br/>
    	
    	收款账户：<input type="text" name="accInAccNo"/><br/>
    	收款人姓名：<input type="text" name="accInName"/><br/>
    	<input type="submit" value="转账">
    </form>
  </body>
</html>
