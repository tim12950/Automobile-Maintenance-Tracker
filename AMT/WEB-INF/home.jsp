<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/somecss.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<header class="mainheader">
	<h1><center>Automobile Maintenance Tracker</center></h1>
		
		<nav>
			<ul>
				<li><a href="${pageContext.request.contextPath}/home">HOME</a></li>
				<li><a href="${pageContext.request.contextPath}/vehicles">LIST/EDIT VEHICLES</a></li>
				<li><a href="${pageContext.request.contextPath}/add-electric-vehicle">ADD ELECTRIC VEHICLE</a></li>
				<li><a href="${pageContext.request.contextPath}/add-gas-vehicle">ADD GAS VEHICLE</a></li>
				<li><a href="${pageContext.request.contextPath}/add-diesel-vehicle">ADD DIESEL VEHICLE</a></li>
			</ul>
		</nav>
		
	</header>
	<br>
	<h2>My name is Tim and this is an automobile maintenance tracker web application written using Java,
	JSP, HTML, and JavaScript. You can add vehicles and schedule/update/delete maintenance tasks for
	 those vehicles. Thank you for visiting!</h2>
</body>
</html>