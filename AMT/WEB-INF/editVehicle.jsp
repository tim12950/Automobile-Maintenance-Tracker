<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/somecss.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit ${plate}</title>
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
	
<h2>Vehicle ${plate}:</h2><br>
Type: ${type}<br><br>
Model: ${model}<br><br>
Make: ${make}<br><br>
Year: ${year}<br><br>
Odometer (km): ${odometer}<br><br>
Scheduled Maintenance Tasks:<br>
<c:choose>
	<c:when test="${scheduledTasks1.hasNext()}">
		<table border="1">
		<tr>
    		<th>Task</th>
    		<th>Status</th>
 		</tr>
	 
		<c:forEach items="${scheduledTasks1}" var="task">
			<tr>
    			<td>${task}</td>
    			<td>
    				<c:choose>
    					<c:when test="${statuses1.next() == 'true'}">
    					Complete
    					</c:when>
    					<c:otherwise>
    					Not Complete
    					</c:otherwise>
    				</c:choose>
    			</td>
 			</tr>
		</c:forEach>
	</table><br><br>
	</c:when>
	<c:otherwise>
		There are no scheduled tasks!<br><br>
	</c:otherwise>
</c:choose>

<form action="${pageContext.request.contextPath}/vehicle-edited" onsubmit="" method="post">
	<input type="hidden" name="plate" value="${plate}">
	<c:if test="${scheduledTasks2.hasNext()}">
		<h3>Toggle statuses of scheduled tasks:</h3>
		<c:forEach items="${scheduledTasks2}" var="task">
			Change status of ${task} to 
			<c:choose>
				<c:when test="${statuses2.next() == 'true'}">
				Not Complete: <input type="checkbox" name="toggle${task}"><br><br>
				</c:when>
				<c:otherwise>
				Complete:<input type="checkbox" name="toggle${task}"><br><br>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</c:if>
	
	<c:if test="${surplusTasks.hasNext()}">
		<h3>Schedule a new Task:</h3>
		<c:forEach items="${surplusTasks}" var="task">
			${task}: <input type="checkbox" name="add${task}"><br><br>
		</c:forEach>
	</c:if>
	
	<c:if test="${scheduledTasks3.hasNext()}">
		<h3>Unschedule Tasks:</h3>
		<c:forEach items="${scheduledTasks3}" var="task">
			${task}: <input type="checkbox" name="remove${task}"><br><br>
		</c:forEach>
	</c:if>
	
	<button type="submit">Submit Edits</button>
</form>

</body>
</html>