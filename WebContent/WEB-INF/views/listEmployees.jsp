<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css" type="text/css"/>
	<title>Show All Users</title>
</head>

<body>
	<form method="POST" action="ListEmployeesController" name="frmListEmployee">
	<div class="datagrid">
	<table>
	        <thead>
	            <tr>
	                <th>Id</th>
	                <th>Last Name</th>
	                <th>First Name</th>
	                <th>Gender</th>
	                <th>Job Code</th>
	                <th>Salary</th>
	                <th>Birth Date</th>
	                <th></th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach items="${employees}" var="employee">
	                <tr class="alt">
	                    <td><c:out value="${employee.id}" /></td>
	                    <td><c:out value="${employee.lname}" /></td>
	                    <td><c:out value="${employee.fname}" /></td>
	                    <td><c:out value="${employee.gender}" /></td>
	                    <td><c:out value="${employee.jobcode}" /></td>
	                    <td><c:out value="${employee.salary}" /></td>
	                    <td><fmt:formatDate pattern="dd/MM/yyyy" 
	                    					value="${employee.birthdate}" /></td>
	                    <td><input type="checkbox" name="selectedRows" 
	                    		   value=<c:out value="${employee.id}" /> />
	                    </td>
	                </tr>
	            </c:forEach>
	        </tbody>
	</table>
	</div>
	<br>
	    
	    <input class="button_style" type="submit" name="Refresh" value="Refresh" />
	    <input class="button_style" type="submit" name="Delete" value="Delete" />
	    <input class="button_style" type="submit" name="Edit" value="Edit" />
	    
	    <c:if test="${not empty error}">
	    	<script>
	   		 	window.addEventListener("load",function(){
	         		alert("${error}");
	    		});
	    	</script>
		</c:if>
	</form>
	    
</body>

</html>