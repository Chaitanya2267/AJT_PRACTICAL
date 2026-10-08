<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demo Program of JSP page</title>
</head>
<body>
    <form method="post">
        Enter your name:
        <input type="text" name="name" required>
        <br><br>
        Enter your age:
        <input type="number" name="age" required>
        <br>
        <input type="submit" value="Submit">
    </form>

    <%
    	String name = request.getParameter("name");
    	String ageString = request.getParameter("age");
    	
    	if(name != null && ageString != null) {
    		
    		int age = Integer.parseInt(ageString);
    		
    		out.println("<h3>Name: " + name + "</h3>");
    		
    		if(age < 0 || age > 120){
    			out.println("<h3>Invalid age</h3>");
    		}
    		else if(age >= 18){
    			out.println("<h3>Age: " + age + "</h3>");
    			out.println("<h3>You are 18 or above.</h3>");
    		}
    		else{
    			out.println("<h3>Age:" + age + "</h3>");
    			out.println("<h3>You are below 18.Not eligible for voting.</h3>");
    		}
    	}
    %>
</body>
</html>
