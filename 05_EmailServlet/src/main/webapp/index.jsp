<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Send Email</title>
</head>

<body>

    <h2>Send Email</h2>

    <form action="FirstMail" method="post">

        Enter Recipient Email:

        <input type="email"
               name="email"
               required>

        <input type="submit"
               value="Send Email">

    </form>

</body>
</html>