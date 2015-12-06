<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import= "com.example.business.Car" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Motor Spy</title>
<style>table, th, td  { 
   border: 1px solid black; }
table {
	display: table;
    border-collapse: separate;
    border-spacing: 2px;
    border-color: gray;}
</style>
</head>
<body>
<table style="width:50%">
<tr>
    <th>Number Plate</th>
    <th>Make</th> 
    <th>Model</th>
    <th>Date</th>
    <th></th>
  </tr>

<% 
	List<Car> cars;
	cars = (List)(request.getSession().getAttribute("car"));
	Car car =(Car)request.getSession().getAttribute("updateCar");
	
	for(int i = 0; i < cars.size(); i++) {
		%>
		<tr>
			<td><%=cars.get(i).getCarReg()%></td>
			<td><%=cars.get(i).getMake()%></td>
			<td><%=cars.get(i).getModel()%></td>
			<td><%=cars.get(i).getDate()%></td>
			<td>
			<form action="FrontController" method="post">
				<input type="hidden" name="carID" value="<%=cars.get(i).getCarID()%>"/>
                <input type="hidden" name="action" value="UpdateCar" />
                <input type="submit" value="Update Car" />
            </form>
            </td>
		</tr>
		<%
		
	}
%>
</table>	
<br>
<form action="FrontController" method="post">
		<input type="hidden" name="carID" value="<%=car.getCarID()%>"/>
		Make:<br> <input type="text" name="make" value = "<%=car.getMake()%>"> <br>
		Model:<br> <input type="text" name="model" value = "<%=car.getModel()%>"> <br>
		Number Plate:<br> <input type="text" name="reg" value = "<%=car.getCarReg()%>"> <br>
		Date 00/00/0000:<br> <input type="text" name="date" value = "<%=car.getDate()%>"> <br>
		<input type="hidden" name="action" value="UpdateCarDB" /> 
		<input type="submit" value="Update" />
	</form>

</body>

</html>