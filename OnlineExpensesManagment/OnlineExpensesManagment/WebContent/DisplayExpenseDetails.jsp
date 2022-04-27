<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.utility.*"%>
<%@page import="com.servlet.*"%>
<%@page import="com.operation.*"%>
<%@page import="com.dbconnection.*"%>
<%@page import="java.util.ArrayList"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> expense details</title>
</head>
<body
	background="sample.jpg">
	
	<h3 align="right"><a style="color:white;" href="HomePage.html">Logout</a></h3>
	<center>
	<%String Name= (String) session.getAttribute("uname"); 
		out.println(" <font style='font-family: roboto;' color='white' size='8'> Hello "+Name+"</font>");
	%></center>
	
	
	
	<center>
		<font style="font-family: roboto;" color="white" size="10">Your Expenses</font>
		<table border='2'>
			<tr>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>Category</th>
				<c:if test="${type eq 'admin'}">
					<th>Update</th>
					<th>Delete</th>
				</c:if>
			</tr>
			
			<c:forEach items="${productlistList}" var="product">
			<c:if test="${uname eq product.username }">
				<tr>
					<td>${ product.pid}</td>
					<td>${ product.pname}</td>
					<td>${ product.pprice}</td>
					<td>${product.pcatagory}</td>

					<c:if test="${type eq 'admin'}">
						<td ><a style="color:white;" href="UpdateController?pid=${ product.pid}"> Update</a></td>
						<td ><a style="color:white;" href="DeleteExpenseServlet?pid=${ product.pid}"> Delete</a></td>
						
					</c:if>
				</tr>
				</c:if>

			</c:forEach>
		</table>
	</center>
	<c:if test="${type eq 'admin'}">
		<h3 align="center"><a style="color:white;" href="AddExpense.jsp">Add Expense</a></h3>
	</c:if>

</body>
</html>