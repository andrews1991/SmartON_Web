<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script>
function callajax(){
	alert("success");
$.ajax({
    url : 'http://localhost:8080/StudentPortal/Event/addEventDetailImage',
    type : 'POST',
    data : formsave,
    enctype : 'multipart/form-data',
    contentType : false,
    cache : false,
    processData : false,
    success : function(response) {},
    error: function(){}    
 )};
}
 </script>
<h2>How to insert image in database using Spring MVC</h2>

<form action="" method="post" name="formsave" enctype="multipart/form-data">

<pre>
		
	Name: <input type="text" name="eventName">
	
				
	Age: <input type="text" name="date" id="date" placeholder="D.O.B"/>
				
	Photo: <input type="file" name="image">
				
	<input type="submit" value="Submit" onclick="callajax()">

</pre>

</form>

</body>
</html>