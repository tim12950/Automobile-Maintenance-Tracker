<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/somecss.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Vehicle</title>
<script type="text/javascript">
	function validateForm() {
		
		var plate = document.getElementById('plate').value;
		var make = document.getElementById('make').value;
		var model = document.getElementById('model').value;
		var year = document.getElementById('year').value;
		var odometer = document.getElementById('odometer').value;
		
		if(plate.trim() == "") {
			alert("You must enter a plate number!")
			return false;
		}
		
		if(plate.length > 16) {
			alert("Plate numbers must be no more than 16 characters!");
			return false;
		}
		
		if(!(/^[a-z0-9]+$/i.test(plate))) {
			alert("Only alphanumeric characters allowed in the plate number!");
			return false;
		}
		
		if(make.trim() == "") {
			alert("You must enter a make!");
			return false;
		}
		
		if(make.length > 32) {
			alert("Makes must be no more than 32 characters");
			return false;
		}
		
		if(model.trim() == "") {
			alert("You must enter a model!");
			return false;
		}
		
		if(model.length > 32) {
			alert("Models must be no more than 32 characters");
			return false;
		}
		
		if(!(/^[0-9]+$/.test(year))) {
			alert("You must enter a non-negative integer as the year!");
			return false;
		}
		
		if(year.length > 4) {
			alert("Year must be no more than 4 characters");
			return false;
		}
		
		if(!(/^[0-9]+$/.test(odometer))) {
			alert("You must enter a non-negative integer as the odometer!");
			return false;
		}
		
		if(odometer.length > 32) {
			alert("Odometer must be no more than 32 characters");
			return false;
		}
		
		return true;
	}
</script>
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

<form action="${pageContext.request.contextPath}/vehicle-added" onsubmit="return validateForm()" method="post">
	Plate Number: <br><input type="text" name="plate" id="plate" style = "width:250px"><br><br>
	Type: ${type}<br><br>
	<input type="hidden" name="type" value="${type}">
	Make: <br><input type="text" name="make" id="make" style = "width:250px"><br><br>
	Model: <br><input type="text" name="model" id="model" style = "width:250px"><br><br>
	Year: <br><input type="text" name="year" id="year" style = "width:250px"><br><br>
	Odometer (km): <br><input type="text" name="odometer" id="odometer" style = "width:250px"><br><br>
	Schedule Maintenance Tasks: <br><br>
					<c:if test="${!type.equals('Electric')}">
  					Oil Change: <input type="checkbox" name="oilChange"><br><br>
  					</c:if>
  					Tire Rotation: <input type="checkbox" name="tireRotation"><br><br>
  					Top up Washer Fluid: <input type="checkbox" name="washerFluid"><br><br>
<button type="submit">Submit</button>
</form>

</body>
</html>