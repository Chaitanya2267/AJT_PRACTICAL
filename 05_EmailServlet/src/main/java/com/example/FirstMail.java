package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstMail")
public class FirstMail extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        // Sender Gmail
        final String from = "yadavoggy3@gmail.com";

        // Use a NEW Gmail App Password here
        final String password = "fxjyszsjdocmikuu";

        // Get recipient from JSP
        String to = request.getParameter("email");

        // Gmail SMTP configuration
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.socketFactory.port", "465");
        props.put(
            "mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory"
        );

        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Create mail session
        Session session = Session.getInstance(
            props,
            new javax.mail.Authenticator() {

                protected PasswordAuthentication
                getPasswordAuthentication() {

                    return new PasswordAuthentication(
                        from,
                        password
                    );
                }
            }
        );

        try {

            // Create email
            Message message = new MimeMessage(session);

            message.setFrom(
                new InternetAddress(from)
            );

            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
            );

            message.setSubject("Congratulations 🎉");

            message.setText(
                "Hello!"
            );

            // Send email
            Transport.send(message);

            out.println("<h2>Email sent successfully!</h2>");
            out.println("<p>Recipient: " + to + "</p>");

        } catch (MessagingException e) {

            out.println("<h2>Error sending email</h2>");
            out.println("<p>" + e.getMessage() + "</p>");

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().println(
            "<h2>FirstMail Servlet is running on Tomcat 9</h2>"
        );
    }
}