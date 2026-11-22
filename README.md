01_Servlet

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Servlet Example</h2>
	<form action="HelloServlet" method="post">
		Enter Name: <input type="text" name="name" required>
		<input type="submit" value="Submit">
	</form>
	<br>
</body>
</html>
```

```java
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h2>Hello from GET Method</h2>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		out.println("<h2>Welcome " + name + "</h2>");
		out.println("<p>POST request successful</p>");
	}
}
```

02_HttpSession
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="SessionServlet" method="post">
		name: <input type="text" name="name" required><br><br>
		Password: <input type="text" name="password" required><br><br>
		<input type="submit" value="Login">
	</form>
</body>
</html>
```

```java
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(name.equals("csw") && password.equals("123")) {
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			
			out.println("<h2>Welcom " + session.getAttribute("name") + "</h2>");
			out.println("Session created successfully");
			
		} else {
			out.println("invalid login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
```

03_jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Page</title>
</head>
<body>
	<h2>Student Registration</h2>
	<form method="post">
		Name: <input type="text" name="name" required><br><br>
		Roll No.: <input type="text" name="roll" required><br><br>
		Email: <input type="email" name="email" required><br><br>
		<input type="submit" value="submit">
	</form>
	 
	<%
		String name = request.getParameter("name");
		String roll = request.getParameter("roll");
		String email = request.getParameter("email");
		
		if(name != null){
	%>
			<h3>Student Information</h3>
			Name: <%= name %><br>
			Roll No.: <%= roll %><br>
			Email: <%= email %>
			
	<%
		}
	%>
	
</body>
</html>
```

04_jdbc

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Student Registration</h2>
	<form action="StudentServlet"  method="post">
		Name: <input type="text" name="name" required><br><br>
		Marks: <input type="email" name="email" required><br><br>
		Age: <input type="number" name="age" required><br><br>
		<input type="submit" value="save student">
	</form>
</body>
</html>
```

```java
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		String name = request.getParameter("name");
		String marks = request.getParameter("marks");
		String age = request.getParameter("age");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String url = "jdbc:mysql://localhost:3306/college";
		String user = "root";
		String pass = "CSW@9376";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try(Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement ps = con.prepareStatement(
					"INSERT INTO student(name, marks, age) VALUES(?, ?, ?)")) {
				ps.setString(1, name);
				ps.setInt(2, Integer.parseInt(marks));
				ps.setInt(3, Integer.parseInt(age));

				int rows = ps.executeUpdate();
				if(rows > 0) {
					out.println("<h2>Student Saved Successfully</h2>");
					out.println("<a href='index.html'>Add Another Student</a>");
				} else {
					out.println("<h2>Student Not Saved</h2>");
				}
			}
		} catch(Exception e) {
			getServletContext().log("Database error", e);
			out.println("<h2>Database Operation Failed</h2>");
			
		}
	}
	@Override
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {

	    doGet(request, response);
	}
}
```

05_mail

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mail</title>
</head>
<body>
	<h2>Send Email</h2>
	<form action="MailServlet" method="post">
		Receiver Email: <input type="email" name="to" required><br><br>
		Subject: <input type="text" name="subject" required><br><br>
		Message: <textarea name="message" required></textarea><br><br>
		<input type="submit" value="Send Email">
	</form>
</body>
</html>
```

```java
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
		String to = request.getParameter("to");
		String subject = request.getParameter("subject");
		String text = request.getParameter("message");
		
		String from = "yadavoggy3@gmail.com";
		String password = "fxjyszsjdocmikuu";
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(from == null || password == null) {
			out.println("Email Configuration Missing");
			return;
		}
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject(subject);
			msg.setText(text);
			Transport.send(msg);
			out.println("<h2>Email Sent Successfully</h2>");
		} catch (MessagingException e) {
			getServletContext().log("Email error", e);
			out.println("<h2>Email sending failed</h2>");
		}
	}
}
```

06_websocket

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Real-Time Application</h2>
	<input type="text" id="msg" placeholder="Enter your message">
	<button onclick="sendMessage()">Send</button>
	<div id="chat"></div>
	
	<script>
		// var protocol = location.protocol === "https:" ? "wss://" : "ws://";
		// var path = location.pathname;
		// var context = path.substring(0, path.lastIndexOf("/"));
		// var socket = new WebSocket(protocol + location.host + context + "/chat");
		
		var socket = new WebSocket("ws://localhost:8080/06_practice/chat");
		
		socket.onmessage = function(event) {
			var p = document.createElement("p");
			p.textContent = event.data;
			document.getElementById("chat").appendChild(p);
		};
		
		function sendMessage() {
			var msg = document.getElementById("msg").value;
			if (socket.readyState === WebSocket.OPEN && msg.trim() != "") {
				socket.send(msg);
				document.getElementById("msg").value = "";
			}
		}
	</script>
</body>
</html>
```

```java
import javax.websocket.*;
import javax.websocket.server.*;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
public class ChatServer {
	private static final Set<Session> users = ConcurrentHashMap.newKeySet();
	
	@OnOpen
	public void onOpen(Session session) {
		users.add(session);
		System.out.println("User Connected");
		session.getAsyncRemote().sendText("Welcome to Chat!");
		System.out.println("Total Users: " + users.size());
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		for(Session user : users) {
			if (user.isOpen()) {
				user.getAsyncRemote().sendText(message);
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		users.remove(session);
		System.out.println("User Disconnected");
	}
	
	@OnError
	public void onError(Session session, Throwable error) {
		if (session != null) users.remove(session);
		error.printStackTrace();
	}
}
```
