<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of all products</title>
</head>
<body>
	<h1>List of all products</h1>
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<th>Name</th>
				<th>Quantity per unit</th>
				<th>Unit price (USD)</th>
				<th>Units in stock</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${products}" var="p">
			<tr>
				<td>${p.productName}</td>
				<td>${p.quantityPerUnit}</td>
				<td>${p.unitPrice}</td>
				<td>${p.unitsInStock}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>