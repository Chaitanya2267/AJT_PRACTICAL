import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/webappdb", "root", "your_password");

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            PrintWriter out = response.getWriter();
            if (rs.next()) {
                out.println("Login Successful!");
            } else {
                out.println("Invalid credentials.");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
