<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	<h1>Status: ${status}</h1>
	<p>Hostname: ${message}</p>
	<p>baseime: ${baseime}</p>

	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">Create new entry</a></li>
			<li><a href="#tabs-2">Modify entry</a></li>
			<li><a href="#tabs-3">Search</a></li>
		</ul>
		<div id="tabs-1">
			<form id="addEntry" method="POST"
				action="${pageContext.request.contextPath}/addEntry">
				<textarea id="ldif" name="ldif" style="width: 550px; height: 600px;"></textarea>
				<input type="submit" value="Add entry" />
			</form>
		</div>
		<div id="tabs-2">
			<form id="modifyEntry" method="POST"
				action="${pageContext.request.contextPath}/modifyEntry">
				<textarea id="ldif" name="ldif" style="width: 550px; height: 600px;"></textarea>
				<input type="submit" value="Modify Entry" />
			</form>
		</div>
		<div id="tabs-3">
			<form method="post" id="searchForm" action="${pageContext.request.contextPath}/search">
			<label>Enter base:</label> <br /> <input type="text" id="base"
				name="base" /> <br /> <label>Enter filter:</label> <br /> <input
				type="text" id="filter" name="filter" /> <br /> 
				<select id="scope" name="scope">
					<option value="base">BASE</option>
					<option value="one">ONE</option>
					<option value="sub">SUB</option>
			</select>
			<input type="submit" />
			</form>
		</div>


	</div>

</body>
</html>