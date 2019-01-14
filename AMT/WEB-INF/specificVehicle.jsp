<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/somecss.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vehicle ${plate}</title>
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
		
	</header><br>

<h2>${msg}</h2>
<h2>Vehicle ${plate}:</h2><br>
Type: ${type}<br><br>
Model: ${model}<br><br>
Make: ${make}<br><br>
Year: ${year}<br><br>
Odometer (km): ${odometer}<br><br>
Scheduled Maintenance Tasks:<br>
<c:choose>
	<c:when test="${scheduledTasks.hasNext()}">
		<table border="1">
		<tr>
    		<th>Task</th>
    		<th>Status</th>
 		</tr>
	 
		<c:forEach items="${scheduledTasks}" var="task">
			<tr>
    			<td>${task}</td>
    			<td>
    				<c:choose>
    					<c:when test="${statuses.next() == 'true'}">
    					Complete
    					</c:when>
    					<c:otherwise>
    					Not Complete
    					</c:otherwise>
    				</c:choose>
    			</td>
 			</tr>
		</c:forEach>
	</table>
	</c:when>
	<c:otherwise>
		There are no scheduled tasks!
	</c:otherwise>
</c:choose>
<br><br>
<form action="${pageContext.request.contextPath}/edit/${plate}" method="post">
    	<button type="submit">Edit Tasks</button>
</form>

</body>
</html>