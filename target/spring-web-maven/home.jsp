<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Home page</title>
	</head>
	<body>
		<h1>Home page</h1>
		<p>
			${message}
			<br /> 
			<a href="${pageContext.request.contextPath}/student/add.html">Add new student</a> 
			<br /> 
			<a href="${pageContext.request.contextPath}/student/list.html">Students list</a><br />
			
			<br /> 
			<a href="${pageContext.request.contextPath}/lab-group/add.html">Lab Group new student</a> 
			<br /> 
			<a href="${pageContext.request.contextPath}/lab-group/list.html">Lab Group list</a><br />
		</p>
	</body>
</html>