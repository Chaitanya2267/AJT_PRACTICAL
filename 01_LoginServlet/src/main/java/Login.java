import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // Get values from HTML form
        String name = request.getParameter("name");
        String rollno = request.getParameter("rollno");
        String year = request.getParameter("year");
        String department = request.getParameter("department");

        // Display student details
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Details</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<center>");

        out.println("<h2>Student Registration Successful</h2>");

        out.println("<p><b>Name:</b> " + name + "</p>");
        out.println("<p><b>Roll No:</b> " + rollno + "</p>");
        out.println("<p><b>Year:</b> " + year + "</p>");
        out.println("<p><b>Department:</b> " + department + "</p>");

        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}