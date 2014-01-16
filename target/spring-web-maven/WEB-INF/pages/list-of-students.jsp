<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>List of teams</title>
	</head>
	<body>
		<h1>List of Students</h1>
		<p>Here you can see the list of the students, edit them, remove or update.</p>
		<table border="1px" cellpadding="0" cellspacing="0" >
			<thead>
				<tr>
					<th width="10%">id</th>
					<th width="15%">name</th>
					<th width="10%">last name</th>
					<th width="10%">actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${students}">
					<tr>
						<td>${student.index}</td>
						<td>${student.name}</td>
						<td>${student.lastName}</td>
						<td>
							<a href="${pageContext.request.contextPath}/student/edit/${student.index}.html">Edit</a><br/>
							<a href="${pageContext.request.contextPath}/student/delete/${student.index}.html">Delete</a><br/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
	
	</body>
</html>