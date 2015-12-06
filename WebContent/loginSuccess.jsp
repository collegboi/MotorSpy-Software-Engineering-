<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Motor Spy</title>
</head>

<body>

	<br />
	<br />

	<c:set var="user" value="${sessionScope.user}" />
	<b>Hello <c:out value="${user.firstName}" />
	</b>

	<br />
	<br />
	<form action="FrontController" method="post">
		<input type="hidden" name="viewType" value="1"/>
        <input type="hidden" name="action" value="ViewCarsUser" />
        <input type="submit" value="View Cars" />
    </form>
    <br>
    <b>Add Vehicle</b>
    <br>
	<form action="FrontController" method="post">
		Make:<br> <input type="text" name="make"> <br>
		Make:<br> <input type="text" name="model"> <br>
		Number Plate:<br> <input type="text" name="reg"> <br>
		Date 00/00/0000:<br> <input type="text" name="date"> <br>
		<input type="hidden" name="action" value="UploadCar" /> 
		<input type="submit" value="Upload" />
	</form>
</body>
</html>