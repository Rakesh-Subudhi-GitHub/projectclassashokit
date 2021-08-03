<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<form:form action="SearchMobile" modelAttribute="formSearch"  method="POST">
			<table>
				<tr>
					<td>BRAND</td>
					<td>
						<form:select path="brand">
								<form:option value="">-Select-</form:option>
								<form:options items="${brands }" />
						</form:select>
					</td>
					
					<td>RAM</td>
					<td>	
						<form:select path="ram">
						<form:option value="">-Select-</form:option>
								<form:options items="${ram }" />
						</form:select>
					</td>
					
					<td>PRICE-RANGE</td>
					<td>	
						<form:select path="price">
						<form:option value="">-Select-</form:option>
								<form:option value="1000"> < 1000</form:option>
								<form:option value="1500"> < 1500</form:option>
								<form:option value="2000"> < 2000</form:option>
								<form:option value="3000"> < 3000</form:option>
						</form:select>
					</td>
					
					<td>RATING</td>
					<td>	
						<form:select path="rating">
						<form:option value="">-Select-</form:option>
								<form:options items="${rating }" />
						</form:select>
					</td>	
					
				</tr>
				
			</table>
			
			<button type="submit" class="btn btn-success" >Search</button>
			
		</form:form>
		
		<br>
		<br>
		<hr>
		<br>
		<br>
		
		<!-- Show list -->
		<div>
		
			<table border="1">
				<thead>
					<tr>
						<td>Mobile Name</td>
						<td>Mobile Ram</td>
						<td>Mobile Price</td>
						<td>Mobile Rating</td>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${filterMobilesList}" var="mobile">
						<tr>
							<td>${mobile.brand}</td>
							<td>${mobile.ram}</td>
							<td>${mobile.price}</td>
							<td>${mobile.rating}</td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
		
		</div>
		
		
</body>
</html>