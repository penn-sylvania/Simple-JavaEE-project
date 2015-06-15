<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" type="text/css"/>
	<title>Add new employee</title>
</head>

<body>

    <form method="POST" action='EmployeeController' name="frmAddEmployee">
        <input type="hidden" name="ID"
            value="<c:out value="${employee.id}" />" /> <br />
        <div class="main">
	        <div class="field">
		        <label for="fname">First Name</label> 
		        <input class="textfield" type="text" name="FName" id="fname"
		               value="<c:out value="${employee.fname}" />" />
	        </div>
	        <div class="field">
		        <label for="lname">Last Name</label> 
	        	<input  class="textfield" type="text" name="LName" id="lname"
	            	    value="<c:out value="${employee.lname}" />" />
	        </div>
	        <div class="field">
	        	<label for="gender">Gender</label>
		        <input type="radio" name="Gender" value="M" id="gender"
		        	   <c:out value="${employee.gender == 'M' ? 'checked' : ''}" /> 
		        />Male
				<input type="radio" name="Gender" value="F" id="gender"
					   <c:out value="${employee.gender == 'F' ? 'checked' : ''}" /> 
				/>Female
			</div>
			<div class="field">
				<label for="jcode">Job Code</label>
	        	<input class="textfield" type="text" name="JobCode" id="jcode"
	                   value="<c:out value="${employee.jobcode}" />" />
	        </div>
	        <div class="field">
	        	<label for="salary">Salary</label>
	       		<input class="textfield" type="text" name="Salary" id="salary"
	             	   value="<c:out value="${employee.salary}" />" />
	        </div>
	        <div class="field">
	        	<label for="bd">Date of birth</label>
	        	<input class="textfield" type="text" name="BirthDate" id="bd"
	            	   value="<fmt:formatDate pattern="dd/MM/yyyy" 
	                   value="${employee.birthdate}" />" />
	        </div>
        
        
		<br>
		
        <input class="button_style" type="submit" name="Add" value="Add" 
               <c:out value="${employee.id != null ? 'disabled' : ''}" /> />
        <input class="button_style" type="submit" name="Save" value="Save"
               <c:out value="${employee.id == null ? 'disabled' : ''}" /> />
        <input class="button_style" type="submit" name="Cancel" value="Cancel" />
        </div>
        
        <c:if test="${not empty inputError}">
    		<script>
   		 		window.addEventListener("load",function(){
         			alert("${inputError}");
    			});
    		</script>
		</c:if>
    </form>
    
</body>

</html>