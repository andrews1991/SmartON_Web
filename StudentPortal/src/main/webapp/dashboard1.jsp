<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>How to insert image in database using Spring MVC</h2>

<form action="http://localhost:8080/StudentPortal/Event/addEventDetailImage" method="post"  enctype="multipart/form-data">

<pre>
		
	Name: <input type="text" name="eventName">
				
	Age: <input type="number" name="eventOrgName">
				
	Photo: <input type="file" name="photo">
				
	<input type="submit" value="Submit">

</pre>

</form>

</body>
</html>