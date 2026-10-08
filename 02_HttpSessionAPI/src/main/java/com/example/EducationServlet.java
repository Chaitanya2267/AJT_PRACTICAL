package com.example;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EducationServlet")
public class EducationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String degree = request.getParameter("degree");
        String university = request.getParameter("university");
        
        HttpSession session = request.getSession();
        session.setAttribute("degree", degree);
        session.setAttribute("university", university);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h2>Final User Information</h2>");
        out.println("<p><b>Name:</b> " + session.getAttribute("name") + "</p>");
        out.println("<p><b>Age:</b> " + session.getAttribute("age") + "</p>");
        out.println("<p><b>Email:</b> " + session.getAttribute("email") + "</p>");
        out.println("<p><b>Phone:</b> " + session.getAttribute("phone") + "</p>");
        out.println("<p><b>Degree:</b> " + session.getAttribute("degree") + "</p>");
        out.println("<p><b>University:</b> " + session.getAttribute("university") + "</p>");
        out.println("<br><a href='personal.html'>Start Again</a>");
        out.println("</body></html>");
    }
}
