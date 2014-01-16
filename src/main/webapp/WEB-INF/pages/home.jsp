<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>
<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>ILDB</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();

	});
</script>
</head>
<body>
	<img src="js/banner.png" />
	<p>
		${message}</p> <br /> <a
			href="${pageContext.request.contextPath}/login.html">Login</a> <br />
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">Overview</a></li>
			<li><a href="#tabs-2">Enter new publicly accessible server</a></li>
			<li><a href="#tabs-3">Connect to a server</a></li>
		</ul>
		<div id="tabs-1">
			<table border="1px" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th width="15%">Hostname</th>
						<th width="10%">Port</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${lista}">
						<tr>
							<td>${item.hostname}</td>
							<td>${item.portNumber}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="tabs-2"></div>
		<div id="tabs-3">
			<form id="connectForm"
				action="${pageContext.request.contextPath}/connected" method="POST">
				<p>Pick a server from the list:</p>
				<br />
				<select id="hostnameChoice" name="hostnameChoice">
					<c:forEach var="item" items="${lista}">
						<option value="${item.hostname}">${item.hostname}</option>
					</c:forEach>
					
				</select>
				<br /> <br /> <label>BindRDN:</label><br /> <input type="text"
					id="bindRDN" name="bindRDN" /> <br /><label>BindPassword:</label><br />
				<br /> <input type="password" id="bindPass" name="bindPass" /> <input
					type="submit" value="Connect" />
			</form>
		</div>


	</div>
	</p>
</body>
</html>