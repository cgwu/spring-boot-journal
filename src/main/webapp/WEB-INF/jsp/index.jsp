<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>这是JSP</title>
</head>
<body>
<h1>这是index.jsp!这非常好！</h1>
<br>
now: ${sessionScope.now}
<br>
<a href="<c:url value="/url"/>">url.jsp</a>
<br>
<br>
<br><br><br>
<a href="<s:url value="/url"/>">surl.jsp</a>
</body>
</html>