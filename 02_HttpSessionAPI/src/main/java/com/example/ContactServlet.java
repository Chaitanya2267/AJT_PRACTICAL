package com.example;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("phone", phone);
        
        // Redirect to the final form
        response.sendRedirect("education.html");
    }
}
