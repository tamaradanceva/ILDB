<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>
<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<img src="js/banner.png" />
	<h1>Status of change:</h1>>
	<br />
	${status}
	<p><a href="${pageContext.request.contextPath}">Home page</a></p>
	<p><a href="${pageContext.request.contextPath}/connected">Go back</a></p>
</body>
</html>