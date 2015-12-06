<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>liberty</title>
</head>
<body>
<form action="${ctx}/add" method="post">
  <p>userId: <input type="text" name="userId" /></p>
  <p>title: <input type="text" name="title" /></p>
  <p>content: <input type="text" name="content" /></p>
  <p>tags: <input type="text" name="tags" /></p>
  <p>score: <input type="text" name="score" /></p>
  <p>amount: <input type="text" name="amount" /></p>
  <input type="submit" value="Submit" />
</form>
</body>
</html>