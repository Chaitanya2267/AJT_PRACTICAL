package com.example;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PersonalServlet")
public class PersonalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("age", age);
        
        // Redirect to the next form
        response.sendRedirect("contact.html");
    }
}
