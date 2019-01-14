<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/somecss.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vehicles</title>
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

<c:if test="${!plates.isEmpty()}">
	<c:if test="${not empty msg}">
		<h2>${msg}</h2>
	</c:if>
	<h2>Click on a plate number to see more details about the car, or to edit its maintenance tasks.</h2>
	<c:forEach items="${plates}" var="plate">
	<a href="${pageContext.request.contextPath}/vehicles/${plate}">${plate}</a><br>
	</c:forEach>
</c:if>

<c:if test="${plates.isEmpty()}">
	<h2>There are no vehicles here yet!</h2>
</c:if>

</body>
</html>