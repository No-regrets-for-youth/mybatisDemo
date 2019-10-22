<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>转账记录</title>
  </head>
  
  <body>
  <table border=1>
  	<tr>
  		<th>转账账号</th>
  		<th>收账账号</th>
  		<th>转账金额</th>
  	</tr>
  	<c:forEach items="${pageInfo.list }" var="log">
  		<tr>
  			<td>${log.accOut }</td>
  			<td>${log.accIn }</td>
  			<td>${log.money }</td>
  		</tr>
  	</c:forEach>
  </table>
  <a href="show?pageSize=${pageInfo.pageSize }&pageNumber=${pageInfo.pageNumber -1 } " <c:if test="${pageInfo.pageNumber<=1 }" > onclick="javascript:return false" </c:if>>上一页</a>
  <a href="show?pageSize=${pageInfo.pageSize }&pageNumber=${pageInfo.pageNumber +1 } " <c:if test="${pageInfo.pageNumber>=pageInfo.total }"> onclick="javascript:return false" </c:if>>下一页</a>
  </body>
</html>
